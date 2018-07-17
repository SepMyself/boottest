package com.example.boottest.model;

import org.springframework.beans.factory.annotation.Value;

public class CustomCfg {
    //@Value("${custom.api-key")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
