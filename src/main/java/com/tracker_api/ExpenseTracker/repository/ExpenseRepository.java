package com.tracker_api.ExpenseTracker.repository;

import com.tracker_api.ExpenseTracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> findByUserId(String userId);

    List<Expense> findByUserIdAndDateBetween(String userId, LocalDate startOfMonth, LocalDate endOfMonth);
}
