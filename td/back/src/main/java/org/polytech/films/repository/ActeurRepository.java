package org.polytech.films.repository;

import org.polytech.films.model.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {
}
