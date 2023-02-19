package com.elomega.audiovisuel.service.maison_de_production_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface MaisonDeProductionService {
    // pour obtenir tout ls maisons de productions
    public Page<MaisonDeProduction> getMaisonDeproduction(int page, int size);
    // pour obtenir une maison de production via son identifiant
    public Optional<MaisonDeProduction> getMaisonDeProducrionById(Long id);
    // pour ajouter une maison de production
    public Optional<MaisonDeProduction> postMaisonDeProduction(MaisonDeProduction maisonDeProduction);
    //    // pour supprimer une maison de production via son Id
    public boolean deleteMaisonDeProductionById(Long id);
    // pour mettre a jour une mison de production
    public Optional<MaisonDeProduction> updateMaisonDeProduction(MaisonDeProduction maisonDeProduction);
    // pour obtenir tout les films d'une maison de production
    public Optional<List<Film>> getAllFilmsOfMaisonDeProduction(Long idMaisonDeproduction);
    // pour obtenir un film d'une maison de production via leur idenrifiant
    public Optional<Film> getOneFilmOfMaisonDeProduction(Long idMaisonDeProduction,Long idFilm);
    // ajouter un film pour une maison de production
    public Optional<Film> postOneFilmForMaisonDeProduction(Long idMaisonDeProduction,Film film);
    public Optional<List<Acteur>> associatActeursAndFilm(Long idMaisonDeProduction,Long idFilm,List<Acteur> acteurs);
}
