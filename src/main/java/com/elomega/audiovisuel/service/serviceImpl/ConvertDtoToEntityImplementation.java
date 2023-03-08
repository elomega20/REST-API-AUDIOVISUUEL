package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.service.ConvertDtoToEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertDtoToEntityImplementation implements ConvertDtoToEntity {
   /* @Autowired
    private ModelMapper modelMapper;*/
    @Override
    public Acteur convertActeurDtoToActeurEntity(ActeurRequest acteurRequest) {
        /*modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Acteur acteur = new Acteur();
        acteur = modelMapper.map(acteurDto,Acteur.class);*/
        Acteur acteur = new Acteur();
        acteur.setNom(acteurRequest.getNom());
        acteur.setPrenom(acteurRequest.getPrenom());
        acteur.setDateDeNaissance(acteurRequest.getDateDeNaissance());
        return acteur;
    }

}
