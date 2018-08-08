package com.example.boottest.config.handler;

import org.springframework.core.convert.converter.Converter;

public interface RestErrorConverter<T> extends Converter<RestError, T> {
	T convert(RestError re);
}
