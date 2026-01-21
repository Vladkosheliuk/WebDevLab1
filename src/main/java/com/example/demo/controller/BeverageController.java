package com.example.demo.controller;

import com.example.demo.DTO.BeverageDTO;
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

    // GET /beverages?type=coffee
    @GetMapping
    public List<Beverage> getAll(@RequestParam(required = false) String type) {
        return service.getAll(type);
    }

    // GET /beverages/{id}
    @GetMapping("/{id}")
    public Beverage getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // POST /beverages
    @PostMapping
    public Beverage create(
            @Valid @RequestBody BeverageDTO dto,
            @RequestHeader("X-Client-Name") String clientName) {

        System.out.println("Request from: " + clientName);
        return service.create(dto);
    }

    // PUT /beverages/{id}
    @PutMapping("/{id}")
    public Beverage update(
            @PathVariable Long id,
            @Valid @RequestBody BeverageDTO dto) {
        return service.update(id, dto);
    }

    // DELETE /beverages/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
