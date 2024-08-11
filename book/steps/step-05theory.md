Возможное решение

```java
@Data
@Builder
public class Contact {
    private UUID id;
    private String name;
    private String email;
}
```

```java
@RestController
@RequestMapping("/contact")
public class ContactController {

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = Contact.builder()
                .id(UUID.randomUUID())
                .name("John")
                .email("john@example.com")
                .build();
        return ResponseEntity.ok(contact);
    }
}
```

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.32</version>
    <scope>provided</scope>
</dependency>
```

[Шаг 6](./step-06.md)
