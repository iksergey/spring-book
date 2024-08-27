## "Распилим" структуры таблицы

Чтобы изменить структуру данных и установить связь между автомобилями и инженерами, выполните следующие шаги:

## Шаг 1: Изменение класса `Car`

1. Удалите поле `engineerName` и добавьте поле `engineerId`, которое будет ссылаться на идентификатор инженера.

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private int year;
    private String cityOfManufacture;
    private Long engineerId;  // заменили engineerName на engineerId
}
```

## Шаг 2: Создание сущности `Engineer`

Создайте новый класс `Engineer`, который будет представлять инженера.

```java
@Entity
@Table(name = "engineers")
public class Engineer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String description;
}
```

## Шаг 3: Обновление репозитория `CarRepository`

Обновите методы в репозитории `CarRepository`, если необходимо, для работы с новым полем `engineerId`.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ksergey.ContactsApp.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByEngineerId(Long engineerId);
}
```

## Шаг 4: Обновление контроллера `CarController`

Обновите методы контроллера, чтобы они работали с `engineerId`. Возможно, вам потребуется изменить логику поиска и обновления.

```java
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
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setCityOfManufacture(carDetails.getCityOfManufacture());
        car.setEngineerId(carDetails.getEngineerId());
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

    @GetMapping("/search")
    public ResponseEntity<List<Car>> getCarsByEngineerId(@RequestParam Long engineerId) {
        List<Car> cars = carRepository.findByEngineerId(engineerId);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
}
```

## Шаг 5: Создание репозитория `EngineerRepository`

Создайте новый репозиторий для работы с инженерами, если необходимо.

```java
import org.springframework.data.jpa.repository.JpaRepository;
import ru.ksergey.ContactsApp.model.Engineer;

public interface EngineerRepository extends JpaRepository<Engineer, Long> {
}
```

[Далее](./step-14theory.md)
