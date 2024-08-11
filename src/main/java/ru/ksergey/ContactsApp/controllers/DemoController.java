package ru.ksergey.ContactsApp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable("id") String value) {
        String response = String.format("Передано значение: %s", value);
        return ResponseEntity.ok(response);
    }

}
