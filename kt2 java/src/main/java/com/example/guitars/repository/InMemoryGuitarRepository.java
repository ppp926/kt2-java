
package com.example.guitars.repository;

import com.example.guitars.entity.Guitar;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository // Делаем класс бином
public class InMemoryGuitarRepository implements GuitarRepository {
    private final Map<Long, Guitar> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @id86240433 (@Override)
    public Guitar save(Guitar guitar) {
        long id = idGenerator.getAndIncrement();
        guitar.setId(id);
        storage.put(id, guitar);
        return guitar;
    }

    @id86240433 (@Override)
    public Optional<Guitar> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @id86240433 (@Override)
    public List<Guitar> findAll() {
        return new ArrayList<>(storage.values());
    }

    @id86240433 (@Override)
    public Guitar update(Guitar guitar) {
        if (storage.containsKey(guitar.getId())) {
            storage.put(guitar.getId(), guitar);
            return guitar;
        }
        return null; // Или можно выбрасывать кастомный Exception
    }

    @id86240433 (@Override)
    public boolean deleteById(Long id) {
        return storage.remove(id) != null;
    }
}