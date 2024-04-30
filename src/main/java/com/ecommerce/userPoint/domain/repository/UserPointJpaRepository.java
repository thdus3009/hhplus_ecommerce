package com.ecommerce.userPoint.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userPoint.entity.UserPoint;

@Repository
public interface UserPointJpaRepository extends JpaRepository<UserPoint, Long> {

	Optional<UserPoint> findByUserId(Long userId);
}
