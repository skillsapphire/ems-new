package com.skillsapphire.service;

import com.skillsapphire.model.Category;
import com.skillsapphire.model.Expense;
import com.skillsapphire.repository.EmsRepository;
import com.skillsapphire.util.EmsUtil;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmsCategoryService {

    private Scanner input = new Scanner(System.in);
    private static EmsCategoryService emsCategoryService;
    private EmsRepository emsRepository = EmsRepository.getEmsRepository();

    private EmsCategoryService(){}

    public static EmsCategoryService getCategoryService(){
        if(emsCategoryService == null) {
            emsCategoryService = new EmsCategoryService();
        }
        return emsCategoryService;
    }

    public void addCategory(){
        System.out.print("Enter category name: ");
        String categoryName = input.nextLine();
        Category category = new Category();
        category.setName(categoryName);
        emsRepository.getCategories().add(category);
        System.out.println("Category added sucessfully!");
    }
    public void updateCategory(){

        showAllCategories();
        System.out.print("Choose a category which you want to update: ");
        Integer choice = input.nextInt();

        Category category = emsRepository.getCategories().get(choice-1);
        emsRepository.getCategories().remove(choice-1);
        // to flush the input stream extra new line
        input.nextLine();
        System.out.print("Enter new value for the category name: ");
        String newCatName = input.nextLine();
        category.setName(newCatName);

        emsRepository.getCategories().add(choice-1,category);
        System.out.println("Below is the list of updated categories: ");
        showAllCategories();


    }
    public void deleteCategory(){
        showAllCategories();
        System.out.print("Choose a category for deletion: ");
        Integer choice = input.nextInt();
        emsRepository.getCategories().remove(choice-1);
        System.out.println("Category was successfully deleted!");
        showAllCategories();
    }
    public void showCategory(){
        showAllCategories();
        System.out.print("Choose a category for showing details: ");
        Integer choice = input.nextInt();
        Category category = emsRepository.getCategories().get(choice-1);
        System.out.print("Below is the details of the chosen category: ");
        System.out.println("Category name: "+category.getName()+", Category Id: "+category.getId());
    }
    public void showAllCategories(){
        List<Category> categories = emsRepository.getCategories();
        System.out.println("Showing all categories: ");
        for(int i=0; i<categories.size(); i++){
            System.out.println((i+1)+". "+"Category name: "+categories.get(i).getName()+", Category Id: "+categories.get(i).getId());
        }
    }

    //// Expense related operations
    public void addExpense(){
        System.out.println("Enter the expense details: ");
        showAllCategories();
        System.out.print("Choose a category from above list: ");
        Integer catChoice = input.nextInt();
        Category selectedCat = emsRepository.getCategories().get(catChoice-1);

        System.out.print("Enter expense amount: ");
        Float expAmount = input.nextFloat();
        // Flush the extra newline, as its gets added to the stream after taking any numeric value as input from scanner class
        input.nextLine();

        System.out.print("Enter expense remark: ");
        String expRemark = input.nextLine();

        System.out.print("Enter expense date (DD/MM/YYYY - 20/11/2019): ");
        String dateInString = input.nextLine();

        Expense expense = new Expense();
        expense.setCategoryId(selectedCat.getId());
        expense.setAmount(expAmount);
        expense.setRemark(expRemark);

        Date expDate = EmsUtil.convertStringToDate(dateInString,"dd/MM/yyyy");
        expense.setDate(expDate);

        emsRepository.getExpenses().add(expense);
        System.out.println("Expense added successfully!");

    }
    public void updateExpense(){
        System.out.print("updateExpense");
    }
    public void deleteExpense(){
        System.out.print("deleteExpense");
    }
    public void showAnExpense(){
        System.out.print("showAnExpense");
    }
    public void showAllExpenses(){

        System.out.println("Showing all expenses: ");
        List<Expense> expenseList = emsRepository.getExpenses();
        Expense expense = null;
        for(int i=0; i<expenseList.size(); i++){
            expense = expenseList.get(i);
            String categoryName = EmsUtil.getCategoryNameWithCategoryId(expense.getCategoryId(), emsRepository.getCategories());
            String expDateInString = EmsUtil.convertDateToString(expense.getDate(),"dd/MM/yyyy");
            System.out.println((i+1)+". "+"Expense amount: "+expense.getAmount()+", Expense date: "+expDateInString+", Expense remark: "+expense.getRemark()+", Expense category: "+categoryName);
        }
    }
}
