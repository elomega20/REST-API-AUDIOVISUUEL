package com.elomega.audiovisuel.service.film_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Stream;

public interface FilmService {
    // pour abtenir tout les films
    public Page<Film> getFilm(int page, int size);
    // pour abtenir un film via son identifiant
    public Film getFilmById(@PathVariable Long id);
    // pour ajouter un film
    public Film postFilm(@RequestBody Film film);
    // pour supprimer un film via son identifant
    public boolean deleteFilmById(@PathVariable Long id);
    // pour  abtenir tout les acteurs d'un film
    public List<Acteur> getAllActeursOfFilm(@PathVariable Long idFilm);
    // pour obtenir un acteur d'un film via leur identifiants
    public Stream<Acteur> getOneActeurOfFilm(@PathVariable Long idFilm, @PathVariable Long idActeur);
    public Film updateFilm(@PathVariable Long id,@RequestBody Film film);
}
