package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.MaisonDeProductionResponse;
import com.elomega.audiovisuel.dto.UserResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.model.User;

public interface ConvertEntityToDto {
    ActeurResponse convertActeurEntityToActeurResponse(Acteur acteur);
    MaisonDeProductionResponse convertMainsonDeProductionEntityToMaisonDeProductionResponse(MaisonDeProduction maisonDeProduction);
    UserResponse convertUserEntityToUserResponse(User user);
}
