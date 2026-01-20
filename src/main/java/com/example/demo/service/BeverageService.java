package com.example.demo.service;

import com.example.demo.model.Beverage;
import com.example.demo.repository.BeverageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageService {

    private final BeverageRepository repository;

    public BeverageService(BeverageRepository repository) {
        this.repository = repository;
    }

    public List<Beverage> getAll(String type) {
        if (type == null) {
            return repository.findAll();
        }

        return repository.findAll()
                .stream()
                .filter(b -> b.getType().equalsIgnoreCase(type))
                .toList();
    }

    public Beverage getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beverage not found"));
    }

    public Beverage create(Beverage beverage) {
        return repository.save(beverage);
    }

    public Beverage update(Long id, Beverage beverage) {
        return repository.update(id, beverage);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
