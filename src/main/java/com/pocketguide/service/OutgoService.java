package com.pocketguide.service;

import com.pocketguide.exception.OutgoNotFoundException;
import com.pocketguide.model.Outgo;
import com.pocketguide.repository.OutgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutgoService {

    private OutgoRepository outgoRepository;

    @Autowired
    public OutgoService(OutgoRepository outgoRepository) {
        this.outgoRepository = outgoRepository;
    }

    public Outgo save(Outgo outgo) {
        return outgoRepository.save(outgo);
    }

    public List<Outgo> listAll() {
        return outgoRepository.findAll();
    }

    public Outgo findById(Long id) throws OutgoNotFoundException {
        Optional<Outgo> spent = outgoRepository.findById(id);
        if (!spent.isPresent()) {
            throw new OutgoNotFoundException("Spent with id " + id + " was not found");
        }

        return spent.get();
    }

    public void deleteById(Long id) throws OutgoNotFoundException {
        try {
            outgoRepository.deleteById(id);
        } catch (Exception e) {
            throw new OutgoNotFoundException("Spent with id " + id + " was not found");
        }
    }
}
