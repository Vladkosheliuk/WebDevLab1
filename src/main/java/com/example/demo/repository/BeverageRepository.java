package com.example.demo.repository;

import com.example.demo.model.Beverage;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BeverageRepository {

    private final Map<Long, Beverage> storage = new HashMap<>();
    private long idCounter = 1;

    public List<Beverage> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Beverage> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public Beverage save(Beverage beverage) {
        beverage.setId(idCounter++);
        storage.put(beverage.getId(), beverage);
        return beverage;
    }

    public Beverage update(Long id, Beverage beverage) {
        beverage.setId(id);
        storage.put(id, beverage);
        return beverage;
    }

    public void delete(Long id) {
        storage.remove(id);
    }

    public boolean existsByNameAndType(String name, String type) {
        return storage.values().stream()
                .anyMatch(b -> b.getName().equalsIgnoreCase(name) &&
                        b.getType().equalsIgnoreCase(type));
    }

    public boolean exists(Long id) {
        return storage.containsKey(id);
    }
}
