package ru.ksergey.ContactsApp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:11000")))
                .info(new Info().title("Название вашего API"));
    }
}

// Swagger UI доступен по адресу http://localhost:11000/swagger-ui/index.html

// настройка конфигурации?