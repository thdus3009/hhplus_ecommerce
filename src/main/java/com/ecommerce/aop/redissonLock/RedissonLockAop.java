package com.ecommerce.aop.redissonLock;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import com.ecommerce.common.exception.CustomException;
import com.ecommerce.common.exception.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // log
@Aspect
@Component
@RequiredArgsConstructor
public class RedissonLockAop {
	private static final String REDISSON_LOCK_PREFIX = "LOCK:";

	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;

	@Around("@annotation(com.ecommerce.aop.redissonLock.RedissonLock)")
	public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);

		String key = REDISSON_LOCK_PREFIX +
					 CustomSpringELParser.getDynamicValue(
						 signature.getParameterNames(),
						 joinPoint.getArgs(),
						 redissonLock.key());

		log.info("lock on [method:{}] [key:{}]", method, key);

		RLock rLock = redissonClient.getLock(key);
		String lockName = rLock.getName();
		try {
			boolean available =
				rLock.tryLock(
					redissonLock.waitTime(),
					redissonLock.leaseTime(),
					redissonLock.timeUnit());
			if (!available) {
				throw new CustomException(ErrorCode.LOCK_NOT_AVAILABLE);
			}

			return aopForTransaction.proceed(joinPoint);

		} catch (InterruptedException e) {//락을 얻으려고 시도하다가 인터럽트를 받았을 때 발생
			throw new CustomException(ErrorCode.LOCK_INTERRUPTED_ERROR);
		} finally {
			try {
				rLock.unlock();
				log.info("unlock complete [Lock:{}] ", lockName);
			} catch (IllegalMonitorStateException e) {//락이 이미 종료되었을때 발생
				log.info("Redisson Lock Already Unlocked");
			}
		}
	}
}
