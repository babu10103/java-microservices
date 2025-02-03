package com.msdemo.order_service.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientFactory {

    public WebClient createWebClient(String baseUrl) {
        return WebClient.builder().build();
//                .baseUrl(baseUrl)
//                .defaultHeader("Content-Type", "application/json")
//                .build();
    }
}
