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
public class ValueWithIndexes {
    private double value;
    private int iIndex;
    private int jIndex;

    public double getValue() {
        return value;
    }

    public int getiIndex() {
        return iIndex;
    }

    public int getjIndex() {
        return jIndex;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setiIndex(int iIndex) {
        this.iIndex = iIndex;
    }

    public void setjIndex(int jIndex) {
        this.jIndex = jIndex;
    }        
}
