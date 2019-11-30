package com.skillsapphire.util;

import com.skillsapphire.model.Category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmsUtil {

    public static Date convertStringToDate(String dateInString, String dateFormat){

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            Date date = sdf.parse(dateInString);
            return date;
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        return null;
    }

    public static String getCategoryNameWithCategoryId(Long categoryId, List<Category> categoryList){
        String catName = null;
        for(Category category : categoryList){
            if(category.getId() == categoryId){
                catName = category.getName();
                break;
            }
        }
        return catName;
    }

    public static String convertDateToString(Date date, String format){

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
