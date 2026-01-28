package com.example.demo.service;

import com.example.demo.DTO.BeverageDTO;
import com.example.demo.exception.*;
import com.example.demo.model.Beverage;
import com.example.demo.repository.BeverageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BeverageService {

    private final BeverageRepository repository;
    private final Random random = new Random();

    public BeverageService(BeverageRepository repository) {
        this.repository = repository;
    }

    public List<Beverage> getAll(String type) {
        List<Beverage> all = repository.findAll();
        if (type == null) return all;
        return all.stream()
                .filter(b -> b.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public Beverage getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BeverageNotFoundException(id));
    }

    public Beverage create(BeverageDTO dto) {
        if (repository.existsByNameAndType(dto.getName(), dto.getType())) {
            throw new BeverageAlreadyExistsException("Beverage already exists");
        }

        Beverage beverage = new Beverage();
        beverage.setName(dto.getName());
        beverage.setType(dto.getType());
        beverage.setPrice(dto.getPrice());
        beverage.setSize(dto.getSize());
        beverage.setAvailable(dto.getAvailable());

        return repository.save(beverage);
    }

    public Beverage update(Long id, BeverageDTO dto) {
        Beverage beverage = getById(id);
        beverage.setName(dto.getName());
        beverage.setType(dto.getType());
        beverage.setPrice(dto.getPrice());
        beverage.setSize(dto.getSize());
        beverage.setAvailable(dto.getAvailable());
        return repository.update(id, beverage);
    }

    public void delete(Long id) {
        if (!repository.exists(id)) {
            throw new BeverageNotFoundException(id);
        }

        if (random.nextBoolean()) {
            throw new RandomDeleteException("Random failure on delete");
        }

        repository.delete(id);
    }
}
