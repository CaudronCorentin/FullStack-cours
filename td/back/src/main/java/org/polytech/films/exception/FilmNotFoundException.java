package org.polytech.films.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(Long id) {
        super("film " + id + " introuvable");
    }
}