package com.example.demo.service;

import com.example.demo.DTO.BeverageDTO;
import com.example.demo.exception.BeverageAlreadyExistsException;
import com.example.demo.exception.BeverageNotFoundException;
import com.example.demo.exception.RandomDeleteException;
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
        List<Beverage> list = repository.findAll();
        if (type != null) {
            list = list.stream()
                    .filter(b -> b.getType().equalsIgnoreCase(type))
                    .toList();
        }
        return list; // если пусто, вернется []
    }

    public Beverage getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BeverageNotFoundException("Beverage not found"));
    }

    public Beverage create(BeverageDTO dto) {
        // Проверяем, существует ли уже напиток с таким именем
        boolean exists = repository.findAll().stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(dto.getName()));
        if (exists) {
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
        Beverage existing = repository.findById(id)
                .orElseThrow(() -> new BeverageNotFoundException("Beverage not found"));

        existing.setName(dto.getName());
        existing.setType(dto.getType());
        existing.setPrice(dto.getPrice());
        existing.setSize(dto.getSize());
        existing.setAvailable(dto.getAvailable());

        return repository.update(id, existing);
    }

    public void delete(Long id) {
        if (!repository.findById(id).isPresent()) {
            throw new BeverageNotFoundException("Beverage not found");
        }

        // 50% шанс выбросить RandomDeleteException
        if (Math.random() < 0.5) {
            throw new RandomDeleteException("Random failure on delete");
        }

        repository.delete(id);
    }
}
