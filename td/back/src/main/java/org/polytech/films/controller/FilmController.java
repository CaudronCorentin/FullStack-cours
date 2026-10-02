package org.polytech.films.controller;

import org.polytech.films.DTO.ActeurDto;
import org.polytech.films.DTO.FilmCreationDto;
import org.polytech.films.DTO.FilmDetailDto;
import org.polytech.films.DTO.FilmDto;
import org.polytech.films.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<FilmDto> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    public FilmDetailDto getFilm(@PathVariable Long id) {
        return filmService.getFilm(id);
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmCreationDto filmCreationDto) {
        FilmDto film_cree = filmService.createFilm(filmCreationDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(film_cree.id())
                .toUri();
        return ResponseEntity.created(location).body(film_cree);
    }
    @PutMapping("/{id}")
    public FilmDto updateFilm(@PathVariable Long id,@RequestBody FilmCreationDto filmCreationDto) {
        return filmService.updateFilm(id, filmCreationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurDto> getActeursDuFilm(@PathVariable Long id) {
        return filmService.getActeursDuFilm(id);
    }

    @PostMapping("/{id}/acteurs/{acteurId}")
    public FilmDetailDto ajouterActeur(@PathVariable Long id, @PathVariable Long acteurId) {
        return filmService.ajouterActeur(id, acteurId);
    }

    @DeleteMapping("/{id}/acteurs/{acteurId}")
    public ResponseEntity<Void> retirerActeur(@PathVariable Long id, @PathVariable Long acteurId) {
        filmService.retirerActeur(id, acteurId);
        return ResponseEntity.noContent().build();
    }
}