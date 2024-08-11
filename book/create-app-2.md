# Аннотация `@SpringBootApplication` 

В Spring Boot является ключевой и упрощает настройку приложения. Она объединяет в себе три другие аннотации:

1. **@Configuration**: Обозначает, что класс предоставляет конфигурацию для контекста приложения. Это позволяет регистрировать дополнительные бины и импортировать другие классы конфигурации.

2. **@EnableAutoConfiguration**: Включает механизм автоконфигурации Spring Boot, который автоматически настраивает приложение на основе библиотек, присутствующих в classpath. Например, если в classpath есть `spring-webmvc`, приложение будет настроено как веб-приложение с настройкой `DispatcherServlet`.

3. **@ComponentScan**: Указывает Spring сканировать пакет, в котором находится класс, и его подпакеты для поиска аннотированных компонентов, таких как `@Component`, `@Service`, `@Repository`, и автоматически регистрировать их как бины в контексте приложения.


Источники: [1](https://spring.io/guides/gs/spring-boot/)
[2](https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html)
[3](https://dev.to/saurabhnative/purpose-of-springbootapplication-annotation-in-spring-boot-29j9)
[4](https://docs.spring.io/spring-boot/reference/using/using-the-springbootapplication-annotation.html)
[5](https://www.geeksforgeeks.org/spring-boot-annotations/)
[6](https://www.geeksforgeeks.org/how-spring-boot-application-works-internally/)
[7](https://www.digitalocean.com/community/tutorials/springbootapplication-springapplication)

# Аннотация `@ComponentScan`

В Spring используется для указания пакетов, которые необходимо сканировать на наличие компонентов, управляемых Spring. Она играет ключевую роль в автоматическом обнаружении и регистрации бинов в контексте приложения. Вот основные аспекты использования `@ComponentScan`:

### Основные функции `@ComponentScan`

- **Определение пакетов для сканирования**: `@ComponentScan` позволяет указать, какие пакеты должны быть сканированы для поиска классов, аннотированных `@Component`, а также его специализаций, таких как `@Service`, `@Controller`, и `@Repository`.

- **Автоматическая регистрация бинов**: Все классы, найденные в указанных пакетах и аннотированные соответствующими аннотациями, будут автоматически зарегистрированы в `ApplicationContext`, что делает их доступными для внедрения зависимостей (DI).

- В контексте Spring, `бинами` называются объекты, которые управляются контейнером Inversion of Control (IoC) Spring. Эти бины формируют основу приложения и создаются, конфигурируются и управляются контейнером Spring.

- **Гибкость настройки**: Аннотация позволяет указать конкретные пакеты с помощью атрибутов `basePackages` или `basePackageClasses`, что делает процесс сканирования более гибким и настраиваемым.

- **Использование по умолчанию**: Если `basePackages` не указаны, сканирование происходит в пакете, где объявлена аннотация `@ComponentScan`, и во всех его подпакетах.

### Пример использования

```java
import org.springframework.stereotype.Component;

@Component
public class Foo {

}
```

Источники:
[1](https://www.geeksforgeeks.org/spring-componentscan-annotation-with-example/) 
[2](https://reflectoring.io/spring-component-scanning/) 
[3](https://springframework.guru/spring-component-scan/) 
[4](https://stackoverflow.com/questions/28963639/how-to-understand-spring-componentscan) 
[5](https://springframework4u.wordpress.com/spring-core/component-scan/) 
[6](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/ComponentScan.html) 
[7](https://docs.spring.io/spring-framework/reference/core/beans/classpath-scanning.html) 
[8](https://codingnomads.com/spring-componentscan-annotation) 

[Далее >>](./steps/step-01.md)
