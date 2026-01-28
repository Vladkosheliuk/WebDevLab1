package com.example.demo.controller;

import com.example.demo.DTO.BeverageDTO;
import com.example.demo.exception.RandomDeleteException;
import com.example.demo.model.Beverage;
import com.example.demo.service.BeverageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beverages")
public class BeverageController {

    private final BeverageService service;

    public BeverageController(BeverageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beverage> getAll(@RequestParam(name = "type", required = false) String type) {
        return service.getAll(type);
    }

    @GetMapping("/{id}")
    public Beverage getById(@PathVariable(name = "id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Beverage create(@Valid @RequestBody BeverageDTO dto,
                           @RequestHeader("X-Client-Name") String client) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Beverage update(@PathVariable(name = "id") Long id, @Valid @RequestBody BeverageDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @ExceptionHandler(RandomDeleteException.class)
    public String handleRandomDelete(RandomDeleteException ex) {
        return ex.getMessage();
    }
}
