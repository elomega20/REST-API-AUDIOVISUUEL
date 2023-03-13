package com.elomega.audiovisuel.service.serviceImpl;

import com.elomega.audiovisuel.dto.ActeurResponse;
import com.elomega.audiovisuel.dto.UserRequest;
import com.elomega.audiovisuel.dto.UserResponse;
import com.elomega.audiovisuel.model.User;
import com.elomega.audiovisuel.repository.UserRepository;
import com.elomega.audiovisuel.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final ConvertDtoToEntityImplementation convertDtoToEntityImplementation;
    private final ConvertEntityToDtoImplementation convertEntityToDtoImplementation;
    @Override
    public UserResponse postUser(UserRequest userRequest) {
        User user =  convertUserRequestToUserEntity(userRequest);
        userRepository.saveAndFlush(user);
        return convertUserEntityToUserResponse(user);
    }

    @Override
    public Page<UserResponse> getuser(int page, int size) {
        List<UserResponse> userResponses = userRepository.findByRole("USER",PageRequest.of(page,size))
                .stream()
                .map(this::convertUserEntityToUserResponse)
                .collect(Collectors.toList());
        Page<UserResponse> userResponsesPages = new PageImpl<>(userResponses);
        return userResponsesPages;
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        if (userRepository.existsById(id))
            return Optional.of(convertUserEntityToUserResponse(userRepository.findById(id).get()));
        else
            return Optional.empty();
    }

    @Override
    public Optional<UserResponse> updateUser(UserResponse userResponse) {
        if (userRepository.existsById(userResponse.getId())){
            User user = convertUserResponseToUserEntity(userResponse);
            userRepository.saveAndFlush(user);
            return Optional.of(convertUserEntityToUserResponse(user));
        }else
            return Optional.empty();
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserResponse convertUserEntityToUserResponse(User user){
        return convertEntityToDtoImplementation.convertUserEntityToUserResponse(user);
    }
    private User convertUserRequestToUserEntity(UserRequest userRequest){
        return convertDtoToEntityImplementation.convertUserRequestToUserEntity(userRequest);
    }
    private User convertUserResponseToUserEntity(UserResponse userResponse){
        return convertDtoToEntityImplementation.convertUserResponseToUserEntity(userResponse);
    }
}
