package com.justdeepfried.learnApi.controller;

import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void addUser(@RequestBody UserModel userModel){
        userService.addUser(userModel);
    }

    @GetMapping("/{idd}")
    public UserModel getUserById(@PathVariable ("idd") int id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable int id, @RequestBody UserModel updateEntity) {
       userService.updateUser(id, updateEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
