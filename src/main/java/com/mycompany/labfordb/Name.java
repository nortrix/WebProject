/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb;

import java.io.Serializable;

/**
 *
 * @author Валик
 */
public class Name implements Serializable {
    private String first;
    private String last;
    private Integer age;

    public Name(String first, String last, Integer age) {
        this.first = first;
        this.last = last;
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }
           
}
