package com.elomega.audiovisuel.repository;

import com.elomega.audiovisuel.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findByRole(String role,Pageable pageable);
}
