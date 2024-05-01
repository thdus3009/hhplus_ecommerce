package com.ecommerce.userpoint.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.userpoint.entity.UserPoint;

@Repository
public interface UserPointJpaRepository extends JpaRepository<UserPoint, Long> {

	Optional<UserPoint> findByUserId(Long userId);
}
