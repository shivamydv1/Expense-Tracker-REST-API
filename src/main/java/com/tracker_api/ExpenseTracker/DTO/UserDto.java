package com.tracker_api.ExpenseTracker.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private  String name;
    private  String email;
    private  String password;
    private  Double monthlyBudget;
}
