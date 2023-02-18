package com.elomega.audiovisuel.service.tenu_de_combat_service;

import com.elomega.audiovisuel.enumeration.Couleur;
import com.elomega.audiovisuel.enumeration.Pouvoir;
import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class TenuDeCombatServiceImplementation implements TenuDeCombatService {
    private final TenuDeCombatRepository tenuDeCombatRepository;
    @Override
    public Page<TenuDeCombat> getTenuDeCombat(int page, int size) {
        return tenuDeCombatRepository.findAll(PageRequest.of(page,size));
    }

    @Override
    public Optional<TenuDeCombat> getTenuDeCombatById(Long id) {
        return tenuDeCombatRepository.findById(id);
    }

    @Override
    public Optional<TenuDeCombat> postTenuDeCombat(TenuDeCombat tenuDeCombat) {
        tenuDeCombat.setPouvoir(getPouvoir(tenuDeCombat.getPouvoir()).name());
        tenuDeCombat.setCouleur(getCouleur(tenuDeCombat.getCouleur()).name());
        return Optional.of(tenuDeCombatRepository.save(tenuDeCombat));
    }

    @Override
    public boolean deleteTenuDeCombarById(Long id) {
        Optional<TenuDeCombat> tenuDeCombat = tenuDeCombatRepository.findById(id);
        if (tenuDeCombat.isPresent()){
            tenuDeCombatRepository.deleteById(id);
            return TRUE;
        }
        return FALSE;
    }

    @Override
    public Optional<TenuDeCombat> updateTenuDeCombat(TenuDeCombat tenuDeCombat) {
        Optional<TenuDeCombat> tenuDeCombatExistant = tenuDeCombatRepository.findById(tenuDeCombat.getTenuDeCombatId());
        if (tenuDeCombatExistant.isPresent()){
            tenuDeCombat.setPouvoir(getPouvoir(tenuDeCombat.getPouvoir()).name());
            tenuDeCombat.setCouleur(getCouleur(tenuDeCombat.getCouleur()).name());
            return Optional.of(tenuDeCombatRepository.save(tenuDeCombat));
        }
        return  Optional.empty();
    }

    private Couleur getCouleur(String couleur){
        return Couleur.valueOf(couleur);
    }

    private Pouvoir getPouvoir(String pouvoir){
        return Pouvoir.valueOf(pouvoir);
    }
}
