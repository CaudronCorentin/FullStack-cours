package org.polytech.films.service;

import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.model.Film;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    public Film getFilm(Long id) {
        Film film = filmRepository.findById(id);
        if (film == null) {
            throw new FilmNotFoundException(id);
        }
        return film;
    }
}