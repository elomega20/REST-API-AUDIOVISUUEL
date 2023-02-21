package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.TenuDeCombat;
import com.elomega.audiovisuel.service.TenuDeCombatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TenuDeCombatController {
    private final TenuDeCombatService tenuDeCombatService;
    @GetMapping("/tenu-de-combats")
    public ResponseEntity<Page<TenuDeCombat>> getTenuDeCombat(@Param("page") int page, @Param("size") int size){
        return new ResponseEntity<>(tenuDeCombatService.getTenuDeCombat(page,size),HttpStatus.OK);
    }

    @GetMapping("/tenu-de-combats/{id}")
    public ResponseEntity<TenuDeCombat> getTenuDeCombatById(@PathVariable Long id){
        return tenuDeCombatService.getTenuDeCombatById(id)
                .map(tenuDeCombat -> new ResponseEntity<>(tenuDeCombat,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tenu-de-combats")
    public ResponseEntity<TenuDeCombat> postTenuDeCombat(@RequestBody TenuDeCombat tenuDeCombat){
        return tenuDeCombatService.postTenuDeCombat(tenuDeCombat)
                .map(tenuDeCombat1 -> new ResponseEntity<>(tenuDeCombat1,HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping("/tenu-de-combats/{id}")
    public ResponseEntity<HttpStatus> deleteTenuDeCombarById(@PathVariable Long id){
        return tenuDeCombatService.deleteTenuDeCombarById(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tenu-de-combats/{id}")
    public ResponseEntity<TenuDeCombat> updateTenuDeCombat(@RequestBody TenuDeCombat tenuDeCombat) {
        return tenuDeCombatService.updateTenuDeCombat(tenuDeCombat)
                .map(tenuDeCombat1 -> new ResponseEntity<>(tenuDeCombat1,HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
