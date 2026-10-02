package org.polytech.films.repository;

import org.polytech.films.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByActeursId(Long acteurId);

    @Query("""
        select f from Film f join f.acteurs a where a.id = :acteurId""")
    List<Film> findFilmsDeActeur(@Param("acteurId") Long acteurId);
}