package org.polytech.films.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titre;

    private String realisateur;
    private LocalDate dateSortie;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany
    @JoinTable(name="film_acteur", joinColumns = @JoinColumn(name="id_film"),inverseJoinColumns = @JoinColumn(name="id_acteur"))
    private Set<Acteur> acteurs = new HashSet<>();

    public Film() {}

    public Film(Long id, String titre, String realisateur, LocalDate dateSortie, Genre genre) {
        this.id = id;
        this.titre = titre;
        this.realisateur = realisateur;
        this.dateSortie = dateSortie;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }
}
