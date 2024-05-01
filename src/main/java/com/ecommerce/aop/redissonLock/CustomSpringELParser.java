package com.ecommerce.aop.redissonLock;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * RedissonLock 어노테이션과 함께 사용되는 값을 파싱하기 위한 클래스이다.
 * 전달받은 Lock의 이름을 파싱 한다.
 */
public class CustomSpringELParser {
	private CustomSpringELParser() {
	}

	public static Object getDynamicValue(String[] parameterNames, Object[] args, String key) {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();

		for (int i = 0; i < parameterNames.length; i++) {
			context.setVariable(parameterNames[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, Object.class);
	}

}