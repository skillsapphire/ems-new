package com.skillsapphire.service;

import com.skillsapphire.model.Category;
import com.skillsapphire.model.Expense;
import com.skillsapphire.repository.EmsRepository;
import com.skillsapphire.util.EmsUtil;

import java.util.*;

public class EmsCategoryService {

    private Scanner input = new Scanner(System.in);
    private static EmsCategoryService emsCategoryService;
    private EmsRepository emsRepository = EmsRepository.getEmsRepository();
    private EmsReportService emsReportService = EmsReportService.getEmsReportService();

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

        showAllExpenses();
        System.out.print("Choose an expense to update: ");
        Integer choice = input.nextInt();

        // Flush the extra newline, as its gets added to the stream after taking any numeric value as input from scanner class
        input.nextLine();

        Expense expense = emsRepository.getExpenses().get(choice-1);
        emsRepository.getExpenses().remove(choice-1);

        // Questionaire what to update
        System.out.println("What do you want to update, choose from below: ");
        System.out.print("1. Amount, 2. Date, 3. Remark, 4. Category :");

        choice = input.nextInt();

        // Flush the extra newline, as its gets added to the stream after taking any numeric value as input from scanner class
        input.nextLine();

        if(choice == 1){

            System.out.println("Current expense amount is: "+expense.getAmount());
            System.out.print("Enter new expense amount: ");
            Float amount = input.nextFloat();
            expense.setAmount(amount);

        }else if(choice == 2){

            System.out.println("Current expense date is: "+expense.getDate());
            System.out.print("Enter new expense date(DD/MM/YYYY): ");
            String dateInString = input.nextLine();
            Date date = EmsUtil.convertStringToDate(dateInString,"dd/MM/yyyy");
            expense.setDate(date);

        }else if(choice == 3){

            System.out.println("Current expense remark is: "+expense.getRemark());
            System.out.print("Enter new expense remark: ");
            String remark = input.nextLine();
            expense.setRemark(remark);

        }else if(choice == 4){

            String catName = EmsUtil.getCategoryNameWithCategoryId(expense.getCategoryId(), emsRepository.getCategories());
            System.out.println("Current expense category is: "+catName);

            System.out.println("Choose new expense category: ");
            showAllCategories();
            choice = input.nextInt();

            Category category = emsRepository.getCategories().get(choice-1);
            expense.setCategoryId(category.getId());

        }
        emsRepository.getExpenses().add(choice-1,expense);
        System.out.println("Below is the list of updated expense: ");
        showAllExpenses();

    }
    public void deleteExpense(){

        showAllExpenses();
        System.out.print("Choose an expense to delete: ");
        Integer choice = input.nextInt();

        emsRepository.getExpenses().remove(choice-1);
        System.out.println("Expense was successfully deleted!");

        showAllExpenses();

    }
    public void showAnExpense(){
        showAllExpenses();
        System.out.print("Choose an expense for showing details: ");
        Integer choice = input.nextInt();
        Expense expense = emsRepository.getExpenses().get(choice-1);
        System.out.println("Below is the details of the chosen expense: ");
        String categoryName = EmsUtil.getCategoryNameWithCategoryId(expense.getCategoryId(), emsRepository.getCategories());
        String expDateInString = EmsUtil.convertDateToString(expense.getDate(),"dd/MM/yyyy");
        System.out.println("Expense amount: "+expense.getAmount()+", Expense date: "+expDateInString+", Expense remark: "+expense.getRemark()+", Expense category: "+categoryName);
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

    public void showCategoryWiseReport(){
        System.out.println("Category wise expense: ");
        Map<String,Float> reportMap = emsReportService.categoryWiseExpenseReport();
        Set<String> categorynames = reportMap.keySet();
        Float grandTotal = 0.0F;
        for (String catName : categorynames){
            Float categoryWiseTotal = reportMap.get(catName);
            grandTotal = grandTotal + categoryWiseTotal;
            System.out.println(catName+" - "+categoryWiseTotal);
        }
        System.out.println("Grand total for all categories is "+grandTotal);
    }
}
