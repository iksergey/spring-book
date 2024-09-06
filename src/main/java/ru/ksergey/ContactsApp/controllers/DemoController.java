package ru.ksergey.ContactsApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
public class DemoController {


    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable("id") String value) {
        String response = String.format("Передано значение: %s", value);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/postItem")
    public ResponseEntity<String> getItemWithValue(@RequestBody String data) { //Данные будут переданы в метод как строка.
        String response = String.format("postItemWithValue получил данные: %s ", data);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> getItem(@RequestBody String data) {
        String response = String.format("postItem получил данные: %s ", data);
        return ResponseEntity.ok(response);
    }
}


//@RestController является сочетанием аннотаций @Controller и @ResponseBody, что означает, что все методы в
// этом классе будут возвращать данные напрямую в HTTP-ответ (в формате JSON или XML), а не представление.
// Таким образом, возвращаемый объект Contact будет автоматически сериализован в JSON и отправлен в ответе.

//@RequestMapping(“/demo”) используется для задания базового URL для всех методов в контроллере, все методы в
// DemoController будут доступны по URL, начинающемуся с /demo.

//@GetMapping используется для обработки HTTP GET запросов. Путь /{id} указывает, что метод будет принимать
// переменную часть URL, которая будет передана в метод в качестве параметра.

//@PathVariable связывает переменную из URL с параметром метода.
//В данном случае, значение из {id} в URL будет передано в метод как параметр value.

//String.format("Передано значение: %s", value) формирует строку ответа с использованием значения переменной value.
//Если значение value равно "привет!", то результатом этой строки будет "Передано значение: привет!".

//ResponseEntity.ok() создает объект ResponseEntity с HTTP статусом 200 (OK), указывает на успешное выполнение запроса.

// http://localhost:11000/demo/привет!

//@PostMapping(“/postItem”) будет обрабатывать POST запросы, отправленные на URL /demo/postItem, если контроллер
// аннотирован как @RequestMapping("/demo").

//@RequestBody указывает, что метод ожидает получить данные из тела запроса.

//ResponseEntity.ok(response): Возвращает HTTP ответ с кодом 200 (OK) и телом, содержащим строку
// postItemWithValue получил данные: {data}, где {data} — это данные, полученные из запроса.