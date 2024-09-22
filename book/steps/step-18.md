# Подготовка к микросервисам

Этот гайд поможет вам настроить аутентификацию в вашем Spring Boot приложении, используя перехватчик (interceptor) и проверку токена.

❗️ Перед обновлением создайте отдельную ветку с вашим приложением

### 1. Настройка конфигурации

Создайте класс `WebConfig` в пакете `configuration`:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/contact/**");
    }
}
```

**Пояснения:**
- `@Configuration`: Аннотация, указывающая Spring, что этот класс содержит конфигурацию.
- `WebMvcConfigurer`: Интерфейс для настройки Spring MVC.
- `addInterceptors`: Метод для добавления перехватчиков. Здесь мы добавляем наш `AuthenticationInterceptor` ко всем путям, начинающимся с "/contact/". 

При необходимости, через запятую можно указать дополнительные пути. Например: 

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns("/contact/**", "/api/cars/{id}");
}
```

### 2. Сервис валидации токена

Создайте класс `TokenValidationService` в пакете `service`:

```java
@Service
public class TokenValidationService {
    private final WebClient webClient;

    public TokenValidationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
            .baseUrl("http://localhost:8080")
            .build();
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
```

**Пояснения:**
- `@Service`: Аннотация, указывающая Spring, что этот класс является сервисом.
- `WebClient`: Клиент для выполнения HTTP-запросов.
- `validateToken`: Метод для проверки валидности токена путем отправки запроса на другой сервис.

### 3. Создание перехватчика аутентификации

Создайте класс `AuthenticationInterceptor` в пакете `infrastructure`:

```java
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final TokenValidationService tokenValidationService;

    public AuthenticationInterceptor(TokenValidationService tokenValidationService) {
        this.tokenValidationService = tokenValidationService;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !tokenValidationService.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
```

**Пояснения:**
- `@Component`: Аннотация, указывающая Spring, что этот класс является компонентом.
- `HandlerInterceptor`: Интерфейс для создания перехватчиков в Spring MVC.
- `preHandle`: Метод, который выполняется перед обработкой запроса. Здесь мы проверяем наличие и валидность токена.

### 4. Настройка порта приложения

Измените файл `application.properties`:

```text
server.port=11000
```

Это изменит порт, на котором запускается ваше приложение, на 11000.

### 5. Настройка Swagger (опционально)

Если вы используете Swagger для документации API, создайте класс `SwaggerConfig` в пакете `configuration`:

```java
@Configuration
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(List.of(new Server().url("http://localhost:11000")))
                .info(new Info().title("Demo Jwt Spring Security").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                        .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
```

**Пояснения:**
- Этот класс настраивает Swagger для работы с JWT-аутентификацией и указывает правильный URL сервера.

### Заключение

Теперь ваше приложение настроено для использования аутентификации с помощью JWT-токенов. Все запросы к эндпоинтам, начинающимся с "/contact/", будут проходить проверку токена. Не забудьте добавить все необходимые зависимости в ваш проект для корректной работы этого кода.
