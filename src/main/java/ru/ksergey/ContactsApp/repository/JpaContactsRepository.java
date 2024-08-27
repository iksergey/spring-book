package ru.ksergey.ContactsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ksergey.ContactsApp.model.Contact;

public interface JpaContactsRepository extends JpaRepository<Contact, Integer> {
    // Дополнительные методы JPA, если необходимо
    // Например
    // Contact findByEmail(String email);
}
