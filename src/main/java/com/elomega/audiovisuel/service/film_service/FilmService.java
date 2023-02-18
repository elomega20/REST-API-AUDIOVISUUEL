package com.elomega.audiovisuel.service.film_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface FilmService {
    // pour abtenir tout les films
    public Page<Film> getFilm(int page, int size);
    // pour abtenir un film via son identifiant
    public Optional<Film> getFilmById(Long id);
    // pour ajouter un film
    public Optional<Film> postFilm(Film film);
    // pour supprimer un film via son identifant
    public boolean deleteFilmById(Long id);
    // pour  obtenir tout les acteurs d'un film
    public Optional<List<Acteur>> getAllActeursOfFilm(Long idFilm);
    // pour obtenir un acteur d'un film via leur identifiants
    public Optional<Acteur> getOneActeurOfFilm(Long idFilm,Long idActeur);
    // pour mettre a jour un film
    public Optional<Film> updateFilm(Film film);
}
