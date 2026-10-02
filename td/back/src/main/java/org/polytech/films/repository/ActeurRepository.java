package org.polytech.films.repository;

import org.polytech.films.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    List<Acteur> findByFilmsId(Long filmId);

    @Query(""" 
        select a from Acteur a join a.films f where f.id = :filmId""")
    List<Acteur> findActeursDuFilm(@Param("filmId") Long filmId);
}