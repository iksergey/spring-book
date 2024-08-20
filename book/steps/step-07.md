Чтобы подключить Swagger к приложению на Spring Boot

1. **Добавьте зависимости** в ваш `pom.xml` 

```xml
<dependency>  
    <groupId>org.springdoc</groupId>  
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>  
    <version>2.6.0</version>  
</dependency>
```
  
2. **Создайте класс конфигурации Swagger**. 
`configuration/SwaggerConfig`

```java
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
            .servers(List.of(new Server().url("http://localhost:8080")))  
            .info(new Info().title("Название вашего API"));  
    }  
}
```

3. **Запустите ваше приложение**. После запуска приложения Swagger UI будет доступен по адресу `http://localhost:PORT/swagger-ui/index.html` 

4. Пример документации https://github.com/iksergey/spring-book/blob/mastery/src/main/java/ru/ksergey/ContactsApp/controllers/ContactController.java

## [Шаг 8](./step-08.md)
