### Метод `postItemWithValue`

```java
@PostMapping("/postItem")
public ResponseEntity<String> postItemWithValue(@RequestBody String data) {
    String response = String.format("postItemWithValue получил данные: %s", data);
    return ResponseEntity.ok(response);
}
```

- **@PostMapping("/postItem")**: Этот метод будет обрабатывать POST запросы, отправленные на URL `/demo/postItem`, если контроллер аннотирован как `@RequestMapping("/demo")`.
- **@RequestBody String data**: Аннотация `@RequestBody` указывает, что метод ожидает получить данные из тела запроса. Эти данные будут автоматически преобразованы в строку и переданы в параметр `data`.
- **ResponseEntity.ok(response)**: Возвращает HTTP ответ с кодом 200 (OK) и телом, содержащим строку `postItemWithValue получил данные: {data}`, где `{data}` — это данные, полученные из запроса.

### Метод `postItem`

```java
@PostMapping
public ResponseEntity<String> postItem(@RequestBody String data) {
    String response = String.format("postItem получил данные: %s", data);
    return ResponseEntity.ok(response);
}
```

- **@PostMapping**: Этот метод будет обрабатывать POST запросы, отправленные на URL `/demo`, так как он не имеет дополнительного пути.
- **@RequestBody String data**: Аналогично первому методу, данные из тела запроса будут преобразованы в строку и переданы в параметр `data`.
- **ResponseEntity.ok(response)**: Возвращает HTTP ответ с кодом 200 (OK) и телом, содержащим строку `postItem получил данные: {data}`, где `{data}` — это данные, полученные из запроса.

### Различия и особенности

- **URL маршрутизация**: Первый метод `postItemWithValue` обрабатывает запросы на более специфичный URL `/demo/postItem`, в то время как второй метод `postItem` обрабатывает запросы на базовый URL `/demo`.
- **Назначение методов**: Оба метода предназначены для обработки POST запросов и получения данных из тела запроса, но они могут быть использованы для разных целей в зависимости от URL, на который отправляется запрос.
- **Переопределение**: Если POST запрос отправляется на `/demo/postItem`, будет вызван метод `postItemWithValue`. Если запрос отправляется на `/demo`, будет вызван метод `postItem`. Это позволяет вам организовать логику обработки данных в зависимости от пути запроса.

[Шаг 5](./step-05.md)
