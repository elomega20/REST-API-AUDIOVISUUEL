package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.MaisonDeProductionResponse;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.service.ConvertEntityToDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertEntityToDtoImplementation implements ConvertEntityToDto {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public ActeurResponse convertActeurEntityToActeurResponse(Acteur acteur) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ActeurResponse acteurResponse = new ActeurResponse();
        acteurResponse = modelMapper.map(acteur,ActeurResponse.class);
        return acteurResponse;
    }

    @Override
    public MaisonDeProductionResponse convertMainsonDeProductionEntityToMaisonDeProductionResponse(MaisonDeProduction maisonDeProduction) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        MaisonDeProductionResponse maisonDeProductionResponse = new MaisonDeProductionResponse();
        maisonDeProductionResponse = modelMapper.map(maisonDeProduction,MaisonDeProductionResponse.class);
        return maisonDeProductionResponse;
    }
}
