package org.polytech.films.service;

import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }
}
