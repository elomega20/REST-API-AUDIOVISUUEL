package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.repository.MaisonDeProductionRepository;
import com.elomega.audiovisuel.service.ConvertDtoToEntity;
import com.elomega.audiovisuel.service.ConvertEntityToDto;
import com.elomega.audiovisuel.service.FilmService;
import com.elomega.audiovisuel.service.MaisonDeProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
public class MaisonDeproductionServiceImplementation implements MaisonDeProductionService {
    private final MaisonDeProductionRepository maisonDeProductionRepository;
    private final FilmRepository filmRepository;
    private final FilmService filmService;
    private final ActeurRepository acteurRepository;
    private final ConvertDtoToEntity convertDtoToEntity;
    @Override
    public Page<MaisonDeProduction> getMaisonDeproduction(int page, int size) {
        return maisonDeProductionRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<MaisonDeProduction> getMaisonDeProducrionById(Long id) {
        return maisonDeProductionRepository.findById(id);
    }

    @Override
    public Optional<MaisonDeProduction> postMaisonDeProduction(MaisonDeProduction maisonDeProduction) {
        return Optional.of(maisonDeProductionRepository.save(maisonDeProduction));
    }

    @Override
    public boolean deleteMaisonDeProductionById(Long id) {
        if (maisonDeProductionRepository.findById(id).isPresent()){
            maisonDeProductionRepository.deleteById(id);
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public Optional<MaisonDeProduction> updateMaisonDeProduction(MaisonDeProduction maisonDeProduction) {
        if (maisonDeProductionRepository.findById(maisonDeProduction.getId()).isPresent()){
            return Optional.of(maisonDeProductionRepository.save(maisonDeProduction));
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<List<Film>> getAllFilmsOfMaisonDeProduction(Long idMaisonDeproduction) {
        Optional<MaisonDeProduction> maisonDeProduction = maisonDeProductionRepository.findById(idMaisonDeproduction);
        if (maisonDeProduction.isPresent())
            return Optional.of(maisonDeProduction.get().getFilms());
        else
            return Optional.empty();

    }

    @Override
    public Optional<Film> getOneFilmOfMaisonDeProduction(Long idMaisonDeProduction, Long idFilm) {
        Optional<MaisonDeProduction> maisonDeProduction = maisonDeProductionRepository.findById(idMaisonDeProduction);
        if (maisonDeProduction.isPresent()){
            Optional<Film> film = filmRepository.findById(idFilm);
            if (film.isPresent()){
                return maisonDeProduction
                        .get()
                        .getFilms()
                        .stream()
                        .filter(Film -> Film.getFilmId() == idFilm)
                        .findFirst();
            }
            else
                return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Film> postOneFilmForMaisonDeProduction(Long idMaisonDeProduction, Film film) {
        Optional<MaisonDeProduction> maisonDeProduction = maisonDeProductionRepository.findById(idMaisonDeProduction);
        if (maisonDeProduction.isPresent()){
            Optional<Film> filmPosted = filmService.postFilm(film);
            if (filmPosted.isPresent()){
                maisonDeProduction.get().addFilm(filmPosted.get());
                filmPosted.get().setMaisonDeProduction(maisonDeProduction.get());
                maisonDeProductionRepository.save(maisonDeProduction.get());
                filmRepository.save(filmPosted.get());
            }
            else
                return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ActeurResponse>> associatActeursAndFilm(Long idMaisonDeProduction, Long idFilm, List<ActeurResponse> acteurResponses) {
        List<Acteur> acteurs = acteurResponses.stream()
                .map(this::convertActeurResponseToActeurEntity)
                .collect(Collectors.toList());
        if (maisonDeProductionRepository.existsById(idMaisonDeProduction) && filmRepository.existsById(idFilm) && allActeursExist(acteurs)){
            Optional<Film> film = filmRepository.findById(idFilm);
            for (Acteur acteur : acteurs) {
                film.get().addActeur(acteur);
                acteur.addFilm(film.get());
                filmRepository.save(film.get());
                acteurRepository.save(acteur);
            }

            return Optional.of(acteurResponses);
        }
        return Optional.empty();
    }

    private boolean allActeursExist(List<Acteur> acteurs){
        for (Acteur acteur : acteurs) {
            if (!acteurRepository.existsById(acteur.getId()))
                return false;
        }
        return true;
    }
    private Acteur convertActeurResponseToActeurEntity(ActeurResponse acteurResponse){
        return convertDtoToEntity.convertActeurResponseToActeurEntity(acteurResponse);
    }

}
