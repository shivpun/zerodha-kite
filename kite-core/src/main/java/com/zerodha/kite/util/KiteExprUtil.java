package com.zerodha.kite.util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class KiteExprUtil {
	
	public static EvaluationContext context(Object present, Object past) {
		StandardEvaluationContext context = new StandardEvaluationContext(present);
		context.setVariable("c", present);
		context.setVariable("p", past);
		return context;
	}

	public static <T> T cmp(EvaluationContext context, String expression, Class<T> clazz) {
		ExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression(expression);
		return expr.getValue(context, clazz);
	}
}
