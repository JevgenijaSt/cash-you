package com.example.cash_you.models;

public class Category extends BaseEntity{
    public Category(String name) {
        super(name);
    }

    public void Display() {
        System.out.printf("Categories name %s", super.GetName());
        System.out.printf("Current balance %s", super.GetCurrentBalance());
    }
}
