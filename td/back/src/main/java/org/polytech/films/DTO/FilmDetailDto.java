package org.polytech.films.DTO;

import org.polytech.films.model.Genre;

import java.time.LocalDate;
import java.util.List;

public record FilmDetailDto(Long id, String titre, String realisateur,
                            LocalDate dateSortie, Genre genre, List<ActeurDto> acteurs) {
}