package com.example.boottest.service;

public interface VerifyCodeService {
	void send(String key, String value);
	int verify(String key, String value);
}
