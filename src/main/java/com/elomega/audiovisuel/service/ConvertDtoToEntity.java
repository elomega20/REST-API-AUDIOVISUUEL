package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;

public interface ConvertDtoToEntity {
    Acteur convertActeurResquestToActeurEntity(ActeurRequest acteurRequest);
    Acteur convertActeurResponseToActeurEntity(ActeurResponse acteurResponse);
}
