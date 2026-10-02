package org.polytech.films.service;

import org.polytech.films.DTO.FilmCreationDto;
import org.polytech.films.DTO.FilmDto;
import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.mapper.FilmMapper;
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

    public List<FilmDto> getFilms() {
        return filmRepository.findAll().stream().map(FilmMapper::toDto).toList();
    }

    public FilmDto getFilm(Long id) {
        return FilmMapper.toDto(trouverFilm(id));
    }

    public FilmDto createFilm(FilmCreationDto dto) {
        Film film = filmRepository.save(FilmMapper.toEntity(dto));
        return FilmMapper.toDto(film);
    }

    public FilmDto updateFilm(Long id, FilmCreationDto dto) {
        Film film_actuel = trouverFilm(id);
        film_actuel.setTitre(dto.titre());
        film_actuel.setRealisateur(dto.realisateur());
        film_actuel.setDateSortie(dto.dateSortie());
        film_actuel.setGenre(dto.genre());
        return FilmMapper.toDto(filmRepository.save(film_actuel));
    }

    public void deleteFilm(Long id) {
        filmRepository.delete(trouverFilm(id));
    }

    private Film trouverFilm(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new FilmNotFoundException(id));
    }

}