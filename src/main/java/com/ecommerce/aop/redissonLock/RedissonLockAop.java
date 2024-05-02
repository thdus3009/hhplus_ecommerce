package com.ecommerce.aop.redissonLock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ecommerce.common.exception.BadLockIdentifierException;
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

	@Around("@annotation(com.ecommerce.aop.redissonLock.RedissonLock)") // 첫번째 매개변수
	public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();
		Method method = signature.getMethod();
		RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
		String baseKey = redissonLock.key();

		// String dynamicKey =
		// 	generateDynamicKey(
		// 		redissonLock.identifier(),
		// 		joinPoint.getArgs(),
		// 		redissonLock.paramClassType(),
		// 		signature.getParameterNames()
		// 	);
		String dynamicKey = "";

		RLock rLock = redissonClient.getLock(REDISSON_LOCK_PREFIX + baseKey + ":" + dynamicKey);
		log.info("method " + method + "/ redisson 키 설정" + baseKey + ":" + dynamicKey);
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

		} catch (InterruptedException e) { //락을 얻으려고 시도하다가 인터럽트를 받았을 때 발생
			throw new CustomException(ErrorCode.LOCK_INTERRUPTED_ERROR);
		} finally {
			try {
				rLock.unlock();
				log.info("unlock complete [Lock:{}] ", lockName);
			} catch (IllegalMonitorStateException e) { //락이 이미 종료되었을때 발생
				log.info("Redisson Lock Already Unlocked");
			}
		}
	}

	public String generateDynamicKey(String identifier, Object[] args, Class<?> paramClassType,
		String[] parameterNames) {
		try {
			String dynamicKey;
			if (paramClassType.equals(Object.class)) {
				dynamicKey = createDynamicKeyFromPrimitive(parameterNames, args, identifier);
			} else {
				dynamicKey = createDynamicKeyFromObject(args, paramClassType, identifier);
			}
			return dynamicKey;
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException error) {
			log.error(error.getMessage());
			throw new BadLockIdentifierException();
		}
	}

	public String createDynamicKeyFromPrimitive(
		String[] methodParameterNames, Object[] args, String paramName) {
		for (int i = 0; i < methodParameterNames.length; i++) {
			if (methodParameterNames[i].equals(paramName)) {
				return String.valueOf(args[i]);
			}
		}
		throw new BadLockIdentifierException();
	}

	public String createDynamicKeyFromObject(
		Object[] args, Class<?> paramClassType, String identifier)
		throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		String paramClassName = paramClassType.getSimpleName();
		for (int i = 0; i < args.length; i++) {
			String argsClassName = args[i].getClass().getSimpleName();
			if (argsClassName.startsWith(paramClassName)) {
				Class<?> aClass = args[i].getClass();
				String capitalize = StringUtils.capitalize(identifier);
				Object result = aClass.getMethod("get" + capitalize).invoke(args[i]);
				return String.valueOf(result);
			}
		}
		throw new BadLockIdentifierException();
	}
}
