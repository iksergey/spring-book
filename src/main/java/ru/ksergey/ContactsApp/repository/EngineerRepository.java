package ru.ksergey.ContactsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ksergey.ContactsApp.model.Engineer;

public interface EngineerRepository extends JpaRepository<Engineer, Long> {

}
