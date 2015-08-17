package com.github.warmuuh.message.web.util;

import spark.Request;

public class SparkRequestUtil {

	
	 
	
	
	public static int getParameter(Request req, String name, int defaultValue){
		return getParameter(req, name, defaultValue, null);
	}
	public static int getParameter(Request req, String name, int defaultValue, ParameterValidator<Integer> validator){
		String paramValue = getParameter(req, name, null, null);

		if (paramValue != null){
			Integer val = Integer.parseInt(paramValue);
			if (validator != null && !validator.isValid(val)){
				throw new IllegalArgumentException("Parameter value "+ paramValue + " is not valid for parameter " + name);
			}
			return val;
		}
		
		return defaultValue;
	}
	
	public static String getParameter(Request req, String name){
		return getParameter(req, name, (ParameterValidator<String>)null);
	}
	
	public static String getParameter(Request req, String name, ParameterValidator<String> validator){
		String parameter = getParameter(req, name, null, validator);
		if (parameter == null)
			throw new IllegalArgumentException("Parameter " + name + " not found.");
		return parameter;
	}
	
	public static String getParameter(Request req, String name, String defaultValue){
		return getParameter(req, name, defaultValue, null);
	}
	
	public static String getParameter(Request req, String name, String defaultValue, ParameterValidator<String> validator){
		String paramValue = req.params(":"+name);
		if (paramValue == null)
			paramValue = req.queryParams(name);
		if (paramValue != null && validator != null){
			if (!validator.isValid(paramValue)){
				throw new IllegalArgumentException("Parameter value "+ paramValue + " is not valid for parameter " + name);
			}
		}
		if (paramValue == null)
			return defaultValue;
		return paramValue;
	}
}
