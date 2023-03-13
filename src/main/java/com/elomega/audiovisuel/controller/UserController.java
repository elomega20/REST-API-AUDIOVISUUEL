package com.elomega.audiovisuel.controller;

import com.elomega.audiovisuel.dto.UserRequest;
import com.elomega.audiovisuel.dto.UserResponse;
import com.elomega.audiovisuel.model.User;
import com.elomega.audiovisuel.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users")
    public ResponseEntity<UserResponse> postUser(@RequestBody UserRequest userRequest) {
        return new  ResponseEntity<>(userService.postUser(userRequest),CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> getuser(@RequestParam("page") int page,@RequestParam("size") int size) {
        return new ResponseEntity<>(userService.getuser(page,size),OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(userResponse -> new ResponseEntity<>(userResponse,OK))
                .orElseGet(()-> new ResponseEntity<>(NOT_FOUND));
    }
    @PutMapping("/users")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserResponse userResponse) {
        return userService.updateUser(userResponse)
                .map(userResponse1 -> new ResponseEntity<>(userResponse1,OK))
                .orElseGet(()-> new ResponseEntity<>(NOT_FOUND));
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id) ? new ResponseEntity<>(OK) : new ResponseEntity<>(NOT_FOUND);
    }
}
