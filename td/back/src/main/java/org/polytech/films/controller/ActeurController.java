package org.polytech.films.controller;

import org.polytech.films.DTO.ActeurCreationDto;
import org.polytech.films.DTO.ActeurDto;
import org.polytech.films.service.ActeurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {

    private final ActeurService acteurService;

    public ActeurController(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    @GetMapping
    public List<ActeurDto> getActeurs() {
        return acteurService.getActeurs();
    }

    @GetMapping("/{id}")
    public ActeurDto getActeur(@PathVariable Long id) {
        return acteurService.getActeur(id);
    }

    @PostMapping
    public ResponseEntity<ActeurDto> createActeur(@RequestBody ActeurCreationDto acteurCreationDto) {
        ActeurDto acteur_cree = acteurService.createActeur(acteurCreationDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(acteur_cree.id())
                .toUri();
        return ResponseEntity.created(location).body(acteur_cree);
    }

    @PutMapping("/{id}")
    public ActeurDto updateActeur(@PathVariable Long id, @RequestBody ActeurCreationDto acteurCreationDto) {
        return acteurService.updateActeur(id, acteurCreationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActeur(@PathVariable Long id) {
        acteurService.deleteActeur(id);
        return ResponseEntity.noContent().build();
    }
}