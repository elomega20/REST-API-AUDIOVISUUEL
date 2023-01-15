package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
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
public class FilmController {
    private final FilmService filmService;
    // ENDPOINTS FILMS
    @GetMapping("/films")
    public ResponseEntity<Response> getFilm(@Param("page") int page, @Param("size") int size) {
        Page<Film> films = filmService.getFilm(page,size);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("Liste des films Obtenu")
                        .data(Map.of("films",films))
                        .build()
        );
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Response> getFilmById(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        if(film != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Obtention du film " + id)
                            .data(Map.of("film",film))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("le film " + id + " n'existe pas!")
                            .build()
            );
        }
    }

    @PostMapping("/films")
    public ResponseEntity<Response> postFilm(@RequestBody Film film) {
        Film filmSave = filmService.postFilm(film);
        if (filmSave != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .message("creation reussie")
                            .data(Map.of("film",filmSave))
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

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Response> deleteFilmById(@PathVariable Long id) {
        boolean isDeleted = filmService.deleteFilmById(id);
        if (isDeleted){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("le film " + id + " a ete supprimer avec success")
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("le film que  vous esseyer de supprimer n'existe pas")
                            .build()
            );
        }
    }

    @GetMapping("/films/{id}/acteurs")
    public ResponseEntity<Response> getAllActeursOfFilm(@PathVariable("id") Long idFilm) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("l'ensemble des acteurs du film " + idFilm)
                        .data(Map.of("acteurs",filmService.getAllActeursOfFilm(idFilm)))
                        .build()
        );
    }

    @GetMapping("/films/{id_film}/acteurs/{id_acteur}")
    public ResponseEntity<Response> getOneActeurOfFilm(@PathVariable("id_film") Long idFilm,@PathVariable("id_acteur") Long idActeur) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("acteur " +idActeur+ " du film " +idFilm)
                        .data(Map.of("acteur",filmService.getOneActeurOfFilm(idFilm,idActeur)))
                        .build()
        );
    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Response> updateFilm(@PathVariable Long id,@RequestBody Film film) {
        Film filmExistant = filmService.updateFilm(id,film);
        if (filmExistant != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("le film " + id + " est mise a jour avec success!")
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("le film " + id + " n'existe pas , mise a jour echouer!")
                            .build()
            );
        }
    }
}
