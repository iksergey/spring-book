package ru.ksergey.ContactsApp.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Contact {

    private UUID id;
    private String firstName;
    private String email;
}
