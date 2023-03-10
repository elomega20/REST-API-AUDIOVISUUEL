package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.service.ConvertEntityToDto;
import com.elomega.audiovisuel.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class FilmServiceImplementation implements FilmService {
    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;
    private final ConvertEntityToDto convertEntityToDto;
    @Override
    public Page<Film> getFilm(int page, int size) {
        return filmRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<Film> getFilmById(Long id) {
        return filmRepository.findById(id);
    }

    @Override
    public Optional<Film> postFilm(Film film) {
        return Optional.of(filmRepository.save(film));
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
    public Optional<List<ActeurResponse>> getAllActeursOfFilm(Long idFilm) {
        Optional<Film> film = filmRepository.findById(idFilm);
        if (film.isPresent()){
            return Optional.of(
                    film.get()
                            .getActeurs()
                            .stream()
                            .map(this::convertActeurEntityToActeurResponse)
                            .collect(Collectors.toList())
            );
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<ActeurResponse> getOneActeurOfFilm(Long idFilm, Long idActeur) {
        Optional<Film> film = filmRepository.findById(idFilm);
        if (film.isPresent()){
            Optional<Acteur> acteur = acteurRepository.findById(idActeur);
            if (acteur.isPresent()) {
                Optional<Acteur> acteurTrouver =  film.get()
                        .getActeurs()
                        .stream()
                        .filter(Acteur -> Acteur.getId() == idActeur)
                        .findFirst();
                return Optional.of(convertActeurEntityToActeurResponse(acteurTrouver.get()));
            }
            else
                return Optional.empty();
        }
        else
            return Optional.empty();
    }

    @Override
    public Optional<Film> updateFilm(Film film) {
        Optional<Film> filmExistant = Optional.ofNullable(filmRepository.findById(film.getFilmId()).orElseThrow(IllegalArgumentException::new));
        return filmExistant.map(
                film1 -> {
                    return filmRepository.save(film);
                }
        );
    }
    private ActeurResponse convertActeurEntityToActeurResponse(Acteur acteur) {
        return convertEntityToDto.convertActeurEntityToActeurResponse(acteur);
    }
}
