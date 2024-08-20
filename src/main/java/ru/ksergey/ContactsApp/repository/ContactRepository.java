package ru.ksergey.ContactsApp.repository;

import ru.ksergey.ContactsApp.model.Contact;

import java.util.List;

public interface ContactRepository {
    Contact create(Contact contact);

    Contact read(Integer id);

    List<Contact> readAll();

    Contact update(Contact contact);

    boolean delete(Integer id);
}
