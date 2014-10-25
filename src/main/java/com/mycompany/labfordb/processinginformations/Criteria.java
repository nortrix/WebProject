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
public class Criteria {
    private String value;
    private String label;

    public Criteria(String value, String label) {
        this.value = value;
        this.label = label;
    }
    
    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }    
}
