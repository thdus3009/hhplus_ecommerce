package com.ecommerce.domain.point.repository;

import com.ecommerce.domain.point.entity.UserPointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long> {

}
