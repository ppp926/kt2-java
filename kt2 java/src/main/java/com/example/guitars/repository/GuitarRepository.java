package com.example.guitars.repository;

import com.example.guitars.entity.Guitar;
import java.util.List;
import java.util.Optional;

public interface GuitarRepository {
    Guitar save(Guitar guitar);
    Optional<Guitar> findById(Long id);
    List<Guitar> findAll();
    Guitar update(Guitar guitar);
    boolean deleteById(Long id);
}