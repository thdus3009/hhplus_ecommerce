package com.ecommerce.userpoint.domain.infrastructure;

import com.ecommerce.userpoint.entity.UserPointHistory;

public interface PointHistoryManager {
	UserPointHistory save(UserPointHistory history);
}

