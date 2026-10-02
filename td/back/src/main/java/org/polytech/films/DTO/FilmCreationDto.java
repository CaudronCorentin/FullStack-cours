package org.polytech.films.DTO;

import org.polytech.films.model.Genre;

import java.time.LocalDate;

public record FilmCreationDto(
        String titre,
        String realisateur,
        LocalDate dateSortie,
        Genre genre) { }
