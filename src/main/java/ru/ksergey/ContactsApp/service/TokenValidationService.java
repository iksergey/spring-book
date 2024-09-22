package ru.ksergey.ContactsApp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TokenValidationService {
    private final WebClient webClient;

    public TokenValidationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public boolean validateToken(String token) {
        return webClient.post()
                .uri("/validate-token")
                .header("Authorization", token)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
