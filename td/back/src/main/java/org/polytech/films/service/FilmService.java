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
        Film film = filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException(id));
        return film;
    }
    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilm(Long id, Film film) {
        Film film_actuel = getFilm(id);
        film_actuel.setTitre(film.getTitre());
        film_actuel.setRealisateur(film.getRealisateur());
        film_actuel.setDateSortie(film.getDateSortie());
        film_actuel.setGenre(film.getGenre());
        return filmRepository.save(film_actuel);
    }

    public void deleteFilm(Long id) {
        Film film = getFilm(id);
        filmRepository.delete(film);
    }

}