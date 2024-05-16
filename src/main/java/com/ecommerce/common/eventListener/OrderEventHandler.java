package com.ecommerce.common.eventListener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ecommerce.order.api.dto.OrderAndOrderItems;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Component
@Slf4j
public class OrderEventHandler {
	private final OkHttpClient client = new OkHttpClient();

	@Async("threadPoolTaskExecutor")
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void orderEventHandler(OrderAndOrderItems event) {
		String url = "https://test.com";

		RequestBody body = RequestBody.create(event.toString(), MediaType.get("application/json; charset=utf-8"));
		Request request = new Request.Builder()
			.url(url)
			.post(body)
			.build();

		try {
			client.newCall(request).execute();
		} catch (Exception e) {
			log.error("API 요청 실패, {}", e.getMessage());
		}
	}
}