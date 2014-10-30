/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.processinginformations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean
@SessionScoped
public class ResultCalcBean implements Serializable {
    
    private String result;

    public String getResult() {
        List<Criteria> resultList = СriteriaСalculation.getInstance().getSelectedList();
        result = "";
        for (int i = 0; i < resultList.size(); i++) {
//            result = result + resultList.get(i).getCriteria() + "\n" + "Вывод: "
//                    + resultList.get(i).getMinOrMax() + " Объект: " + resultList.get(i).getNameObject()
//                    + " № = " + resultList.get(i).getId() + " Значение: " + resultList.get(i).getResult() + "\n\n";
            result = result + resultList.get(i).getLabel() + "\n";
        }
        return result;
    }
    
}
