package com.elomega.audiovisuel.service.acteur_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
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
        return acteurRepository.findById(acteurSave.getActeurId());
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
    public Optional<Acteur> getAllFilmsOfActeur(Long idActeur) {
        return acteurRepository.findById(idActeur);
    }

    @Override
    public Optional<Film> getOneFilmOfActeur(Long idActeur, Long idFilm) {
        return acteurRepository.findById(idActeur).get().getFilms().stream()
                .filter(Film -> Film.getFilmId() == idFilm).findFirst();
    }

    /*@Override
    public Optional<Acteur> updateActeur(Long id, Acteur acteur) {
        Optional<Acteur> acteurExistant = Optional.ofNullable(acteurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("L'acteur %d n'existe pas", id))));
        if (acteurExistant.isPresent()){
            BeanUtils.copyProperties(acteur,acteurExistant.get(),"acteurId");
            acteurRepository.save(acteurExistant.get());
            return acteurExistant;
        }
        return Optional.empty();
    }*/
    @Override
    public Optional<Acteur> updateActeur(Long id, Acteur acteur) {
        Optional<Acteur> acteurExistant = Optional.ofNullable(acteurRepository.findById(id).orElseThrow(IllegalArgumentException::new));
        return acteurExistant.map(
                act -> {
                    BeanUtils.copyProperties(acteur,act,"acteurId");
                    acteurRepository.save(act);
                    return act;
                }
        );
    }

}
