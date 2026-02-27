package com.justdeepfried.learnApi.controller;

import com.justdeepfried.learnApi.model.TransactionModel;
import com.justdeepfried.learnApi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService service) {
        this.transactionService = service;
    }

    @PostMapping("/{id}")
    public void addTransaction(@RequestBody TransactionModel transaction ,@PathVariable int id) {
        transactionService.addTransaction(transaction, id);
    }

}
