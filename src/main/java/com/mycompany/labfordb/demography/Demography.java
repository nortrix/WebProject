/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.demography;

/**
 *
 * @author Валик
 */
public class Demography {
    private int id;
    private String district;
    private int doctorsSecurity;
    private int birthrate;
    private int mortality;
    private int mortalityInTheWorkingAge;

    public int getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public int getDoctorsSecurity() {
        return doctorsSecurity;
    }

    public int getBirthrate() {
        return birthrate;
    }

    public int getMortality() {
        return mortality;
    }

    public int getMortalityInTheWorkingAge() {
        return mortalityInTheWorkingAge;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDoctorsSecurity(int doctorsSecurity) {
        this.doctorsSecurity = doctorsSecurity;
    }

    public void setBirthrate(int birthrate) {
        this.birthrate = birthrate;
    }

    public void setMortality(int mortality) {
        this.mortality = mortality;
    }

    public void setMortalityInTheWorkingAge(int mortalityInTheWorkingAge) {
        this.mortalityInTheWorkingAge = mortalityInTheWorkingAge;
    }    
}
