package com.justdeepfried.learnApi.service;

import com.justdeepfried.learnApi.model.TransactionModel;
import com.justdeepfried.learnApi.model.UserModel;
import com.justdeepfried.learnApi.repository.TransactionRepository;
import com.justdeepfried.learnApi.repository.UserDbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactions;

    @Autowired
    UserDbRepository users;

    public void addTransaction(TransactionModel transactionModel, int id) {
        UserModel user = users.findById(id).orElse(new UserModel());
        transactionModel.setUser(user);
        transactionModel.setActive(true);
        transactions.save(transactionModel);
    }
}
