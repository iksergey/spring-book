package ru.ksergey.ContactsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ksergey.ContactsApp.model.Contact;
import ru.ksergey.ContactsApp.repository.ContactRepository;

import java.util.List;
import java.util.Random;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact getContact(Integer id) {
        return contactRepository.read(id);
    }

    public Contact createContact(String name, String email) {
        Contact contact = Contact.builder()
                .id(new Random().nextInt(0,Integer.MAX_VALUE))
                .name(name)
                .email(email)
                .build();
        return contactRepository.create(contact);
    }

    public Contact updateContact(Integer id, String name, String email) {
        Contact contact = Contact.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
        return contactRepository.update(contact);
    }

    public boolean deleteContact(Integer id) {
        return contactRepository.delete(id);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.readAll();
    }
}
