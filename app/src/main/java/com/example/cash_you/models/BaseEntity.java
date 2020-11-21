package com.example.cash_you.models;

public abstract class BaseEntity {
    private String name;
    private Double currentBalance;

    public BaseEntity(String name) {
        this.name = name;
        this.currentBalance = 0.0;
    }

    public String GetName() {
        return name;
    }
    public Double GetCurrentBalance() {
        return currentBalance;
    }

    public String ChangeName(String newName) {
        this.name = newName;
        return name;
    }
    public Double IncreaseBalance(Double amount) {
        this.currentBalance += amount;
        return currentBalance;
    }
    public Double ReduceBalance(Double amount) {
        this.currentBalance -= amount;
        return currentBalance;
    }
}
