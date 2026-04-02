package com.example.guitars.service;

import com.example.guitars.dto.GuitarDto;
import com.example.guitars.entity.Guitar;
import com.example.guitars.repository.GuitarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Делаем класс бином
public class GuitarService {

    // SLF4J Логгер
    private static final Logger log = LoggerFactory.getLogger(GuitarService.class);

    private final GuitarRepository repository;

    // Внедрение зависимости через конструктор (требование выполнено)
    public GuitarService(GuitarRepository repository) {
        this.repository = repository;
    }

    public GuitarDto create(GuitarDto dto) {
        log.debug("Creating new guitar: {} {}", dto.getBrand(), dto.getModel());
        Guitar entity = new Guitar(null, dto.getBrand(), dto.getModel(), dto.getStringsCount());
        Guitar saved = repository.save(entity);
        return convertToDto(saved);
    }

    public List<GuitarDto> getAll() {
        log.debug("Fetching all guitars");
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<GuitarDto> getById(Long id) {
        log.debug("Fetching guitar by id: {}", id);
        return repository.findById(id).map(this::convertToDto);
    }

    public Optional<GuitarDto> update(Long id, GuitarDto dto) {
        log.debug("Updating guitar with id: {}", id);
        Optional<Guitar> existing = repository.findById(id);
        if (existing.isPresent()) {
            Guitar updatedEntity = new Guitar(id, dto.getBrand(), dto.getModel(), dto.getStringsCount());
            repository.update(updatedEntity);
            return Optional.of(convertToDto(updatedEntity));
        }
        return Optional.empty();
    }

    public boolean delete(Long id) {
        log.debug("Deleting guitar with id: {}", id);
        return repository.deleteById(id);
    }

    // Простой ручной маппер Entity -> DTO
    private GuitarDto convertToDto(Guitar guitar) {
        return new GuitarDto(guitar.getId(), guitar.getBrand(), guitar.getModel(), guitar.getStringsCount());
    }
}