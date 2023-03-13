package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.UserRequest;
import com.elomega.audiovisuel.dto.UserResponse;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    // pour ajouter un utilisateur
    public UserResponse postUser(UserRequest userRequest);
    // pour recuperer tt les utilisateurs
    public Page<UserResponse> getuser(int page, int size);
    // pour recuperer un utilisateur via son identifian
    public Optional<UserResponse> getUserById(Long id);
    // pour mettre a jour un utilisateur
    public Optional<UserResponse> updateUser(UserResponse userResponse);
    //pour supprimer un utilisateur
    public boolean deleteUser(Long id);
}
