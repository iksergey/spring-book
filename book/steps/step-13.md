## Начало работы с Spring Boot и JPA, следуйте этой пошаговой инструкции:

### Шаг 1: Подключение зависимости Spring Data JPA

Начните с добавления зависимости `spring-boot-starter-data-jpa` в ваш проект Maven. Это позволит использовать возможности Spring Data JPA для работы с базами данных.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Шаг 2: Создание модели автомобиля

Создайте класс `Car`, который будет представлять сущность автомобиля с необходимыми полями.

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transport")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private String engineerName;
}
```

### Шаг 3: Описание репозитория CarRepository

Создайте интерфейс `CarRepository`, который будет использоваться для взаимодействия с базой данных. Он должен расширять `JpaRepository`.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
```

### Шаг 4: Взаимодействие с репозиторием через REST API

Создайте контроллер, который будет обрабатывать HTTP-запросы для взаимодействия с сущностями `Car`.

```java
package ru.ksergey.ContactsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ksergey.ContactsApp.model.Car;
import ru.ksergey.ContactsApp.repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        // Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setCityOfManufacture(carDetails.getCityOfManufacture());
        car.setEngineerName(carDetails.getEngineerName());
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        carRepository.delete(car);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
```

### Шаг 5: Добавьте исключение в пакет `exception`

```java
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```

#### Примеры HTTP-запросов

- **GET /cars**: Получить список всех автомобилей.
- **POST /cars**: Создать новый автомобиль. Тело запроса должно содержать JSON с данными автомобиля.
- **PUT /cars/{id}**: Обновить информацию об автомобиле по ID. Тело запроса должно содержать обновленные данные автомобиля в формате JSON.
- **DELETE /cars/{id}**: Удалить автомобиль по ID.

Проверьте базу данных, ещё раз изучите код. Попробуйте объяснить что произошло

[Далее](./step-13theory.md)
