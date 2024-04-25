package com.ecommerce.stub;

import com.ecommerce.item.entity.Item;
import com.ecommerce.item.entity.ItemStock;
import com.ecommerce.item.entity.OrderItemStatus;
import com.ecommerce.order.api.dto.OrderAndOrderItems;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItem;
import com.ecommerce.userPoint.entity.UserPoint;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class StubData {
    // request, response 관련 함수 따로 빼놓기

    public static List<Item> items(List<Long> itemIds){
        List<Item> items = new ArrayList<>();
        for (Long itemId : itemIds){
            items.add(
                new Item(itemId, "상품"+itemId, 5000L, 1L, ZonedDateTime.now())
            );
        }
        return items;
    }
    public static List<ItemStock> itemStocks(List<Long> itemIds){
        List<ItemStock> itemStocks = new ArrayList<>();
        for (Long itemId : itemIds){
            itemStocks.add(
                    new ItemStock(itemId, 10L)
            );
        }
        return itemStocks;
    }

    public static UserPoint usePoint(Long userId) {
        return new UserPoint(1L, userId, 15000L, ZonedDateTime.now(), null);
    }
    public static OrderAndOrderItems order(Long userId, Long totalPrice){
        Order order = Order.builder()
                .id(1L)
                .uuid("asdf1234")
                .userId(userId)
                .totalPrice(totalPrice)
                .totalCount(1L)
                .receiverName("김소연")
                .receiverPhone("01011112222")
                .receiverAddress("서울특별시 강남구")
                .build();
        Item item = new Item(1L, "상품1", 5000L, 1L, ZonedDateTime.now());
        List<OrderItem> orderItems = List.of(
                OrderItem.builder()
                        .id(1L)
                        .order(order)
                        .item(item)
                        .status(OrderItemStatus.READY)
                        .itemName("abc 후드티")
                        .description(null)
                        .itemPrice(5000L)
                        .itemCount(1L)
                        .build()
        );
        return new OrderAndOrderItems(
                order,
                orderItems
        );
    }
}
