package com.elomega.audiovisuel.repository;

import com.elomega.audiovisuel.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
