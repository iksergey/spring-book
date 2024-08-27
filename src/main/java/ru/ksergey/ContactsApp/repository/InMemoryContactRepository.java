package ru.ksergey.ContactsApp.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.ksergey.ContactsApp.model.Contact;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Repository
public class InMemoryContactRepository implements ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    @Override
    public Contact create(Contact contact) {
        contacts.add(contact);
        return contact;
    }

    @Override
    public Contact read(Integer id) {
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Contact> readAll() {
        return new ArrayList<>(contacts);
    }

    @Override
    public Contact update(Contact contact) {
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(contact.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            contacts.set(index, contact);
            return contact;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
