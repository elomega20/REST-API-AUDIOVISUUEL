package com.elomega.audiovisuel.service.acteur_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Stream;


public interface ActeurService {
    // pour abtenir tout les acteurs
    public Page<Acteur> getActeur(int page,int size);
    // pour abtenir un acteur via son identifiant
    public Acteur getActeurById(@PathVariable Long id);
    // pour ajouter un acteur
    public Acteur postActeur(@RequestBody Acteur acteur);
    // pour supprimer un acteur via son identifant
    public boolean deleteActeurById(@PathVariable Long id);
    // pour  abtenir tout les films d'un acteur
    public List<Film> getAllFilmsOfActeur(@PathVariable Long idActeur);
    // pour obtenir un film d'un acteur via leur identifiants
    public Stream<Film> getOneFilmOfActeur(@PathVariable Long idActeur, @PathVariable Long idFilm);
    public Acteur updateActeur(@PathVariable Long id,@RequestBody Acteur acteur);
}
