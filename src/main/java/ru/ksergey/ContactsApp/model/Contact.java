package ru.ksergey.ContactsApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {
    private Integer id;
    private String name;
    private String email;
}
