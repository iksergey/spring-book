Зависимость `ContactService` в контроллере `ContactController` автоматически создается и передается благодаря механизму инверсии управления (IoC) и внедрению зависимостей (DI). 

### Напоминание
- В контексте Spring, `бинами` называются объекты, которые управляются контейнером Inversion of Control (IoC) Spring. Эти бины формируют основу приложения и создаются, конфигурируются и управляются контейнером Spring.

### Аннотация @Service

- **`@Service`**: Эта аннотация используется для обозначения класса как сервисного компонента в Spring. Это специализированная форма аннотации `@Component`, которая указывает, что класс содержит бизнес-логику. Когда Spring сканирует ваш проект, он находит классы, аннотированные `@Service`, и создает их экземпляры как бины в контексте приложения.

### Конструкторное внедрение

- **Конструктор `ContactController(ContactService contactService)`**: Это пример конструкторного внедрения зависимостей. Spring автоматически передает экземпляр `ContactService` в этот конструктор, когда создает бин `ContactController`. Это возможно благодаря тому, что `ContactService` был зарегистрирован как бин через аннотацию `@Service`.

### Аннотация @Autowired

- **`@Autowired`**: Эта аннотация используется для автоматического внедрения зависимостей в поля, методы или конструкторы. Spring Framework автоматически находит и внедряет подходящий бин в отмеченное место.

### Изменение кода с использованием @Autowired

Вы можете изменить конструкторное внедрение на поле или метод с помощью `@Autowired`. Вот как это можно сделать:

#### Внедрение через поле

Отрефакторите `ContactController`

```java
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}
```

#### Внедрение через метод

```java
@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}
```

### Объяснение

- **Конструкторное внедрение**: Рекомендуемый способ, так как он позволяет создавать неизменяемые объекты и делает зависимости явными.
- **Внедрение через поле**: Удобный способ, но не рекомендуется, так как делает тестирование сложнее и зависимости менее явными.
- **Внедрение через метод**: Позволяет выполнять дополнительную логику при установке зависимости, но также делает зависимости менее явными.

## [Шаг 7](./step-07.md)
