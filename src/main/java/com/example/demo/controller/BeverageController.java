package com.example.demo.controller;

import com.example.demo.model.Beverage;
import com.example.demo.service.BeverageService;
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
    public List<Beverage> getAll(
            @RequestParam(required = false) String type) {
        return service.getAll(type);
    }


    @GetMapping("/{id}")
    public Beverage getById(@PathVariable Long id) {
        return service.getById(id);
    }


    @PostMapping
    public Beverage create(
            @RequestBody Beverage beverage,
            @RequestHeader("X-Client-Name") String clientName) {

        System.out.println("Request from: " + clientName);
        return service.create(beverage);
    }


    @PutMapping("/{id}")
    public Beverage update(
            @PathVariable Long id,
            @RequestBody Beverage beverage) {
        return service.update(id, beverage);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
