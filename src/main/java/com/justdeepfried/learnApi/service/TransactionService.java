package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.exception.TransactionNotFoundException;
import com.justdeepfried.learnApi.model.TransactionModel;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.repository.TransactionRepository;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repo;

    @Autowired
    UserDbRepository users;

    public void addTransaction(TransactionModel transactionModel, int id) {
        UserModel user = users.findById(id).orElse(new UserModel());
        transactionModel.setUser(user);
        transactionModel.setActive(true);
        repo.save(transactionModel);
    }

    public TransactionModel getById(long id) {
        return repo.findById((int) id).orElseThrow(() -> new TransactionNotFoundException("Transaction with id: " + id + " not found!"));
    }
}
