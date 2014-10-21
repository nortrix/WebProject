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
public class PreparedDemography {
    private int id;
    private String district;
    private double doctorsSecurity;
    private double birthrate;
    private double mortality;
    private double mortalityInTheWorkingAge;

    public void setId(int id) {
        this.id = id;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setDoctorsSecurity(double doctorsSecurity) {
        this.doctorsSecurity = doctorsSecurity;
    }

    public void setBirthrate(double birthrate) {
        this.birthrate = birthrate;
    }

    public void setMortality(double mortality) {
        this.mortality = mortality;
    }

    public void setMortalityInTheWorkingAge(double mortalityInTheWorkingAge) {
        this.mortalityInTheWorkingAge = mortalityInTheWorkingAge;
    }

    public int getId() {
        return id;
    }

    public String getDistrict() {
        return district;
    }

    public double getDoctorsSecurity() {
        return doctorsSecurity;
    }

    public double getBirthrate() {
        return birthrate;
    }

    public double getMortality() {
        return mortality;
    }

    public double getMortalityInTheWorkingAge() {
        return mortalityInTheWorkingAge;
    }
}
