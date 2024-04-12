package com.ecommerce.domain.item.infrastructure;

import com.ecommerce.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Long> {
    @Query(value = "select item from Item item " +
            "where item.id in :itemIds")
    public List<Item> findByIds(List<Long> itemIds);

}
