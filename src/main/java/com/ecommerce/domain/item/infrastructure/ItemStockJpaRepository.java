package com.ecommerce.domain.item.infrastructure;

import com.ecommerce.domain.item.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemStockJpaRepository extends JpaRepository<ItemStock, Long> {
    @Query(value = "select stock from ItemStock stock " +
            "where stock.itemId in :itemIds")
    public List<ItemStock> findByItemIds(List<Long> itemIds);
}
