package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.model.Acteur;

public interface ConvertEntityToDto {
    ActeurResponse convertActeurEntityToActeurResponse(Acteur acteur);
}
