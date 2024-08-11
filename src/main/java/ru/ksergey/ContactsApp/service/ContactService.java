package ru.ksergey.ContactsApp.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.ksergey.ContactsApp.model.Contact;

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
