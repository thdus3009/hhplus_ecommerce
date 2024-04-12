package com.ecommerce.api.order.dto.mapper;

import com.ecommerce.api.order.dto.OrderResponseDto;
import com.ecommerce.domain.order.entity.Order;
import com.ecommerce.domain.order.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderResponseMapper {
    OrderResponseMapper INSTANCE = Mappers.getMapper(OrderResponseMapper.class);

    // Order, List<OrderItem>
    OrderResponseDto toOrderResponseDto(Order order, List<OrderItem> orderItem);
}
