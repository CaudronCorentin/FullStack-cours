package org.polytech.films.service;

import org.polytech.films.DTO.FilmCreationDto;
import org.polytech.films.DTO.FilmDetailDto;
import org.polytech.films.DTO.FilmDto;
import org.polytech.films.exception.FilmNotFoundException;
import org.polytech.films.mapper.FilmMapper;
import org.polytech.films.model.Film;
import org.polytech.films.repository.ActeurRepository;
import org.polytech.films.repository.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;

    public FilmService(FilmRepository filmRepository, ActeurRepository acteurRepository) {
        this.filmRepository = filmRepository;
        this.acteurRepository = acteurRepository;
    }

    public List<FilmDto> getFilms() {
        return filmRepository.findAll().stream().map(FilmMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public FilmDetailDto getFilm(Long id) {
        return FilmMapper.toDetailDto(trouverFilm(id));
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