package ru.ksergey.ContactsApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ksergey.ContactsApp.model.Contact;
import ru.ksergey.ContactsApp.service.ContactService;

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
