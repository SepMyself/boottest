package com.example.boottest.config.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class RestExceptionHandler { //} extends AbstractHandlerExceptionResolver implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	private HttpMessageConverter<?>[] messageConverters = null;
	private List<HttpMessageConverter<?>> allMessageConverters = null;

	private RestErrorResolver errorResolver;
	private RestErrorConverter<?> errorConverter;

	public RestExceptionHandler() {
		//this.errorResolver = new DefaultRestErrorResolver();
		this.errorConverter = new MapRestErrorConverter();
	}
}
