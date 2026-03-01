package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.model.UserPrincipal;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    // A custom UserDetailsService is required to authenticate the users for database access.
    // Take username and password from db using loadUserByUsername.
    // This custom UserDetailService is injected into the SecurityConfig so that it gets called instead of the default UserDetailService

    private UserDbRepository userRepo;

    public CustomUserDetailService(UserDbRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user);
    }
}
