package org.polytech.films.exception;

public class ActeurNotFoundException extends RuntimeException{
    public ActeurNotFoundException(Long id) {
        super("acteur " + id + " introuvable");
    }
}
