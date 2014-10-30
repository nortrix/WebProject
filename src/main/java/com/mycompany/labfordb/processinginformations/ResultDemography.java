/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.processinginformations;

/**
 *
 * @author Валик
 */
public class ResultDemography {
    private int id;
    private String criteria;
    private String minOrMax;
    private String nameObject;
    private Double result;

    public int getId() {
        return id;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getMinOrMax() {
        return minOrMax;
    }

    public String getNameObject() {
        return nameObject;
    }

    public Double getResult() {
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public void setMinOrMax(String minOrMax) {
        this.minOrMax = minOrMax;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public void setResult(Double result) {
        this.result = result;
    }
    
}
