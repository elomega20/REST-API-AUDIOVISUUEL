package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.MaisonDeProductionRequest;
import com.elomega.audiovisuel.dto.MaisonDeProductionResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.service.MaisonDeProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MaisonDeProductionController {
    private final MaisonDeProductionService maisonDeProductionService;

    @GetMapping("/maison-de-productions")
    public ResponseEntity<Page<MaisonDeProductionResponse>> getMaisonDeproduction(@Param("page") int page, @Param("size") int size) {
        return new ResponseEntity<>(maisonDeProductionService.getMaisonDeproduction(page,size),HttpStatus.OK);
    }

    @GetMapping("/maison-de-productions/{id}")
    public ResponseEntity<MaisonDeProductionResponse> getMaisonDeProducrionById(@PathVariable Long id) {
        return maisonDeProductionService.getMaisonDeProducrionById(id)
                .map(maisonDeProduction -> new ResponseEntity<>(maisonDeProduction,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/maison-de-productions")
    public ResponseEntity<MaisonDeProductionResponse> postMaisonDeProduction(@RequestBody MaisonDeProductionRequest maisonDeProductionRequest) {
        return maisonDeProductionService.postMaisonDeProduction(maisonDeProductionRequest)
                .map(maisonDeProduction1 -> new ResponseEntity<>(maisonDeProduction1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/maison-de-productions/{id}")
    public ResponseEntity<HttpStatus> deleteMaisonDeProductionById(@PathVariable Long id) {
        return maisonDeProductionService.deleteMaisonDeProductionById(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/maison-de-productions")
    public ResponseEntity<MaisonDeProductionResponse> updateMaisonDeProduction(@RequestBody MaisonDeProductionResponse maisonDeProductionResponse) {
        return maisonDeProductionService.updateMaisonDeProduction(maisonDeProductionResponse)
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
    @PostMapping("/maison-de-productions/{idmp}/films")
    public ResponseEntity<Film> postOneFilmForMaisonDeProduction(@PathVariable("idmp") Long idMaisonDeProduction,@RequestBody Film film){
        return maisonDeProductionService.postOneFilmForMaisonDeProduction(idMaisonDeProduction,film)
                .map(film1 -> new ResponseEntity<>(film1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @PostMapping("/maison-de-productions/{idmp}/films/{idFilm}/acteurs")
    public ResponseEntity<List<ActeurResponse>> associatActeursAndFilm(@PathVariable("idmp") Long idMaisonDeProduction,@PathVariable("idFilm") Long idFilm,@RequestBody List<ActeurResponse> acteurResponses){
        return maisonDeProductionService.associatActeursAndFilm(idMaisonDeProduction,idFilm,acteurResponses)
                .map(acteurs1 -> new ResponseEntity<>(acteurs1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
