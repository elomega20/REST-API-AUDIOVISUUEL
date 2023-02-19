package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.service.acteur_service.ActeurService;
import com.elomega.audiovisuel.service.film_service.FilmService;
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
public class ActeurController {
    private final ActeurService acteurService;

    //  ENDPOINTS ACTEURS
    @GetMapping("/acteurs")
    public ResponseEntity<Page<Acteur>> getActeur(@Param("page") int page, @Param("size") int size) {
        return new ResponseEntity<>(acteurService.getActeur(page,size),HttpStatus.OK);
    }

    @GetMapping("/acteurs/{id}")
    public ResponseEntity<Acteur> getActeurById(@PathVariable Long id) {
        return acteurService.getActeurById(id)
                .map(acteur -> new ResponseEntity<>(acteur,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/acteurs")
    public ResponseEntity<Acteur> postActeur(@RequestBody Acteur acteur) {
        return acteurService.postActeur(acteur)
                .map(acteurSave -> new ResponseEntity<>(acteurSave,HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/acteurs/{id}")
    public ResponseEntity<HttpStatus> deleteActeurById(@PathVariable Long id) {
        return acteurService.deleteActeurById(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/acteurs/{id}/films")
    public ResponseEntity<List<Film>> getAllFilmsOfActeur(@PathVariable("id") Long idActeur) {
        return acteurService.getAllFilmsOfActeur(idActeur)
                .map(films -> new ResponseEntity<>(films,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/acteurs/{id_acteur}/films/{id_film}")
    public ResponseEntity<Film> getOneFilmOfActeur(@PathVariable("id_acteur") Long idActeur,@PathVariable("id_film") Long idFilm) {
        return acteurService.getOneFilmOfActeur(idActeur,idFilm)
                .map(film -> new ResponseEntity<>(film,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/acteurs")
    public ResponseEntity<Acteur> updateActeur(@RequestBody Acteur acteur) {
        return acteurService.updateActeur(acteur)
                .map(acteur1->new ResponseEntity<>(acteur1, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    @PostMapping("/acteurs/{id_acteur}")
    public ResponseEntity<TenuDeCombat> addTenuDeCombatforActeur(@PathVariable("id_acteur") Long idActeur,@RequestBody TenuDeCombat tenuDeCombat){
        return acteurService.addTenuDeCombatforActeur(idActeur,tenuDeCombat)
                .map(tenuDeCombat1 -> new ResponseEntity<>(tenuDeCombat1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
