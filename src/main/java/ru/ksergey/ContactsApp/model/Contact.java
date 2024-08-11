package ru.ksergey.ContactsApp.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Contact {
    private UUID id;
    private String name;
    private String email;
}
