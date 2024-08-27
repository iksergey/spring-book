package ru.ksergey.ContactsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ksergey.ContactsApp.model.Car;
import ru.ksergey.ContactsApp.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> updateCar(Long id, Car carDetails) {
        if (carRepository.existsById(id)) {
            carDetails.setId(id);
            return Optional.of(carRepository.save(carDetails));
        }
        return Optional.empty();
    }

    public boolean deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Car> getCarsByEngineerId(Long engineerId) {
        return carRepository.findByEngineerId(engineerId);
    }
}