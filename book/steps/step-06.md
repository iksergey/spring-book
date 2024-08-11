Создайте пакет `service` внутри которого разместите

`ContactService.java`
```java
@Service
public class ContactService {

    public Contact getContact() {
        return Contact.builder()
                .id(UUID.randomUUID())
                .name("John")
                .email("john@example.com")
                .build();
    }
}
```

Отрефакторите `ContactController.java`

```java
@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}
```

[Далее](./step-06theory.md)
