package com.github.warmuuh.message.web.util;

@FunctionalInterface
public interface ParameterValidator<T> {

	public boolean isValid(T value);
	
}
