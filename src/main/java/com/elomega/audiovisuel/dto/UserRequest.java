package com.elomega.audiovisuel.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String profileImageUrl;
    private String role;
    private String isActive;
    private String isNotLocked;
}
