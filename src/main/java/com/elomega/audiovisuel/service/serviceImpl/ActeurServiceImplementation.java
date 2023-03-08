package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.TenuDeCombat;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import com.elomega.audiovisuel.service.ActeurService;
import com.elomega.audiovisuel.service.ConvertDtoToEntity;
import com.elomega.audiovisuel.service.TenuDeCombatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ActeurServiceImplementation implements ActeurService {
    private final ActeurRepository acteurRepository;
    private final FilmRepository filmRepository;
    private final TenuDeCombatRepository tenuDeCombatRepository;
    private final TenuDeCombatService tenuDeCombatService;
    private final ConvertDtoToEntity convertDtoToEntity;
    @Override
    public Page<Acteur> getActeur(int page,int size) {
        return acteurRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<Acteur> getActeurById(Long id) {
        return acteurRepository.findById(id);
    }

    @Override
    public Optional<Acteur> postActeur(ActeurRequest acteurRequest) {
        Acteur acteur = convertDtoToEntity.convertActeurDtoToActeurEntity(acteurRequest);
        acteurRepository.saveAndFlush(acteur);
        return Optional.of(acteur);
    }

    @Override
    public boolean deleteActeurById(Long id) {
        if (acteurRepository.findById(id).isPresent()){
            acteurRepository.deleteById(id);
            return TRUE;
        }
        return  FALSE;
    }

    @Override
    public Optional<List<Film>> getAllFilmsOfActeur(Long idActeur) {
        Optional<Acteur> acteur = acteurRepository.findById(idActeur);
        if (acteur.isPresent()){
            return Optional.of(acteur.get().getFilms());
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Film> getOneFilmOfActeur(Long idActeur, Long idFilm) {
        Optional<Acteur> acteur = acteurRepository.findById(idActeur);
        if (acteur.isPresent()){
            Optional<Film> film = filmRepository.findById(idFilm);
            if (film.isPresent()){
                return acteur
                        .get()
                        .getFilms()
                        .stream()
                        .filter(Film -> Film.getFilmId() == idFilm)
                        .findFirst();
            }
            else
                return Optional.empty();
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Acteur> updateActeur(Acteur acteur) {
        Optional<Acteur> acteurExistant = Optional.ofNullable(acteurRepository.findById(acteur.getActeurId()).orElseThrow(IllegalArgumentException::new));
        return acteurExistant.map(
                act -> {
                    return acteurRepository.save(acteur);
                }
        );
    }

    @Override
    public Optional<TenuDeCombat> addTenuDeCombatforActeur(Long idActeur, TenuDeCombat tenuDeCombat) {
        Optional<Acteur> acteur = acteurRepository.findById(idActeur);
        if (acteur.isPresent()){
            Optional<TenuDeCombat> tenuDeCombatPosted = tenuDeCombatService.postTenuDeCombat(tenuDeCombat);
            if (tenuDeCombatPosted.isPresent()) {
                acteur.get().setTenuDeCombat(tenuDeCombatPosted.get());
                tenuDeCombatPosted.get().setActeur(acteur.get());
                acteurRepository.save(acteur.get());
                tenuDeCombatRepository.save(tenuDeCombatPosted.get());
                return tenuDeCombatPosted;
            }
            else
                return Optional.empty();
        }
        return Optional.empty();
    }

}
