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
import javax.faces.model.SelectItem;

/**
 *
 * @author Валик
 */
@ManagedBean
@SessionScoped
public class CriteriaBean implements Serializable {

    private static final List<Criteria> criteriaList = new ArrayList<Criteria>() {
        {
            add(new Criteria("1", "Минимаксный критерий"));
            add(new Criteria("2", "Критерий Сэвиджа"));
            add(new Criteria("3", "Критерий Байеса-Лапласа"));
            add(new Criteria("4", "Расширенный минимаксный критерий"));
            add(new Criteria("5", "Критерий произведений"));
            add(new Criteria("6", "Критерий Гермейера"));
            add(new Criteria("7", "Критерий Гурвица"));
            add(new Criteria("8", "Составной критерий Байеса-Лапласа минимаксный"));
        }
    };

    //содержание выбранных критериев
    private final List<String> selectedCriteriaList = new ArrayList<String>();
    private List<Criteria> selectedCriteriaList1 = new ArrayList<Criteria>();

    public List<Criteria> getCriteriaList() {
        return criteriaList;
    }

    public List<String> getSelectedCriteriaList() {
        return selectedCriteriaList;
    }

    public void setSelectedCriteriaList(List<String> criteriaList1) {
//        this.selectedCriteriaList = criteriaList;     
        for (int i = 0; i < criteriaList1.size(); i++) {
            for (int j = 0; j < criteriaList.size(); j++) {
                if (criteriaList1.get(i).equals(criteriaList.get(j).getValue())) {
                    selectedCriteriaList1.add(new Criteria(criteriaList1.get(i), criteriaList.get(j).getLabel()));
                    break;
                }                
            }            
        }
        СriteriaСalculation.getInstance().setSelectedList(selectedCriteriaList1);
    }
}
