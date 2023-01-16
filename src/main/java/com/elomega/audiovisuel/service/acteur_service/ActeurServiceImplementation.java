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
    public Acteur getActeurById(Long id) {
        Optional<Acteur> acteurOptional = acteurRepository.findById(id);
        if (acteurOptional.isPresent()){
            return acteurOptional.get();
        }
        return null;
    }

    @Override
    public Acteur postActeur(Acteur acteur) {
        Acteur acteurSave = acteurRepository.save(acteur);
        if (acteurRepository.findById(acteurSave.getActeurId()).isPresent()){
            return acteurSave;
        }
        return null;
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
    public List<Film> getAllFilmsOfActeur(Long idActeur) {
        return acteurRepository.findById(idActeur).get().getFilms();
    }

    @Override
    public Stream<Film> getOneFilmOfActeur(Long idActeur, Long idFilm) {
        return acteurRepository.findById(idActeur).get().getFilms().stream()
                .filter(Film -> Film.getFilmId() == idFilm);
    }

    @Override
    public Acteur updateActeur(Long id, Acteur acteur) {
        Optional<Acteur> acteurExistant = acteurRepository.findById(id);
        if (acteurExistant.isPresent()){
            BeanUtils.copyProperties(acteur,acteurExistant.get(),"acteurId");
            acteurRepository.save(acteurExistant.get());
            return acteurExistant.get();
        }
        return null;
    }
}
