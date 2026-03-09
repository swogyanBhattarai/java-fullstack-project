package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.dto.PageResponse;
import com.justdeepfried.learnApi.dto.UserResponse;
import com.justdeepfried.learnApi.exception.UserNotFoundException;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import com.justdeepfried.learnApi.specification.UserSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDbRepository repo;
    private AuthenticationManager auth;
    private JWTService jwtService;

    public UserService(UserDbRepository repo, AuthenticationManager auth, JWTService jwtService) {
        this.repo = repo;
        this.auth = auth;
        this.jwtService = jwtService;
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public PageResponse<UserResponse> getAllSearch(Pageable pageable, String search) {
        Specification<UserModel> specification = UserSpecification.getSpecification(search);
        Page<UserResponse> page = repo.findAll(specification, pageable).map(UserResponse::from);
        return PageResponse.from(page);
    }

    public UserModel findById(int id) {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found!"));
    }

    public void addUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }

    public void updateUser(int id, UserModel updatedUser) {
        UserModel existing = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found!"));
        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(updatedUser.getPassword());
        repo.save(existing);
    }

    public void deleteUser(int id) {
        if (!repo.existsById(id)) {
            throw new UserNotFoundException("User with id: " + id + " not found!");
        }
        repo.deleteById(id);
    }

//    public void createIfNotCreated() {
//        userRepository.createIfNotCreated();
//    }

    public String verify(UserModel user) {
        Authentication authentication = auth.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "Failure";
    }

    public PageResponse<UserResponse> getAll(Pageable pageable) {
        Page<UserResponse> page = repo.findAll(pageable).map(UserResponse::from);
        return PageResponse.from(page);
    }
}
