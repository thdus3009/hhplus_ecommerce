package com.ecommerce.item.domain.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.item.entity.ItemStock;

import jakarta.persistence.LockModeType;

@Repository
public interface ItemStockJpaRepository extends JpaRepository<ItemStock, Long> {
	@Query(value = "select stock from ItemStock stock " +
		"where stock.itemId in :itemIds")
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public List<ItemStock> findByItemIds(@Param("itemIds") List<Long> itemIds);

}
