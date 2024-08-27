package ru.ksergey.ContactsApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ksergey.ContactsApp.exception.ResourceNotFoundException;
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
        //         Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car not found"));
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

    @GetMapping("/search")
    public ResponseEntity<List<Car>> getCarsByEngineerName(@RequestParam String engineerName) {
        List<Car> cars = carRepository.findByEngineerName(engineerName);
        if (cars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

}
