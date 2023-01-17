package com.elomega.audiovisuel.service.tenu_de_combat_service;

import com.elomega.audiovisuel.model.tenu_de_combat.TenuDeCombat;
import org.springframework.data.domain.Page;

public interface TenuDeCombatService {
    // pour obtenir les tenus de combats
    public Page<TenuDeCombat> getTenuDeCombat(int page,int size);
    // pour obtenir un tenu de combat via son identifiant
    public TenuDeCombat getTenuDeCombatById(Long id);
    // pour ajouter un tenu de combat
    public TenuDeCombat postTenuDeCombat(TenuDeCombat tenuDeCombat);
    // pour supprimer un tenu de combat via son identifiant
    public boolean deleteTenuDeCombarById(Long id);
    // pour la mise a jour complete d'un tenu de combat
    public TenuDeCombat updateTenuDeCombat(Long id,TenuDeCombat tenuDeCombat);
    // pour la mise a jour pariel d'un tenu de combat
    public TenuDeCombat partialUpdateTenuDeCombat(Long id,TenuDeCombat tenuDeCombat);
}
