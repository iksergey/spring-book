package ru.ksergey.ContactsApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ksergey.ContactsApp.model.Contact;

import java.util.UUID;

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
