package com.tracker_api.ExpenseTracker.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String name;


    private String email;

    private String password;

    private Double monthlyBudget;

    private List<Expense> expenses;

    public User(String name, String email, String password, Double monthlyBudget) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.monthlyBudget = monthlyBudget;
    }
}
