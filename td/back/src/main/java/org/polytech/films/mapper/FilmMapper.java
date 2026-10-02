package org.polytech.films.mapper;

import org.polytech.films.DTO.FilmCreationDto;
import org.polytech.films.DTO.FilmDto;
import org.polytech.films.model.Film;

public class FilmMapper {

    public static FilmDto toDto(Film f) {
        return new FilmDto(f.getId(), f.getTitre(), f.getRealisateur(),
                f.getDateSortie(), f.getGenre());
    }

    public static Film toEntity(FilmCreationDto d) {
        Film f = new Film();
        f.setTitre(d.titre());
        f.setRealisateur(d.realisateur());
        f.setDateSortie(d.dateSortie());
        f.setGenre(d.genre());
        return f;
    }

}
