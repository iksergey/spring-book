package ru.ksergey.ContactsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ksergey.ContactsApp.models.Contact;
import org.springframework.http.ResponseEntity;
import ru.ksergey.ContactsApp.services.ContactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    @Autowired // Внедрение зависимости.
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}

//@Autowired: Эта аннотация используется для автоматического внедрения зависимостей в поля, методы или конструкторы.
// Spring Framework автоматически находит и внедряет подходящий бин в отмеченное место.

//Конструктор setContactService(ContactService contactService): Это пример конструкторного внедрения зависимостей.
// Spring автоматически передает экземпляр ContactService в этот конструктор, когда создает бин ContactController.
// Это возможно благодаря тому, что ContactService был зарегистрирован как бин через аннотацию @Service.

//@GetMapping(value = "/") указывает, что этот метод будет обрабатывать GET-запросы по URL /contact/.

//getRandomContact() создает и возвращает случайный контакт. Контакт создается с использованием билдерzа,
// что является популярным паттерном проектирования для создания объектов.

//ID: случайно сгенерированный с помощью UUID.randomUUID().

//Конструкторное внедрение: Рекомендуемый способ, позволяет создавать неизменяемые объекты и делает зависимости явными.
//Внедрение через поле: Удобный способ, не рекомендуется, делает тестирование сложнее и зависимости менее явными.
//Внедрение через метод: Позволяет выполнять дополнительную логику, но также делает зависимости менее явными.