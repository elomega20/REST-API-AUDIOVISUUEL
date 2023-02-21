package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.model.TenuDeCombat;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TenuDeCombatService {
    // pour obtenir les tenus de combats
    public Page<TenuDeCombat> getTenuDeCombat(int page,int size);
    // pour obtenir un tenu de combat via son identifiant
    public Optional<TenuDeCombat> getTenuDeCombatById(Long id);
    // pour ajouter un tenu de combat
    public Optional<TenuDeCombat> postTenuDeCombat(TenuDeCombat tenuDeCombat);
    // pour supprimer un tenu de combat via son identifiant
    public boolean deleteTenuDeCombarById(Long id);
    // pour la mise a jour complete d'un tenu de combat
    public Optional<TenuDeCombat> updateTenuDeCombat(TenuDeCombat tenuDeCombat);
}
