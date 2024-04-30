package com.ecommerce.userPoint.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userPoint.entity.UserPointHistory;

@Repository
public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long> {

}
