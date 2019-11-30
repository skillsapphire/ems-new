package com.skillsapphire.controller;

import com.skillsapphire.service.EmsCategoryService;

import java.util.Scanner;

public class EmsController {

    private Scanner input = new Scanner(System.in);
    private Integer userChoice;

    private EmsCategoryService emsCategoryService = EmsCategoryService.getCategoryService();

    public void displayMenu(){

        while(true){
            createMenu();
            switch (userChoice){
                case 1:
                    emsCategoryService.addCategory();
                    pressEnterKeyToContinue();
                    break;
                case 2:
                    emsCategoryService.updateCategory();
                    pressEnterKeyToContinue();
                    break;
                case 3:
                    emsCategoryService.deleteCategory();
                    pressEnterKeyToContinue();
                    break;
                case 4:
                    emsCategoryService.showAllCategories();
                    pressEnterKeyToContinue();
                    break;
                case 5:
                    emsCategoryService.showCategory();
                    pressEnterKeyToContinue();
                    break;
                case 0:
                    System.out.println("closing the app...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    pressEnterKeyToContinue();
                    break;
            }
        }

    }

    public void createMenu(){
        System.out.println("------EMS Menu-------");
        System.out.println("1. Add a new category");
        System.out.println("2. Update a category");
        System.out.println("3. Delete a category");
        System.out.println("4. Show all categories");
        System.out.println("5. Show details of a category");
        System.out.println("0. Close application");
        System.out.println("----------------------");
        System.out.print("Please enter your choice: ");
        userChoice = input.nextInt();
    }

    public void pressEnterKeyToContinue(){
        try{
          System.out.print("Press Enter to continue...");
          System.in.read();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
