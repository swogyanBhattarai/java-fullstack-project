package com.justdeepfried.learnApi.controller;

import com.justdeepfried.learnApi.entity.UserEntity;
import com.justdeepfried.learnApi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private List<UserEntity> Users = new ArrayList<>();
    private int nextId;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        userService.createIfNotCreated();
        return userService.getAll();
    }

    @PostMapping
    public void addUser(@RequestBody UserEntity userEntity){
        userEntity.setId(nextId++);
        Users.add(userEntity);
    }

    @GetMapping("/{idd}")
    public UserEntity getUserById(@PathVariable ("idd") int id) {
        userService.createIfNotCreated();
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateUserById(@PathVariable int id, @RequestBody UserEntity updateEntity) {
        UserEntity update = null;
        try {
            update = Users.stream()
                        .filter(s -> s.getId() == id)
                        .findFirst()
                        .orElseThrow(NullPointerException::new);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        update.setName(updateEntity.getName());
        update.setAge(updateEntity.getAge());

        return update;
    }

}
