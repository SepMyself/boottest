package com.example.boottest.util;

import com.example.boottest.config.BodyReaderRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class RequestUtil {
	private static String tokenHeader;

	@Autowired
	private static ObjectMapper mapper;

	public static String formatRequest(BodyReaderRequestWrapper request) throws IOException {
		//url, authorize, body
		String token = request.getHeader(tokenHeader);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(request.getRequestURL())
				.append("\n")
				.append(token != null ? token + "\n" : "")
				.append("\n");
		stringBuilder.append(request.getBody())
				.append("\n");
		stringBuilder.append(request.getBody())
				.append("\n");

		return stringBuilder.toString();
	}


	//对于static变量
	//只能在setAppId方法上加注解，另外class需要加 @Component等注解，这样spring才能扫描到
	@Value("${jwt.header}")
	public void setTokenHeader(String tokenHeader) {
		RequestUtil.tokenHeader = tokenHeader;
	}
}
