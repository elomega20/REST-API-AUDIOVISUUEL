package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.TenuDeCombat;
import com.elomega.audiovisuel.service.ActeurService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Acteur> postActeur(@RequestBody ActeurRequest acteurRequest) {
        return acteurService.postActeur(acteurRequest)
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
    @PostMapping("/acteurs/{id_acteur}/tenu-de-combats")
    public ResponseEntity<TenuDeCombat> addTenuDeCombatforActeur(@PathVariable("id_acteur") Long idActeur,@RequestBody TenuDeCombat tenuDeCombat){
        return acteurService.addTenuDeCombatforActeur(idActeur,tenuDeCombat)
                .map(tenuDeCombat1 -> new ResponseEntity<>(tenuDeCombat1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
