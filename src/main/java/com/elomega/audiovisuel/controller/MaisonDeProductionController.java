package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import com.elomega.audiovisuel.service.maison_de_production_service.MaisonDeProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MaisonDeProductionController {
    private final MaisonDeProductionService maisonDeProductionService;

    @GetMapping("/maison-de-productions")
    public ResponseEntity<Page<MaisonDeProduction>> getMaisonDeproduction(@Param("page") int page, @Param("size") int size) {
        return new ResponseEntity<>(maisonDeProductionService.getMaisonDeproduction(page,size),HttpStatus.OK);
    }

    @GetMapping("/maison-de-productions/{id}")
    public ResponseEntity<MaisonDeProduction> getMaisonDeProducrionById(@PathVariable Long id) {
        return maisonDeProductionService.getMaisonDeProducrionById(id)
                .map(maisonDeProduction -> new ResponseEntity<>(maisonDeProduction,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/maison-de-productions")
    public ResponseEntity<MaisonDeProduction> postMaisonDeProduction(@RequestBody MaisonDeProduction maisonDeProduction) {
        return maisonDeProductionService.postMaisonDeProduction(maisonDeProduction)
                .map(maisonDeProduction1 -> new ResponseEntity<>(maisonDeProduction1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/maison-de-producrions/{id}")
    public ResponseEntity<HttpStatus> deleteMaisonDeProductionById(@PathVariable Long id) {
        return maisonDeProductionService.deleteMaisonDeProductionById(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/maison-de-productions/{id}")
    public ResponseEntity<MaisonDeProduction> updateMaisonDeProduction(@RequestBody MaisonDeProduction maisonDeProduction) {
        return maisonDeProductionService.updateMaisonDeProduction(maisonDeProduction)
                .map(maisonDeProduction1 -> new ResponseEntity<>(maisonDeProduction1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/maison-de-productions/{id}/films")
    public ResponseEntity<List<Film>> getAllFilmsOfMaisonDeProduction(@PathVariable("id") Long idMaisonDeproduction) {
        return maisonDeProductionService.getAllFilmsOfMaisonDeProduction(idMaisonDeproduction)
                .map(films -> new ResponseEntity<>(films,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/maison-de-productions/{idmp}/films/{idFilm}")
    public ResponseEntity<Film> getOneFilmOfMaisonDeProduction(@PathVariable("idmp") Long idMaisonDeProduction,@PathVariable("idFilm") Long idFilm) {
        return maisonDeProductionService.getOneFilmOfMaisonDeProduction(idMaisonDeProduction,idFilm)
                .map(film -> new ResponseEntity<>(film,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
