package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.exception.UserNotFoundException;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserModel> getAll(Pageable pageable) {
        return repo.findAll(pageable).getContent();
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

}
