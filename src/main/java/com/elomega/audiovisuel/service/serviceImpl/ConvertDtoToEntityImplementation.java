package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurRequest;
import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.enumeration.Role;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.service.ConvertDtoToEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConvertDtoToEntityImplementation implements ConvertDtoToEntity {
    /*@Autowired
    private ModelMapper modelMapper;*/
    @Override
    public Acteur convertActeurResquestToActeurEntity(ActeurRequest acteurRequest) {
        Acteur acteur = new Acteur();
        acteur.setFirstName(acteurRequest.getFirstName());
        acteur.setLastName(acteurRequest.getLastName());
        acteur.setDateDeNaissance(acteurRequest.getDateDeNaissance());
        acteur.setEmail(acteurRequest.getEmail());
        acteur.setRole(getRoleEnumName(acteurRequest.getRole()).name());
        acteur.setAuthorities(getRoleEnumName(acteurRequest.getRole()).getAuthorities());
        acteur.setUsername(acteurRequest.getUsername());
        acteur.setActive(Boolean.parseBoolean(acteurRequest.getIsActive()));
        acteur.setNotLocked(Boolean.parseBoolean(acteurRequest.getIsNotLocked()));
        acteur.setProfileImageUrl(acteurRequest.getProfileImageUrl());
        acteur.setJoinDate(new Date());
        acteur.setPassword(generatePassword());
        acteur.setUserId(generateUserId());
        return acteur;
    }

    @Override
    public Acteur convertActeurResponseToActeurEntity(ActeurResponse acteurResponse) {
        /*modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Acteur acteur = new Acteur();
        acteur = modelMapper.map(acteurResponse,Acteur.class);*/
        Acteur acteur = new Acteur();
        acteur.setId(acteurResponse.getId());
        acteur.setFirstName(acteurResponse.getFirstName());
        acteur.setLastName(acteurResponse.getLastName());
        acteur.setDateDeNaissance(acteurResponse.getDateDeNaissance());
        acteur.setEmail(acteurResponse.getEmail());
        acteur.setRole(getRoleEnumName(acteurResponse.getRole()).name());
        acteur.setAuthorities(getRoleEnumName(acteurResponse.getRole()).getAuthorities());
        acteur.setUsername(acteurResponse.getUsername());
        acteur.setActive(Boolean.parseBoolean(acteurResponse.getIsActive()));
        acteur.setNotLocked(Boolean.parseBoolean(acteurResponse.getIsNotLocked()));
        acteur.setProfileImageUrl(acteurResponse.getProfileImageUrl());
        acteur.setJoinDate(acteurResponse.getJoinDate());
        acteur.setUserId(acteurResponse.getUserId());
        return acteur;
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }
    private String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

}
