package com.elomega.audiovisuel.service.tenu_de_combat_service;

import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import com.elomega.audiovisuel.repository.TenuDeCombatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public TenuDeCombat getTenuDeCombatById(Long id) {
        Optional<TenuDeCombat> tenuDeCombat = tenuDeCombatRepository.findById(id);

        if (tenuDeCombat.isPresent())
            return tenuDeCombat.get();
        else
            return null;
    }

    @Override
    public TenuDeCombat postTenuDeCombat(TenuDeCombat tenuDeCombat) {
        return tenuDeCombatRepository.save(tenuDeCombat);
    }

    @Override
    public boolean deleteTenuDeCombarById(Long id) {
        tenuDeCombatRepository.deleteById(id);
        return TRUE;
    }

    @Override
    public TenuDeCombat updateTenuDeCombat(Long id, TenuDeCombat tenuDeCombat) {
        Optional<TenuDeCombat> tenuDeCombatExistant = tenuDeCombatRepository.findById(id);
        if (tenuDeCombatExistant.isPresent()){
            BeanUtils.copyProperties(tenuDeCombat,tenuDeCombatExistant.get(),"tenuDeCombatId");
            tenuDeCombatRepository.save(tenuDeCombatExistant.get());
            return tenuDeCombatExistant.get();
        }
        return  null;
    }
}
