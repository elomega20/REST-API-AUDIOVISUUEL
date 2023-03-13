package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.*;
import com.elomega.audiovisuel.enumeration.Role;
import com.elomega.audiovisuel.model.Acteur;
import com.elomega.audiovisuel.model.MaisonDeProduction;
import com.elomega.audiovisuel.model.User;
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

    @Override
    public MaisonDeProduction convertMaisonDeProductionRequestToMaisonDeProductionEntity(MaisonDeProductionRequest maisonDeProductionRequest) {
        MaisonDeProduction maisonDeProduction = new MaisonDeProduction();
        maisonDeProduction.setFirstName(maisonDeProductionRequest.getFirstName());
        maisonDeProduction.setLastName(maisonDeProductionRequest.getLastName());
        maisonDeProduction.setAnneeDeCreation(maisonDeProductionRequest.getAnneeDeCreation());
        maisonDeProduction.setEmail(maisonDeProductionRequest.getEmail());
        maisonDeProduction.setRole(getRoleEnumName(maisonDeProductionRequest.getRole()).name());
        maisonDeProduction.setAuthorities(getRoleEnumName(maisonDeProductionRequest.getRole()).getAuthorities());
        maisonDeProduction.setUsername(maisonDeProductionRequest.getUsername());
        maisonDeProduction.setActive(Boolean.parseBoolean(maisonDeProductionRequest.getIsActive()));
        maisonDeProduction.setNotLocked(Boolean.parseBoolean(maisonDeProductionRequest.getIsNotLocked()));
        maisonDeProduction.setProfileImageUrl(maisonDeProductionRequest.getProfileImageUrl());
        maisonDeProduction.setJoinDate(new Date());
        maisonDeProduction.setPassword(generatePassword());
        maisonDeProduction.setUserId(generateUserId());
        return maisonDeProduction;
    }

    @Override
    public MaisonDeProduction convertMaisonDeProductionResponseToMaisonDeProductionEntity(MaisonDeProductionResponse maisonDeProductionResponse) {
        MaisonDeProduction maisonDeProduction = new MaisonDeProduction();
        maisonDeProduction.setId(maisonDeProductionResponse.getId());
        maisonDeProduction.setFirstName(maisonDeProductionResponse.getFirstName());
        maisonDeProduction.setLastName(maisonDeProductionResponse.getLastName());
        maisonDeProduction.setAnneeDeCreation(maisonDeProductionResponse.getAnneeDeCreation());
        maisonDeProduction.setEmail(maisonDeProductionResponse.getEmail());
        maisonDeProduction.setRole(getRoleEnumName(maisonDeProductionResponse.getRole()).name());
        maisonDeProduction.setAuthorities(getRoleEnumName(maisonDeProductionResponse.getRole()).getAuthorities());
        maisonDeProduction.setUsername(maisonDeProductionResponse.getUsername());
        maisonDeProduction.setActive(Boolean.parseBoolean(maisonDeProductionResponse.getIsActive()));
        maisonDeProduction.setNotLocked(Boolean.parseBoolean(maisonDeProductionResponse.getIsNotLocked()));
        maisonDeProduction.setProfileImageUrl(maisonDeProductionResponse.getProfileImageUrl());
        maisonDeProduction.setJoinDate(maisonDeProductionResponse.getJoinDate());
        maisonDeProduction.setUserId(maisonDeProductionResponse.getUserId());
        return maisonDeProduction;
    }

    @Override
    public User convertUserRequestToUserEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setRole(getRoleEnumName(userRequest.getRole()).name());
        user.setAuthorities(getRoleEnumName(userRequest.getRole()).getAuthorities());
        user.setUsername(userRequest.getUsername());
        user.setActive(Boolean.parseBoolean(userRequest.getIsActive()));
        user.setNotLocked(Boolean.parseBoolean(userRequest.getIsNotLocked()));
        user.setProfileImageUrl(userRequest.getProfileImageUrl());
        user.setJoinDate(new Date());
        user.setPassword(generatePassword());
        user.setUserId(generateUserId());
        return user;
    }

    @Override
    public User convertUserResponseToUserEntity(UserResponse userResponse) {
        User user = new User();
        user.setId(userResponse.getId());
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setEmail(userResponse.getEmail());
        user.setRole(getRoleEnumName(userResponse.getRole()).name());
        user.setAuthorities(getRoleEnumName(userResponse.getRole()).getAuthorities());
        user.setUsername(userResponse.getUsername());
        user.setActive(Boolean.parseBoolean(userResponse.getIsActive()));
        user.setNotLocked(Boolean.parseBoolean(userResponse.getIsNotLocked()));
        user.setProfileImageUrl(userResponse.getProfileImageUrl());
        user.setJoinDate(userResponse.getJoinDate());
        user.setUserId(userResponse.getUserId());
        return user;
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
