package com.justdeepfried.learnApi.repository;

import com.justdeepfried.learnApi.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Spring Data JPA Repo layers are created as an interface.
// They take <What kind of data is being stored, What it's primary key is>, here the data being stored is TransactionModel, and it's primary key transaction_id is an Integer.
@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
}
