package org.polytech.films.mapper;

import org.polytech.films.DTO.ActeurCreationDto;
import org.polytech.films.DTO.ActeurDto;
import org.polytech.films.model.Acteur;

public final class ActeurMapper {

    public static ActeurDto toDto(Acteur a) {
        return new ActeurDto(a.getId(), a.getNom(), a.getPrenom());
    }

    public static Acteur toEntity(ActeurCreationDto d) {
        Acteur a = new Acteur();
        a.setNom(d.nom());
        a.setPrenom(d.prenom());
        return a;
    }
}
