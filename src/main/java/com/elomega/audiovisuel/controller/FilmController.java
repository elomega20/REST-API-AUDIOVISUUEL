package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.service.film_service.FilmService;
import com.mysql.cj.x.protobuf.Mysqlx;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;
    // ENDPOINTS FILMS
    @GetMapping("/films")
    public ResponseEntity<Page<Film>> getFilm(@Param("page") int page, @Param("size") int size) {
        return new ResponseEntity<>(filmService.getFilm(page,size),HttpStatus.OK);
    }

    @GetMapping("/films/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id)
                .map(film -> new ResponseEntity<>(film,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/films")
    public ResponseEntity<Film> postFilm(@RequestBody Film film) {
        return filmService.postFilm(film)
                .map(film1 -> new ResponseEntity<>(film1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<HttpStatus> deleteFilmById(@PathVariable Long id) {
        return filmService.deleteFilmById(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/films/{id}/acteurs")
    public ResponseEntity<List<Acteur>> getAllActeursOfFilm(@PathVariable("id") Long idFilm) {
        return filmService.getAllActeursOfFilm(idFilm)
                .map(films -> new ResponseEntity<>(films,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/films/{id_film}/acteurs/{id_acteur}")
    public ResponseEntity<Acteur> getOneActeurOfFilm(@PathVariable("id_film") Long idFilm,@PathVariable("id_acteur") Long idActeur) {
        return filmService.getOneActeurOfFilm(idFilm,idActeur)
                .map(acteur -> new ResponseEntity<>(acteur,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/films")
    public ResponseEntity<Film> updateFilm(@RequestBody Film film) {
        return filmService.updateFilm(film)
                .map(film1 -> new ResponseEntity<>(film1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
