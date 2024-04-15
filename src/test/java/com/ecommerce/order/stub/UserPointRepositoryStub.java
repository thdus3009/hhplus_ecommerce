package com.ecommerce.order.stub;

import com.ecommerce.userPoint.entity.UserPoint;
import com.ecommerce.userPoint.domain.repository.UserPointRepository;

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
