package com.elomega.audiovisuel.repository;

import com.elomega.audiovisuel.model.acteur.Acteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActeurRepository extends JpaRepository<Acteur,Long> {
}
