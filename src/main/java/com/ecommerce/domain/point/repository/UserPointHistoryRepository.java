package com.ecommerce.domain.point.repository;

import com.ecommerce.domain.point.entity.UserPointHistory;

public interface UserPointHistoryRepository {
    UserPointHistory save(UserPointHistory history);
}
