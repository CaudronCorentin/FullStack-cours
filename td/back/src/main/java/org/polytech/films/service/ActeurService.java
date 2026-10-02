package org.polytech.films.service;

import org.polytech.films.DTO.FilmDto;
import org.polytech.films.mapper.FilmMapper;
import org.polytech.films.repository.FilmRepository;
import org.springframework.transaction.annotation.Transactional;
import org.polytech.films.DTO.ActeurCreationDto;
import org.polytech.films.DTO.ActeurDto;
import org.polytech.films.exception.ActeurNotFoundException;
import org.polytech.films.mapper.ActeurMapper;
import org.polytech.films.model.Acteur;
import org.polytech.films.model.Film;
import org.polytech.films.repository.ActeurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActeurService {


    private final ActeurRepository acteurRepository;
    private final FilmRepository filmRepository;

    public ActeurService(ActeurRepository acteurRepository, FilmRepository filmRepository) {
        this.acteurRepository = acteurRepository;
        this.filmRepository = filmRepository;
    }

    public List<ActeurDto> getActeurs() {
        return acteurRepository.findAll().stream().map(ActeurMapper::toDto).toList();
    }

    public ActeurDto getActeur(Long id) {
        return ActeurMapper.toDto(trouverActeur(id));
    }

    public ActeurDto createActeur(ActeurCreationDto dto) {
        Acteur acteur = acteurRepository.save(ActeurMapper.toEntity(dto));
        return ActeurMapper.toDto(acteur);
    }

    public ActeurDto updateActeur(Long id, ActeurCreationDto dto) {
        Acteur acteur_actuel = trouverActeur(id);
        acteur_actuel.setNom(dto.nom());
        acteur_actuel.setPrenom(dto.prenom());
        return ActeurMapper.toDto(acteurRepository.save(acteur_actuel));
    }

    @Transactional //à cause de la relation fait sur les Films
    public void deleteActeur(Long id) {
        Acteur acteur = trouverActeur(id);
        for (Film film : acteur.getFilms()) {
            film.getActeurs().remove(acteur);
        }
        acteurRepository.delete(acteur);
    }

    private Acteur trouverActeur(Long id) {
        return acteurRepository.findById(id).orElseThrow(() -> new ActeurNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<FilmDto> getFilmsDeActeur(Long id) {
        trouverActeur(id);
        return filmRepository.findFilmsDeActeur(id).stream().map(FilmMapper::toDto).toList();
    }

}
