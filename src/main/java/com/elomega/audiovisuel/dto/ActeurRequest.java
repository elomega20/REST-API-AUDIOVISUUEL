package com.elomega.audiovisuel.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ActeurRequest {
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
}
