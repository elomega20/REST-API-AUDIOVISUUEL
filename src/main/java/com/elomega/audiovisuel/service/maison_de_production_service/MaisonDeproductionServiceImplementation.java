package com.elomega.audiovisuel.service.maison_de_production_service;

import com.elomega.audiovisuel.model.film.Film;
import com.elomega.audiovisuel.model.maison_de_production.MaisonDeProduction;
import com.elomega.audiovisuel.repository.MaisonDeProductionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
public class MaisonDeproductionServiceImplementation implements MaisonDeProductionService{
    private final MaisonDeProductionRepository maisonDeProductionRepository;
    @Override
    public Page<MaisonDeProduction> getMaisonDeproduction(int page, int size) {
        return maisonDeProductionRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public MaisonDeProduction getMaisonDeProducrionById(Long id) {
        Optional<MaisonDeProduction> maisonDeProduction = maisonDeProductionRepository.findById(id);
        if (maisonDeProduction.isPresent())
            return maisonDeProduction.get();
        else
            return null;
    }

    @Override
    public MaisonDeProduction postMaisonDeProduction(MaisonDeProduction maisonDeProduction) {
        MaisonDeProduction maisonDeProductionSave = maisonDeProductionRepository.save(maisonDeProduction);
        if (maisonDeProductionRepository.findById(maisonDeProductionSave.getMaisonDeProductionId()).isPresent())
            return maisonDeProductionSave;
        else
            return null;
    }

    @Override
    public boolean deleteMaisonDeProductionById(Long id) {
        maisonDeProductionRepository.deleteById(id);
        return TRUE;
    }

    @Override
    public MaisonDeProduction updateMaisonDeProduction(Long id, MaisonDeProduction maisonDeProduction) {
        MaisonDeProduction maisonDeProductionExistant = maisonDeProductionRepository.findById(id).get();
        BeanUtils.copyProperties(maisonDeProduction,maisonDeProductionExistant,"maisonDeProductionId");
        return maisonDeProductionRepository.save(maisonDeProductionExistant);
    }

    @Override
    public List<Film> getAllFilmsOfMaisonDeProduction(Long idMaisonDeproduction) {
        MaisonDeProduction maisonDeProduction = maisonDeProductionRepository.findById(idMaisonDeproduction).get();
        return maisonDeProduction.getFilms();
    }

    @Override
    public Stream<Film> getOneFilmOfMaisonDeProduction(Long idMaisonDeProduction, Long idFilm) {
        MaisonDeProduction maisonDeProduction = maisonDeProductionRepository.findById(idMaisonDeProduction).get();
        return maisonDeProduction.getFilms().stream().filter(Film -> Film.getFilmId() == idFilm);
    }
}
