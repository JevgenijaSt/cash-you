package com.example.cash_you.data;

import android.graphics.Color;
import com.example.cash_you.models.Category;
import com.example.cash_you.models.Transaction;

import java.util.ArrayList;

public class CategoryManager
{
    private static CategoryManager instance = new CategoryManager();

    //Categories
    private ArrayList<Category> _categoriesIncome;
    private ArrayList<Category> _categoriesExpense;

    //Constructor and Init
    private CategoryManager(){}
    public static CategoryManager getInstance(){ return instance; }
    public void initialize(){
        _categoriesExpense = new ArrayList<>();
    }


    //Default categories
    public void LoadDefaultCategories(String activityType){
        switch (activityType) {
            case "Expense":
                AddCategory("Groceries", Color.argb(255, 0, 0, 255));
                AddCategory("Fast Food", Color.argb(255, 0, 20, 200));
                AddCategory("Restaurant", Color.argb(255, 50, 50, 150));
                AddCategory("Snacks", Color.argb(255, 50, 80, 150));
                AddCategory("Alcohol", Color.argb(255, 60, 60, 60));
                AddCategory("Rent", Color.argb(255, 200, 0, 0));
                AddCategory("Mortgage", Color.argb(255, 180, 30, 0));
                AddCategory("Hotel", Color.argb(255, 180, 70, 50));
                break;
            case "Income":
                AddCategory("Salary", Color.argb(255, 0, 0, 255));
                AddCategory("Rent", Color.argb(255, 0, 20, 200));
                AddCategory("Money debt", Color.argb(255, 50, 50, 150));
                break;
        }
    }

    //Management
    public Category AddCategory(Category category) {
        if (category != null) {
            Category cat = GetCategory(category.GetID());
            if (cat != null){ //Update
                cat.SetTitle(category.GetTitle());
                cat.SetColor(category.GetColor()); }
            else { //Add new
                _categoriesExpense.add(category);

            }
        }
        return category;
    }

    public Category AddCategory(String title, int color){
        Category cat = GetCategory(title);
        if (cat != null){ //Update just color
            cat.SetColor(color); }
        else {
            cat = new Category(title, color);
            AddCategory(cat);
        }
        return cat;
    }

    public void RemoveCategory(Category category) { _categoriesExpense.remove(category); }
    public void RemoveCategory(String title) {
        for (int i = 0; i < _categoriesExpense.size(); i++) {
            if (_categoriesExpense.get(i).GetTitle().equals(title)) {
                RemoveCategory(_categoriesExpense.get(i));
            }
        }
    }
    public void RemoveAllCategories() {
            _categoriesExpense.clear();
        }


    public Category GetCategory(int ID){
        // Short circuit for deleted category
        if (ID == Category.Deleted.GetID()){ return Category.Deleted; }

        for (Category cat : _categoriesExpense) {
            if (cat.GetID() == ID) { return cat; }
        }
        return null;
    }
    public Category GetCategory(String title) {
        for (Category cat : _categoriesExpense) {
            if (cat.GetTitle().equals(title)) { return cat; }
        }
        return null;
    }

    public ArrayList<Category> GetCategories() { return _categoriesExpense; }
    public ArrayList<String> GetCategoriesTitles(){
        ArrayList<String> arr = new ArrayList<>();
        for (Category cat : _categoriesExpense) {
            arr.add(cat.GetTitle());
        }
        return arr;
    }

    public int GetCategoriesCount() { return _categoriesExpense.size(); }

}
