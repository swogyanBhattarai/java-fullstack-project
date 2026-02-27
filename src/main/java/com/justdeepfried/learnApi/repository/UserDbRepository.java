package com.justdeepfried.learnApi.repository;

import com.justdeepfried.learnApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDbRepository extends JpaRepository<UserModel, Integer> {

}
