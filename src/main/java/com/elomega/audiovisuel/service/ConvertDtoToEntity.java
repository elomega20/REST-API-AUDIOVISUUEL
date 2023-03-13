package com.elomega.audiovisuel.service;

import com.elomega.audiovisuel.dto.*;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.model.User;

public interface ConvertDtoToEntity {
    Acteur convertActeurResquestToActeurEntity(ActeurRequest acteurRequest);
    Acteur convertActeurResponseToActeurEntity(ActeurResponse acteurResponse);
    MaisonDeProduction convertMaisonDeProductionRequestToMaisonDeProductionEntity(MaisonDeProductionRequest maisonDeProductionRequest);
    MaisonDeProduction convertMaisonDeProductionResponseToMaisonDeProductionEntity(MaisonDeProductionResponse maisonDeProductionResponse);
    User convertUserRequestToUserEntity(UserRequest userRequest);
    User convertUserResponseToUserEntity(UserResponse userResponse);
}
