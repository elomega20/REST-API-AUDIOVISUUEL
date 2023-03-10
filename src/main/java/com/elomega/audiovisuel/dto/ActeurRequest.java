package com.elomega.audiovisuel.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ActeurRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate dateDeNaissance;
    private String profileImageUrl;
    private String role;
    private String isActive;
    private String isNotLocked;
}
