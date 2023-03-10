package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.TenuDeCombat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface ActeurService {
    // pour abtenir tout les acteurs
    public Page<ActeurResponse> getActeur(int page,int size);
    // pour obtenir un acteur via son identifiant
    public Optional<ActeurResponse> getActeurById(Long id);
    // pour ajouter un acteur
    public Optional<ActeurResponse> postActeur(ActeurRequest acteurRequest);
    // pour supprimer un acteur via son identifant
    public boolean deleteActeurById(Long id);
    // pour  obtenir tout les films d'un acteur
    public Optional<List<Film>> getAllFilmsOfActeur(Long idActeur);
    // pour obtenir un film d'un acteur via leur identifiants
    public Optional<Film> getOneFilmOfActeur(Long idActeur,Long idFilm);
    public Optional<ActeurResponse> updateActeur(ActeurResponse acteurResponse);
    public Optional<TenuDeCombat> addTenuDeCombatforActeur(Long idActeur, TenuDeCombat tenuDeCombat);
}
