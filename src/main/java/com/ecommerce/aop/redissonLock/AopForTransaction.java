package com.ecommerce.aop.redissonLock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * RedissonLock 어노테이션이 적용된 부분은 부모트랜잭션 유무에 상관없이 별도의 트랜잭션으로 동작하도록 설정한다.
 * propagation = Propagation.REQUIRES_NEW로 지정한다.
 */
@Component
public class AopForTransaction {
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
		return joinPoint.proceed();
	}
}