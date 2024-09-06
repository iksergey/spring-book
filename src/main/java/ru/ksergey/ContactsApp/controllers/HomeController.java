package ru.ksergey.ContactsApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController { // MVC (Model-View-Controller)

    @GetMapping("/api")
    public String home() { // вызываемый метод
        return "start.html";
    }
}

//MVC (Model-View-Controller) играет роль посредника между моделью (данными) и представлением (интерфейсом пользователя).

//Аннотация @Controller обрабатывают HTTP-запросы и возвращают представления
// (например, HTML-страницы) или данные (например, JSON).

//Аннотация @GetMapping("/api/") используется для маппинга HTTP GET-запросов на определенный метод контроллера. В данном
// случае, она указывает, что метод будет обрабатывать GET-запросы, направленные на URL (/api/) вашего веб-приложения.
// @GetMapping является специализированной формой аннотации @RequestMapping