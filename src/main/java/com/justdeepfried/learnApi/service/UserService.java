package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.entity.UserEntity;
import com.justdeepfried.learnApi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(int id) {
        return userRepository.findById(id);
    }

    public void createIfNotCreated() {
        userRepository.createIfNotCreated();
    }

}
