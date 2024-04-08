package com.ecommerce.domain.point.repository;

import com.ecommerce.domain.point.entity.UserPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPointJpaRepository extends JpaRepository<UserPoint, Long> {

    Optional<UserPoint> findByUserId(Long userId);
}
