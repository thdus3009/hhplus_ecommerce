package com.ecommerce.userPoint.domain.repository;

import com.ecommerce.userPoint.entity.UserPointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long> {

}
