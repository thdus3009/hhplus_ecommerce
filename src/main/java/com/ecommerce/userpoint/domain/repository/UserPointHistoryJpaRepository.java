package com.ecommerce.userpoint.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userpoint.entity.UserPointHistory;

@Repository
public interface UserPointHistoryJpaRepository extends JpaRepository<UserPointHistory, Long> {

}
