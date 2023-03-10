package com.elomega.audiovisuel.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
public class MaisonDeProductionResponse {
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private LocalDate anneeDeCreation;
    private String profileImageUrl;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private Date joinDate;
    private String role;
    private String[] authorities;
    private String isActive;
    private String isNotLocked;
}
