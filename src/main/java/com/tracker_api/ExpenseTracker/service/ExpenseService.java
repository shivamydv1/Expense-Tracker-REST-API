package com.tracker_api.ExpenseTracker.service;

import com.tracker_api.ExpenseTracker.DTO.ExpenseDto;
import com.tracker_api.ExpenseTracker.model.Expense;
import com.tracker_api.ExpenseTracker.model.User;
import com.tracker_api.ExpenseTracker.repository.ExpenseRepository;
import com.tracker_api.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    public String addExpense(ExpenseDto dto , String userId)  {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate startOfMonth = dto.getDate().withDayOfMonth(1);
        LocalDate endOfMonth = dto.getDate().withDayOfMonth(dto.getDate().lengthOfMonth());

        List<Expense> monthlyExpress = expenseRepository.findByUserIdAndDateBetween(userId , startOfMonth , endOfMonth);

        double totalSpent = monthlyExpress.stream().mapToDouble(Expense::getAmount).sum();

        double newTotal = totalSpent + dto.getAmount();
        Expense expense = new Expense(
                dto.getTitle(),
                dto.getAmount(),
                dto.getCategory(),
                dto.getDate(),
                userId
        );
       expenseRepository.save(expense);

        if (user.getMonthlyBudget() != null && newTotal > user.getMonthlyBudget()) {
            return "Alert: Budget limit exceeded! Total spent = " + newTotal;
        }


        if(totalSpent > user.getMonthlyBudget()) {
            return "Alert: Budget limit exceeded! Total spent = " + totalSpent;
        }

        return "Expense added successfully. Total spent = " + newTotal;
//        return "Expense added successfully";
    }

    public List<ExpenseDto> getExpensesByUserId(String userId) {
        return expenseRepository.findByUserId(userId)
                .stream()
                .map(e -> {
                    ExpenseDto dto = new ExpenseDto();
                    dto.setId(e.getId());
                    dto.setTitle(e.getTitle());
                    dto.setAmount(e.getAmount());
                    dto.setCategory(e.getCategory());
                    dto.setDate(e.getDate());
                    return dto;
                })
                .collect(Collectors.toList());
    }

//    DashBoard
    public Map<String, Object> getDashboard(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate now = LocalDate.now();
        LocalDate firstOfMonth = now.withDayOfMonth(1);

        List<Expense> thisMonthExpenses = expenseRepository
                .findByUserIdAndDateBetween(userId, firstOfMonth, now);

        double totalSpent = thisMonthExpenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : thisMonthExpenses) {
            categoryTotals.put(
                    e.getCategory(),
                    categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }

        List<String> topCategories = categoryTotals.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("totalSpent", totalSpent);
        result.put("budgetLimit", user.getMonthlyBudget());
        result.put("alert", totalSpent > user.getMonthlyBudget() ? "Budget limit exceeded!" : "Within budget ✅");
        result.put("topCategories", topCategories);

        return result;
    }
// End


    public Expense updateExpense(String id , ExpenseDto dto) {
        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDate(dto.getDate());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
