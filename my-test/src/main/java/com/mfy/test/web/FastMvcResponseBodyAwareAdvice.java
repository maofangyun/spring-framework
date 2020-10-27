package com.mfy.test.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 统一返回数据封装
 */
@ControllerAdvice("com.mfy.test.web")
public class FastMvcResponseBodyAwareAdvice implements ResponseBodyAdvice<Object> {

	private final Map<Method, Boolean> supportsCache = new WeakHashMap<>();
	// basePackages多余
	private final String [] basePackages = {"com.mfy.test.web"};	// 可以用@Value替代
	private ObjectMapper objectMapper = new ObjectMapper();


	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		if (supportsCache.containsKey(returnType.getMethod())) {
			return supportsCache.get(returnType.getMethod());
		}
		boolean isSupport = getIsSupport(returnType);
		supportsCache.put(returnType.getMethod(), isSupport);
		return isSupport;
	}

	private boolean getIsSupport(MethodParameter returnType) {
		Class<?> declaringClass = returnType.getMember().getDeclaringClass();

		for (int i = 0; i < basePackages.length; i++) {
			if (declaringClass.getPackage().getName().startsWith(basePackages[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
								  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
								  ServerHttpResponse response) {
		FastResult<Object> result = new FastResult<>();
		result.setData(body);
		result.setMsg("success");
		if (returnType.getGenericParameterType().equals(String.class)) {
			try {
				response.getHeaders().set("Content-Type", "application/json;charset=utf-8");
				return objectMapper.writeValueAsString(result);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
