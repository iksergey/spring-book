package ru.ksergey.ContactsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ksergey.ContactsApp.model.Engineer;
import ru.ksergey.ContactsApp.repository.EngineerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    public List<Engineer> getAllEngineers() {
        return engineerRepository.findAll();
    }

    public Engineer createEngineer(Engineer engineer) {
        return engineerRepository.save(engineer);
    }

    public Optional<Engineer> updateEngineer(Long id, Engineer engineerDetails) {
        if (engineerRepository.existsById(id)) {
            engineerDetails.setId(id);
            return Optional.of(engineerRepository.save(engineerDetails));
        }
        return Optional.empty();
    }

    public boolean deleteEngineer(Long id) {
        if (engineerRepository.existsById(id)) {
            engineerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}