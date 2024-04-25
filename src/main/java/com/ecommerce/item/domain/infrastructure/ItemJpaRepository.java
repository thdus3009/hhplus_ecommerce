package com.ecommerce.item.domain.infrastructure;

import com.ecommerce.item.api.dto.PopularItemResponseDto;
import com.ecommerce.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Long> {
    @Query(value = "select item from Item item " +
            "where item.id in :itemIds")
    public List<Item> findByIds(@Param("itemIds") List<Long> itemIds);

    @Query(value = "SELECT " +
                    "new com.ecommerce.item.api.dto.PopularItemResponseDto(i.id, i.name, i.price, SUM(ooi.itemCount)) " +
                    "FROM Item i " +
                    "JOIN (SELECT oi.item.id AS itemId, SUM(oi.itemCount) AS itemCount " +
                    "      FROM Order o " +
                    "      JOIN OrderItem oi ON o.id = oi.order.id " +
                    "      WHERE o.orderDate BETWEEN :startDate AND CURRENT_DATE() " +
                    "      GROUP BY oi.item.id " +
                    "      ORDER BY itemCount DESC " +
                    "      LIMIT :count) ooi " +
                    "ON i.id = ooi.itemId " +
                    "GROUP BY i " +
                    "ORDER BY SUM(ooi.itemCount) DESC")
    public List<PopularItemResponseDto> findItemsWithDateAndCount(
            @Param("startDate") ZonedDateTime startDate, @Param("count") Long count);

}
