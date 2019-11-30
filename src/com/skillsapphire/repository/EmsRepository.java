package com.skillsapphire.repository;

import com.skillsapphire.model.Category;

import java.util.ArrayList;
import java.util.List;

public class EmsRepository {

    private List<Category> categories = new ArrayList<Category>();
    private static EmsRepository emsRepository;

    private EmsRepository(){}

    public static EmsRepository getEmsRepository(){
        if(emsRepository == null){
            emsRepository = new EmsRepository();
        }
        return emsRepository;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
