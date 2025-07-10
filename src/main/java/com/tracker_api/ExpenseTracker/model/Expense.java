package com.tracker_api.ExpenseTracker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "expenses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    private String id;

    private String title;

    private Double amount;
    private String category;
    private LocalDate date;

    @Field("userId")
    private String userId;

    public Expense(String title, Double amount, String category, LocalDate date, String userId) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.userId = userId;
    }


}
