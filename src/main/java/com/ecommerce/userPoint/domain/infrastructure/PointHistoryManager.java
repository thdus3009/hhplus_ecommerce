package com.ecommerce.userPoint.domain.infrastructure;

import com.ecommerce.userPoint.entity.UserPointHistory;

public interface PointHistoryManager {
	UserPointHistory save(UserPointHistory history);
}

