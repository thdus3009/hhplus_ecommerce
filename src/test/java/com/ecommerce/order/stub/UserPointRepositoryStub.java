package com.ecommerce.order.stub;

import com.ecommerce.domain.point.entity.UserPoint;
import com.ecommerce.domain.point.repository.UserPointRepository;

import java.util.Optional;

public class UserPointRepositoryStub implements UserPointRepository {
    @Override
    public Optional<UserPoint> findByUserId(Long userId) {
        return Optional.empty();
    }

    @Override
    public UserPoint save(UserPoint point) {
        return null;
    }
}
