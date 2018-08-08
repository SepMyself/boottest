package com.example.boottest.config.handler;

import javax.servlet.ServletRequest;

public interface RestErrorResolver {
	RestError resolveError(ServletRequest request, Object handler, Exception ex);
}
