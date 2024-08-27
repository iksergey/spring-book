package ru.ksergey.ContactsApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ksergey.ContactsApp.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByEngineerName(String engineerName);
}