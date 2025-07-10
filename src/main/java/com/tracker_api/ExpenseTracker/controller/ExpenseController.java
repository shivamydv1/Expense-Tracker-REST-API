package com.tracker_api.ExpenseTracker.controller;

import com.tracker_api.ExpenseTracker.DTO.ExpenseDto;
import com.tracker_api.ExpenseTracker.model.Expense;
import com.tracker_api.ExpenseTracker.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public String addExpense(@RequestBody ExpenseDto dto) {
        return expenseService.addExpense(dto, dto.getUserId());
    }

    @GetMapping
    public List<ExpenseDto> getUserExpense(String userId) {
        return expenseService.getExpensesByUserId(userId);
    }

//    DashBoard
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboard(@RequestParam String userId) {
        return expenseService.getDashboard(userId);
    }
// end
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable String id , @RequestBody ExpenseDto dto) {
        return expenseService.updateExpense(id , dto);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable String id) {
        expenseService.deleteExpense(id);
    }
}
