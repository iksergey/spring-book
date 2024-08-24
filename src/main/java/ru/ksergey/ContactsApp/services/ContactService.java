package ru.ksergey.ContactsApp.services;

import org.springframework.stereotype.Service;
import ru.ksergey.ContactsApp.models.Contact;

import java.util.UUID;

@Service // Сервисный класс
public class ContactService {

    public Contact getContact(){
        return Contact.builder()
                .id(UUID.randomUUID())
                .firstName("Vadim")
                .email("vadim@mail.ru")
                .build();
    }
}

//@Service: Эта аннотация используется для обозначения класса как сервисного компонента в Spring. Это специализированная
// форма аннотации @Component, которая указывает, что класс содержит бизнес-логику. Когда Spring сканирует ваш проект,
// он находит классы, аннотированные @Service, и создает их экземпляры как бины в контексте приложения.

//В контексте Spring, бинами называются объекты, которые управляются контейнером Inversion of Control (IoC) Spring.
// Эти бины формируют основу приложения и создаются, конфигурируются и управляются контейнером Spring.