package org.polytech.films.DTO;

import org.polytech.films.model.Genre;

import java.time.LocalDate;

public record FilmDto(
        Long id,
        String titre,
        String realisateur,
        LocalDate dateSortie,
        Genre genre) { }
