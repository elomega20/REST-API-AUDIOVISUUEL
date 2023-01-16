package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.model.Response;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.service.tenu_de_combat_service.TenuDeCombatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TenuDeCombatController {
    private final TenuDeCombatService tenuDeCombatService;
    @GetMapping("/tenu_de_combats")
    public ResponseEntity<Response> getTenuDeCombat(@Param("page") int page,@Param("size") int size){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("liste des tenus de combats")
                        .data(Map.of("tenu_de_combats",tenuDeCombatService.getTenuDeCombat(page,size)))
                        .build()
        );
    }

    @GetMapping("/tenu_de_combats/{id}")
    public ResponseEntity<Response> getTenuDeCombatById(@PathVariable Long id){
        TenuDeCombat tenuDeCombat = tenuDeCombatService.getTenuDeCombatById(id);
        if (tenuDeCombat != null){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("obtention du tenu de combat "+id)
                            .data(Map.of("tenu_de_combat",tenuDeCombat))
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.NOT_FOUND)
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("Le tenu de combat "+id+" n'existe pas")
                            .build()
            );
        }
    }

    @PostMapping("/tenu_de_combats")
    public ResponseEntity<Response> postTenuDeCombat(@RequestBody TenuDeCombat tenuDeCombat){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .message("tenu de combat cree avec success")
                        .data(Map.of("tenu_de_combat",tenuDeCombatService.postTenuDeCombat(tenuDeCombat)))
                        .build()
        );
    }

    @DeleteMapping("/tenu_de_combats/{id}")
    public ResponseEntity<Response> deleteTenuDeCombarById(@PathVariable Long id){
        if (tenuDeCombatService.deleteTenuDeCombarById(id)){
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("tenu combat "+id+" supprimer avec succes")
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .message("La tenu de combat "+id+" n'existe pas , impossible de la supprimer")
                            .build()
            );
        }
    }

    @PutMapping("/tenu_de_combats/{id}")
    public ResponseEntity<Response> updateTenuDeCombat(@PathVariable Long id,@RequestBody @Valid TenuDeCombat tenuDeCombat){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .message("mise a jour reussie avec success")
                        .data(Map.of("tenu_de_combat",tenuDeCombatService.updateTenuDeCombat(id,tenuDeCombat)))
                        .build()
        );
    }
}
