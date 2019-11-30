package com.skillsapphire.service;

import com.skillsapphire.model.Category;
import com.skillsapphire.repository.EmsRepository;

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
        /*showAllCategories();
        System.out.print("Choose a category which you want to update: ");
        Integer choice = input.nextInt();
        Category category = emsRepository.getCategories().get(choice-1);
        emsRepository.getCategories().remove(choice-1);
        input.nextLine();
        System.out.println("Enter new value for category name: ");
        String newCategoryName = input.nextLine();
        category.setName(newCategoryName);
        emsRepository.getCategories().add(choice-1,category);
        showAllCategories();*/

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
}
