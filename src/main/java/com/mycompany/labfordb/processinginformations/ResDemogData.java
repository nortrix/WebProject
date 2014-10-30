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
public class ResDemogData {
    private int id;
    private String criteri;
    private String minOrMax;
    private String nameObject;
    private Double reslt;

    public int getId() {
        return id;
    }

    public String getCriteri() {
        return criteri;
    }

    public String getMinOrMax() {
        return minOrMax;
    }

    public String getNameObject() {
        return nameObject;
    }

    public Double getReslt() {
        return reslt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCriteri(String criteri) {
        this.criteri = criteri;
    }

    public void setMinOrMax(String minOrMax) {
        this.minOrMax = minOrMax;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public void setReslt(Double reslt) {
        this.reslt = reslt;
    }
    
}
