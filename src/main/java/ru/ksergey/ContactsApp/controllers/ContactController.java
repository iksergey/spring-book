package ru.ksergey.ContactsApp.controllers;

import ru.ksergey.ContactsApp.models.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact(){
        Contact contact = Contact.builder()
                .id(UUID.randomUUID())
                .firstName("Vadim")
                .email("vadim@mail.ru")
                .build();
        return ResponseEntity.ok(contact);
    }
}

//@GetMapping(value = "/") указывает, что этот метод будет обрабатывать GET-запросы по URL /contact/.

//getRandomContact() создает и возвращает случайный контакт. Контакт создается с использованием билдера,
// что является популярным паттерном проектирования для создания объектов.

//ID: случайно сгенерированный с помощью UUID.randomUUID().