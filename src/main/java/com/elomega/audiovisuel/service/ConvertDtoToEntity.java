package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.MaisonDeProductionRequest;
import com.elomega.audiovisuel.dto.MaisonDeProductionResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.MaisonDeProduction;

public interface ConvertDtoToEntity {
    Acteur convertActeurResquestToActeurEntity(ActeurRequest acteurRequest);
    Acteur convertActeurResponseToActeurEntity(ActeurResponse acteurResponse);
    MaisonDeProduction convertMaisonDeProductionRequestToMaisonDeProductionEntity(MaisonDeProductionRequest maisonDeProductionRequest);
    MaisonDeProduction convertMaisonDeProductionResponseToMaisonDeProductionEntity(MaisonDeProductionResponse maisonDeProductionResponse);
}
