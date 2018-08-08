package com.example.boottest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	@Value("${my.verifyCode.length}")
	private int length;

	public void send(String key, String value){

	}

	public int verify(String key, String value) {
		return 0;
	}
}
