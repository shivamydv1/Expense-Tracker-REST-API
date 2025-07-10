package com.tracker_api.ExpenseTracker.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExpenseDto {
    private String id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
    private String userId;
}
