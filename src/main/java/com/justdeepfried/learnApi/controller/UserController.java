package com.justdeepfried.learnApi.controller;

import com.justdeepfried.learnApi.dto.PageResponse;
import com.justdeepfried.learnApi.dto.UserResponse;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public PageResponse<UserResponse> getAllUsers(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "5") int pageSize,
                                                  @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                  @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                  @RequestParam(required = false) String search) {
        Sort sort = null;
        if (sortDir.equalsIgnoreCase("ASC")) {
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }
        if (search != null) {
            return userService.getAllSearch(PageRequest.of(pageNumber - 1, pageSize, sort), search);
        } else {
            return userService.getAll(PageRequest.of(pageNumber - 1, pageSize, sort));
        }

    }

    @PostMapping("/login")
    public String verify(@RequestBody UserModel user) {
        return userService.verify(user);
    }

    @GetMapping("/session")
    public String getSessionID(HttpServletRequest request) {
        return "Current Session ID: " + request.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping
    public void addUser(@RequestBody @Valid UserModel userModel){
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
