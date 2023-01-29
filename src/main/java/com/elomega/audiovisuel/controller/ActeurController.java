package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.service.acteur_service.ActeurService;
import com.elomega.audiovisuel.service.film_service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ActeurController {
    private final ActeurService acteurService;

    //  ENDPOINTS ACTEURS
    @GetMapping("/acteurs")
    public ResponseEntity<Response> getActeur(@Param("page") int page, @Param("size") int size) {
        Page<Acteur> acteurs = acteurService.getActeur(page,size);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Liste des acteurs Obtenu")
                        .data(Map.of("acteurs",acteurs))
                        .build()
        );
    }

    @GetMapping("/acteurs/{id}")
    public ResponseEntity<Response> getActeurById(@PathVariable Long id) {
        Acteur acteur = acteurService.getActeurById(id);
        if(acteur != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Obtention de l'acteur " + id)
                            .data(Map.of("acteur",acteur))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("l'acteur " + id + "n'existe pas!")
                            .build()
            );
        }
    }

    @PostMapping("/acteurs")
    public ResponseEntity<Response> postActeur(@RequestBody Acteur acteur) {
        Acteur acteurSave = acteurService.postActeur(acteur);
        if (acteurSave != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .message("creation reussie")
                            .data(Map.of("acteur",acteurSave))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("Il ya une erreur interne du serveur , esseyer plustard")
                            .build()
            );
        }
    }

    @DeleteMapping("/acteurs/{id}")
    public ResponseEntity<Response> deleteActeurById(@PathVariable Long id) {
        boolean isDeleted = acteurService.deleteActeurById(id);
        if (isDeleted){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("acteur " + id + " a ete supprimer avec success")
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("l'acteur que  vous esseyer de supprimer n'existe pas")
                            .build()
            );
        }
    }

    @GetMapping("/acteurs/{id}/films")
    public ResponseEntity<Response> getAllFilmsOfActeur(@PathVariable("id") Long idActeur) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("l'ensemble des films de cette acteur " + idActeur)
                        .data(Map.of("films",acteurService.getAllFilmsOfActeur(idActeur)))
                        .build()
        );
    }

    @GetMapping("/acteurs/{id_acteur}/films/{id_film}")
    public ResponseEntity<Response> getOneFilmOfActeur(@PathVariable("id_acteur") Long idActeur,@PathVariable("id_film") Long idFilm) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("films " +idFilm+ " de l'acteur " +idActeur)
                        .data(Map.of("film",acteurService.getOneFilmOfActeur(idActeur,idFilm)))
                        .build()
        );
    }

    @PutMapping("/acteurs/{id}")
    public ResponseEntity<Acteur> updateActeur(@PathVariable Long id,@RequestBody Acteur acteur) {
        Optional<Acteur> acteurExistant = acteurService.updateActeur(id,acteur);
        return acteurExistant.map(acteur1->new ResponseEntity<>(acteur1, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
