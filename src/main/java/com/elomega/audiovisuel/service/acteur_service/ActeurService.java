package com.elomega.audiovisuel.service.acteur_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface ActeurService {
    // pour abtenir tout les acteurs
    public Page<Acteur> getActeur(int page,int size);
    // pour obtenir un acteur via son identifiant
    public Optional<Acteur> getActeurById(Long id);
    // pour ajouter un acteur
    public Optional<Acteur> postActeur(Acteur acteur);
    // pour supprimer un acteur via son identifant
    public boolean deleteActeurById(Long id);
    // pour  obtenir tout les films d'un acteur
    public Optional<List<Film>> getAllFilmsOfActeur(Long idActeur);
    // pour obtenir un film d'un acteur via leur identifiants
    public Optional<Film> getOneFilmOfActeur(Long idActeur,Long idFilm);
    public Optional<Acteur> updateActeur(Acteur acteur);
    public Optional<TenuDeCombat> addTenuDeCombatforActeur(Long idActeur, TenuDeCombat tenuDeCombat);
}
