package org.polytech.films.repository;

import org.polytech.films.model.Film;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepository {

    private final List<Film> films = new ArrayList<>();
    private long id_prochain = 0;

    public List<Film> findAll() {
        return films;
    }

    public Film findById(Long id) {
        for (Film film : films) {
            if (film.getId().equals(id)) {
                return film;
            }
        }
        return null;
    }

    public Film save(Film film) {
        film.setId(++id_prochain);
        films.add(film);
        return film;
    }
}