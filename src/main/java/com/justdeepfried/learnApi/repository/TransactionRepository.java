package com.justdeepfried.learnApi.repository;

import com.justdeepfried.learnApi.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer> {
}
