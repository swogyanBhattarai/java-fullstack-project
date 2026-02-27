package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.model.TransactionModel;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Autowired
    UserDbRepository repo;

    public List<UserModel> getAll() {
        return repo.findAll();
    }

    public UserModel findById(int id) {
        return repo.findById(id).orElse(new UserModel());
    }

    public void addUser(UserModel user) {
        repo.save(user);
    }

    public void updateUser(int id, UserModel updatedUser) {
        UserModel existing = repo.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        existing.setUsername(updatedUser.getUsername());
        existing.setPassword(updatedUser.getPassword());
        repo.save(existing);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

//    public void createIfNotCreated() {
//        userRepository.createIfNotCreated();
//    }

}
