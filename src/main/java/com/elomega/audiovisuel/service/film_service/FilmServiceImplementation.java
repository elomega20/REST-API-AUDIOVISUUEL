package com.elomega.audiovisuel.service.film_service;

import com.elomega.audiovisuel.model.acteur.Acteur;
import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class FilmServiceImplementation implements FilmService{
    private final FilmRepository filmRepository;
    @Override
    public Page<Film> getFilm(int page, int size) {
        return filmRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Film getFilmById(Long id) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isPresent()){
            return filmOptional.get();
        }
        return null;
    }

    @Override
    public Film postFilm(Film film) {
        Film filmSave = filmRepository.save(film);
        if (filmRepository.findById(filmSave.getFilmId()).isPresent()){
            return filmSave;
        }
        return null;
    }

    @Override
    public boolean deleteFilmById(Long id) {
        if (filmRepository.findById(id).isPresent()){
            filmRepository.deleteById(id);
            return TRUE;
        }
        return  FALSE;
    }

    @Override
    public List<Acteur> getAllActeursOfFilm(Long idFilm) {
        return filmRepository.findById(idFilm).get().getActeurs();
    }

    @Override
    public Stream<Acteur> getOneActeurOfFilm(Long idFilm, Long idActeur) {
        return filmRepository.findById(idFilm).get().getActeurs().stream()
                .filter(Acteur -> Acteur.getActeurId() == idActeur);
    }

    @Override
    public Film updateFilm(Long id, Film film) {
        Optional<Film> filmExistant = filmRepository.findById(id);
        if (filmExistant.isPresent()){
            BeanUtils.copyProperties(film,filmExistant.get(),"filmId");
            filmRepository.save(filmExistant.get());
            return filmExistant.get();
        }
        return null;
    }
}
