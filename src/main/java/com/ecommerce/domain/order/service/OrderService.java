package com.ecommerce.domain.order.service;

import com.ecommerce.api.order.dto.OrderItemDto;
import com.ecommerce.api.order.dto.OrderRequestDto;
import com.ecommerce.api.order.dto.OrderResponseDto;
import com.ecommerce.api.order.dto.mapper.OrderResponseMapper;
import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;
import com.ecommerce.domain.item.entity.Item;
import com.ecommerce.domain.item.entity.ItemStock;
import com.ecommerce.domain.item.infrastructure.ItemRepository;
import com.ecommerce.domain.item.infrastructure.ItemStockRepository;
import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.entity.OrderItem;
import com.ecommerce.domain.order.infrastructure.OrderItemRepository;
import com.ecommerce.domain.order.infrastructure.OrderRepository;
import com.ecommerce.domain.order.validator.OrderValidator;
import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemStockRepository itemStockRepository;
    private final UserPointRepository userPointRepository;
    private final ItemRepository itemRepository;
    private final OrderValidator orderValidator;

    @Transactional
    public OrderResponseDto save(OrderRequestDto orderRequestDto){
        // 주문 상품 아이디 리스트
        List<Long> itemIds = orderRequestDto.getItems().stream()
                .map(OrderItemDto::getItemId)
                .collect(Collectors.toList());

        // 상품 재고 및 상품 정보 확인
        List<ItemStock> itemStocks = itemStockRepository.findByItemIds(itemIds);
        orderValidator.quantityZeroCheck(itemStocks);

        UserPoint userPoint = userPointRepository.findByUserId(orderRequestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_POINT_NULL));
        List<Item> items = itemRepository.findByIds(itemIds);

        // 포인트 잔고 확인
        Long totalPrice = orderValidator.priceCheck(userPoint.getPoint(), items);

        // 주문, 주문 상품 추가
        Order order = Order.builder()
                .userId(orderRequestDto.getUserId())
                .totalPrice(totalPrice)
                .totalCount(orderRequestDto.calculateTotalItemCnt())
                .receiverName(orderRequestDto.getReceiverName())
                .receiverPhone(orderRequestDto.getReceiverPhone())
                .receiverAddress(orderRequestDto.getReceiverAddress())
                .build();
        Order savedOrder = orderRepository.save(order);

        // 제품 수량 차감 및 OrderItem 추가
        List<OrderItem> orderItems = new ArrayList<>();
        for (ItemStock itemStock : itemStocks){
            // 상품 Quantity 변경
            Long itemCnt = orderValidator.orderQuantityCheck(orderRequestDto, itemStock);
            Long remainCnt = itemStock.getQuantity()-itemCnt;
            itemStock.setQuantity(remainCnt);
            // 상품 재고 Quantity 변경
            Item item = items.stream()
                    .filter(i -> i.getId()==itemStock.getItemId())
                    .findFirst()
                    .orElseThrow(() -> new CustomException(ErrorCode.ITEM_NOT_FOUND));
            item.setQuantity(remainCnt);

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .itemId(item.getId())
                    .itemName(item.getName())
                    .itemCount(itemCnt)
                    .itemPrice(item.getPrice())
                    .build();

            orderItems.add(orderItem);
        }
        // 주문 상품 데이터 저장
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);

        // 상품 재고 업데이트 (saveAll)
        itemStockRepository.saveAll(itemStocks);
        itemRepository.saveAll(items);

        return OrderResponseMapper.INSTANCE.toOrderResponseDto(order, orderItems);
    }
}
