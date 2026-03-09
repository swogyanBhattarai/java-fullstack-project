package com.justdeepfried.learnApi.dto;

import com.justdeepfried.learnApi.model.TransactionModel;
import com.justdeepfried.learnApi.model.UserModel;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse (
        int id,
        String username,
        List<String> roles,
        List<TransactionModel> transactions,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){
    public static UserResponse from(UserModel user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRoles(),
                user.getTransactions(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
