Добавьте в контроллер `DemoController` два метода

```java
@PostMapping("/postItem")
public ResponseEntity<String> postItemWithValue(@RequestBody String data) {
    String response = String.format("postItemWithValue получил данные: %s", data);
    return ResponseEntity.ok(response);
}

@PostMapping
public ResponseEntity<String> postItem(@RequestBody String data) {
    String response = String.format("postItem получил данные: %s", data);
    return ResponseEntity.ok(response);
}
```

выполните post-запрос на `http://localhost:PORT/demo/postItem` c телом

```json
{
    "data": "какие-то данные"
}
```

- Какой метод был вызван?
- Возможно ли вызвать другой?

[Далее](./step-04theory.md)
