package com.elomega.audiovisuel.service.acteur_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import com.elomega.audiovisuel.service.tenu_de_combat_service.TenuDeCombatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ActeurServiceImplementation implements ActeurService{
    private final ActeurRepository acteurRepository;
    private final FilmRepository filmRepository;
    private final TenuDeCombatRepository tenuDeCombatRepository;
    private final TenuDeCombatService tenuDeCombatService;
    @Override
    public Page<Acteur> getActeur(int page,int size) {
        return acteurRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<Acteur> getActeurById(Long id) {
        return acteurRepository.findById(id);
    }

    @Override
    public Optional<Acteur> postActeur(Acteur acteur) {
        Acteur acteurSave = acteurRepository.save(acteur);
        return Optional.of(acteurSave);
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
