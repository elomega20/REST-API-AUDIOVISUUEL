package com.elomega.audiovisuel.service.acteur_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;


public interface ActeurService {
    // pour abtenir tout les acteurs
    public Page<Acteur> getActeur(int page,int size);
    // pour abtenir un acteur via son identifiant
    public Acteur getActeurById(Long id);
    // pour ajouter un acteur
    public Acteur postActeur(Acteur acteur);
    // pour supprimer un acteur via son identifant
    public boolean deleteActeurById(Long id);
    // pour  abtenir tout les films d'un acteur
    public List<Film> getAllFilmsOfActeur(Long idActeur);
    // pour obtenir un film d'un acteur via leur identifiants
    public Stream<Film> getOneFilmOfActeur(Long idActeur,Long idFilm);
    public Acteur updateActeur(Long id,Acteur acteur);
}
