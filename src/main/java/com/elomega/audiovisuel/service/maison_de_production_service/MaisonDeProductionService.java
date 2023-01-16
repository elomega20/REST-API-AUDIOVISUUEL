package com.elomega.audiovisuel.service.maison_de_production_service;

import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;

public interface MaisonDeProductionService {
    // pour obtenir tout ls maisons de productions
    public Page<MaisonDeProduction> getMaisonDeproduction(int page, int size);
    // pour obtenir une maison de production via son identifiant
    public MaisonDeProduction getMaisonDeProducrionById(Long id);
    // pour ajouter une maison de production
    public MaisonDeProduction postMaisonDeProduction(MaisonDeProduction maisonDeProduction);
    //    // pour supprimer une maison de production via so
    public boolean deleteMaisonDeProductionById(Long id);
    // pour mettre a jour une mison de production
    public MaisonDeProduction updateMaisonDeProduction(Long id,MaisonDeProduction maisonDeProduction);
    // pour obtenir tout les films d'une maison de production
    public List<Film> getAllFilmsOfMaisonDeProduction(Long idMaisonDeproduction);
    // pour obtenir un film d'une maison de production via leur idenrifiant
    public Stream<Film> getOneFilmOfMaisonDeProduction(Long idMaisonDeProduction,Long idFilm);
}
