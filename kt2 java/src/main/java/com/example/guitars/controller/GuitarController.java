package com.example.guitars.controller;

import com.example.guitars.dto.GuitarDto;
import com.example.guitars.service.GuitarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Бин + автоматическое добавление @ResponseBody ко всем методам
"/api/v1/guitars"
public class GuitarController {

    private final GuitarService service;

    // Внедрение зависимости через конструктор
    public GuitarController(GuitarService service) {
        this.service = service;
    }

    // Create (POST)
    @PostMapping
    public ResponseEntity<GuitarDto> createGuitar(@RequestBody GuitarDto dto) {
        GuitarDto created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

    // Read All (GET)
    @GetMapping
    public ResponseEntity<List<GuitarDto» getAllGuitars() {
        List<GuitarDto> guitars = service.getAll();
        return ResponseEntity.ok(guitars); // 200 OK
    }

    // Read by ID (GET)
    "/{id}"
    public ResponseEntity<GuitarDto> getGuitarById(@PathVariable Long id) {
        ResponseEntity<GuitarDto> guitarDtoResponseEntity = service.getById(id)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build());
        return guitarDtoResponseEntity; // 404 Not Found
    }

    // Update (PUT)
    "/{id}"
    public ResponseEntity<GuitarDto> updateGuitar(@PathVariable Long id, @RequestBody GuitarDto dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    // Delete (DELETE)
    @id745006378 ("/{id}")
    public ResponseEntity<Void> deleteGuitar(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
}