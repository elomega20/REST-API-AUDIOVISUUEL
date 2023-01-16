package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import com.elomega.audiovisuel.service.maison_de_production_service.MaisonDeProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
public class MaisonDeProductionController {
    private final MaisonDeProductionService maisonDeProductionService;

    @GetMapping("/maison_de_productions")
    public ResponseEntity<Response> getMaisonDeproduction(@Param("page") int page, @Param("size") int size) {
        return ResponseEntity.ok(
             Response.builder()
                     .timeStamp(LocalDateTime.now())
                     .status(HttpStatus.OK)
                     .statusCode(HttpStatus.OK.value())
                     .message("liste des maisons de production obtenu")
                     .data(Map.of("maison_de_productions",maisonDeProductionService.getMaisonDeproduction(page,size)))
                     .build()
        );
    }

    @GetMapping("/maison_de_productions/{id}")
    public ResponseEntity<Response> getMaisonDeProducrionById(@PathVariable Long id) {
        MaisonDeProduction maisonDeProduction = maisonDeProductionService.getMaisonDeProducrionById(id);
        if (maisonDeProduction != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("maison de production d'identifiant "+id)
                            .data(Map.of("maison_de_production",maisonDeProduction))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("La maison de production "+id+" n'existe pas")
                            .build()
            );
        }
    }

    @PostMapping("/maison_de_productions")
    public ResponseEntity<Response> postMaisonDeProduction(@RequestBody MaisonDeProduction maisonDeProduction) {
        MaisonDeProduction maisonDeProductionSave = maisonDeProductionService.postMaisonDeProduction(maisonDeProduction);
        if (maisonDeProductionSave != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("maison de production "+maisonDeProduction.getMaisonDeProductionId()+" ajouter avec success")
                            .data(Map.of("maison_de_production",maisonDeProductionSave))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("une erreur interne s'est produit")
                            .build()
            );
        }
    }

    @DeleteMapping("/maison_de_producrions/{id}")
    public ResponseEntity<Response> deleteMaisonDeProductionById(@PathVariable Long id) {
        boolean isDeleted = maisonDeProductionService.deleteMaisonDeProductionById(id);
        if (isDeleted){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("maison de production "+id+" supprimer avec success")
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("impossible de supprimer la maison de production "+id+" car elle n'existe pas")
                            .build()
            );
        }
    }

    @PutMapping("/maison_de_productions/{id}")
    public ResponseEntity<Response> updateMaisonDeProduction(@PathVariable Long id,@RequestBody MaisonDeProduction maisonDeProduction) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("maison de production "+maisonDeProduction.getMaisonDeProductionId()+" mise a jour avec success")
                        .data(Map.of("maison_de_production",maisonDeProductionService.updateMaisonDeProduction(id,maisonDeProduction)))
                        .build()
        );
    }

    @GetMapping("/maison_de_productions/{id}/films")
    public ResponseEntity<Response> getAllFilmsOfMaisonDeProduction(@PathVariable("id") Long idMaisonDeproduction) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("tout les films de la maison de production "+idMaisonDeproduction)
                        .data(Map.of("films",maisonDeProductionService.getAllFilmsOfMaisonDeProduction(idMaisonDeproduction)))
                        .build()
        );
    }

    @GetMapping("/maison_de_productions/{idmp}/films/{idFilm}")
    public ResponseEntity<Response> getOneFilmOfMaisonDeProduction(@PathVariable("idmp") Long idMaisonDeProduction,@PathVariable("idFilm") Long idFilm) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("films "+idFilm+" de la maison de production "+idMaisonDeProduction)
                        .data(Map.of("film",maisonDeProductionService.getOneFilmOfMaisonDeProduction(idMaisonDeProduction,idFilm)))
                        .build()
        );
    }
}
