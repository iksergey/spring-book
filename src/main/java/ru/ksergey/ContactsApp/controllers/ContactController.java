package ru.ksergey.ContactsApp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ksergey.ContactsApp.model.Contact;
import ru.ksergey.ContactsApp.service.ContactService;

@RestController
@RequestMapping("/contact")
@Tag(name = "Contact'ы", description = "Описание")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Operation(summary = "Получить случайный контакт", description = "Возвращает случайный контакт из базы данных.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение контакта"),
        @ApiResponse(responseCode = "404", description = "Контакт не найден"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @GetMapping(value = "/")
    public ResponseEntity<Contact> getRandomContact() {
        Contact contact = contactService.getContact();
        return ResponseEntity.ok(contact);
    }
}

