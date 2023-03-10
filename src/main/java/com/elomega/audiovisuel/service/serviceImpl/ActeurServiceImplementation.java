package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.Film;
import com.elomega.audiovisuel.model.TenuDeCombat;
import com.elomega.audiovisuel.repository.ActeurRepository;
import com.elomega.audiovisuel.repository.FilmRepository;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import com.elomega.audiovisuel.service.ActeurService;
import com.elomega.audiovisuel.service.ConvertDtoToEntity;
import com.elomega.audiovisuel.service.ConvertEntityToDto;
import com.elomega.audiovisuel.service.TenuDeCombatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final ConvertEntityToDto convertEntityToDto;
    @Override
    public Page<ActeurResponse> getActeur(int page,int size) {
        List<ActeurResponse> acteurResponses = acteurRepository.findAll(PageRequest.of(page,size))
                .stream()
                .map(this::convertActeurEntityToActeurResponse)
                .collect(Collectors.toList());
        Page<ActeurResponse> acteurResponsesPages = new PageImpl<>(acteurResponses);
        return acteurResponsesPages;
    }

    @Override
    public Optional<ActeurResponse> getActeurById(Long id) {
        Optional<Acteur> acteur = acteurRepository.findById(id);
        if (acteur.isPresent()) {
            return Optional.of(convertActeurEntityToActeurResponse(acteur.get()));
        }else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ActeurResponse> postActeur(ActeurRequest acteurRequest) {
        Acteur acteur = convertDtoToEntity.convertActeurResquestToActeurEntity(acteurRequest);
        acteurRepository.saveAndFlush(acteur);
        ActeurResponse acteurResponse = convertEntityToDto.convertActeurEntityToActeurResponse(acteur);
        return Optional.of(acteurResponse);
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
    public Optional<ActeurResponse> updateActeur(ActeurResponse acteurResponse) {
        Acteur acteur = convertDtoToEntity.convertActeurResponseToActeurEntity(acteurResponse);
        Optional<Acteur> acteurExistant =  acteurRepository.findById(acteur.getId());
        if (acteurExistant.isPresent()){
            acteurRepository.saveAndFlush(acteur);
            ActeurResponse acteurResponse1 = convertActeurEntityToActeurResponse(acteur);
            log.info(acteurResponse1.toString());
            return Optional.of(acteurResponse1);
        }else {
            return Optional.empty();
        }
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

    private ActeurResponse convertActeurEntityToActeurResponse(Acteur acteur) {
        return convertEntityToDto.convertActeurEntityToActeurResponse(acteur);
    }

}
