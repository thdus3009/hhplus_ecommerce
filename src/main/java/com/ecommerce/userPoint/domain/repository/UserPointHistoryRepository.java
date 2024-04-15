package com.ecommerce.userPoint.domain.repository;

import com.ecommerce.userPoint.entity.UserPointHistory;

public interface UserPointHistoryRepository {
    UserPointHistory save(UserPointHistory history);
}
