package com.skillsapphire.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
