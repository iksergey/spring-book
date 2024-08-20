package ru.ksergey.ContactsApp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ksergey.ContactsApp.model.Contact;
import ru.ksergey.ContactsApp.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/contact")
@Tag(name = "Contact'ы", description = "Описание")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Operation(summary = "Получить контакт по ID", description = "Возвращает контакт по указанному идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение контакта"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Integer id) {
        Contact contact = contactService.getContact(id);
        return contact != null ? ResponseEntity.ok(contact) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Создать новый контакт", description = "Создает новый контакт с указанными данными.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Контакт успешно создан") })
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact.getName(), contact.getEmail());
        return ResponseEntity.status(201).body(createdContact);
    }

    @Operation(summary = "Обновить контакт", description = "Обновляет данные существующего контакта.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контакт успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Integer id, @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(id, contact.getName(), contact.getEmail());
        return updatedContact != null ? ResponseEntity.ok(updatedContact) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Удалить контакт", description = "Удаляет контакт по указанному идентификатору.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Контакт успешно удален"),
            @ApiResponse(responseCode = "404", description = "Контакт не найден") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer id) {
        boolean deleted = contactService.deleteContact(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Получить все контакты", description = "Возвращает список всех контактов.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка контактов") })
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
}
