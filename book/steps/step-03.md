Создайте контроллер `DemoController` и укажите аннотации `@RestController` и `@RequestMapping("/demo")`

```java
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable("id") String value) {
        String response = String.format("Передано значение: %s", value);
        return ResponseEntity.ok(response);
    }
}
```

Перейдите по адресу `http://localhost:PORT/demo/123`

### Попробуйте объяснить что произошло

[Далее](./step-03theory.md)
