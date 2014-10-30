/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.processinginformations;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Валик
 */
public class ResultToList {
    
    private List<ResDemogData> resultList = new ArrayList<ResDemogData>();
    
    public void setResultToList(int id, String criteria, String minOrMax, double result, String nameDistrict) {
        ResDemogData resDem = new ResDemogData();
        resDem.setId(id);
        resDem.setCriteri(criteria);
        resDem.setMinOrMax(minOrMax);
        resDem.setReslt(result);
        resDem.setNameObject(nameDistrict);
        
        resultList.add(resDem);
    }

    public List<ResDemogData> getResultList() {
        return resultList;
    }
    
    private ResultToList() {        
    }
    private static ResultToList self = new ResultToList();
    public static ResultToList getInstance() {
        return self;
    }
}
