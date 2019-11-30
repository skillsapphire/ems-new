package com.skillsapphire.service;

import com.skillsapphire.model.Expense;
import com.skillsapphire.repository.EmsRepository;
import com.skillsapphire.util.EmsUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class EmsReportService {

    private static EmsReportService emsReportService;
    private EmsRepository emsRepository = EmsRepository.getEmsRepository();

    private EmsReportService(){}

    public static EmsReportService getEmsReportService(){

        if(emsReportService == null){
            emsReportService = new EmsReportService();
        }
        return emsReportService;
    }

    public Map<String, Float> categoryWiseExpenseReport(){

        List<Expense> expenseList = emsRepository.getExpenses();
        Map<String, Float> categoryReport = new TreeMap<>();
        Expense expense = null;

        for(int i=0; i<expenseList.size(); i++){
            expense = expenseList.get(i);
            String categoryname = EmsUtil.getCategoryNameWithCategoryId(expense.getCategoryId(),emsRepository.getCategories());
            // if category exist than tak out the total for it and add the amount
            if(categoryReport.containsKey(categoryname)){
                Float total = categoryReport.get(categoryname);
                total = total+expense.getAmount();
                // update the new total for the existing category
                categoryReport.put(categoryname,total);
            }else{
                // this category does not exist, so new category added
                categoryReport.put(categoryname,expense.getAmount());
            }
        }
        return categoryReport;
    }
}
