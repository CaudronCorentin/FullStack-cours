package org.polytech.films.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToMany(mappedBy = "acteurs")
    private Set<Film> films;

    public Acteur() {}

    public Acteur(Long id, String nom, String prenom, Set<Film> films) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.films = films;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
