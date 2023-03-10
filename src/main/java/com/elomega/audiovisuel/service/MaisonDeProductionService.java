package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.MaisonDeProductionRequest;
import com.elomega.audiovisuel.dto.MaisonDeProductionResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MaisonDeProductionService {
    // pour obtenir tout ls maisons de productions
    public Page<MaisonDeProductionResponse> getMaisonDeproduction(int page, int size);
    // pour obtenir une maison de production via son identifiant
    public Optional<MaisonDeProductionResponse> getMaisonDeProducrionById(Long id);
    // pour ajouter une maison de production
    public Optional<MaisonDeProductionResponse> postMaisonDeProduction(MaisonDeProductionRequest maisonDeProductionRequest);
    //    // pour supprimer une maison de production via son Id
    public boolean deleteMaisonDeProductionById(Long id);
    // pour mettre a jour une mison de production
    public Optional<MaisonDeProductionResponse> updateMaisonDeProduction(MaisonDeProductionResponse maisonDeProductionResponse);
    // pour obtenir tout les films d'une maison de production
    public Optional<List<Film>> getAllFilmsOfMaisonDeProduction(Long idMaisonDeproduction);
    // pour obtenir un film d'une maison de production via leur idenrifiant
    public Optional<Film> getOneFilmOfMaisonDeProduction(Long idMaisonDeProduction,Long idFilm);
    // ajouter un film pour une maison de production
    public Optional<Film> postOneFilmForMaisonDeProduction(Long idMaisonDeProduction,Film film);
    public Optional<List<ActeurResponse>> associatActeursAndFilm(Long idMaisonDeProduction,Long idFilm,List<ActeurResponse> acteurResponses);
}
