package com.ecommerce.userpoint.domain.repository;

import com.ecommerce.userpoint.entity.UserPointHistory;

public interface UserPointHistoryRepository {
	UserPointHistory save(UserPointHistory history);
}
