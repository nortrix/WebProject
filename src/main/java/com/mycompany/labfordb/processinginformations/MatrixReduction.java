/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.processinginformations;

import com.mycompany.labfordb.demography.Demography;
import com.mycompany.labfordb.demography.PreparedDemography;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Валик
 */
public class MatrixReduction {

    private List<PreparedDemography> preparedDataList;

    //Приведение данных таблицы Демографии
    public List<PreparedDemography> demographyMatrixReduction(List<Demography> demographyDataList) {

//        System.out.println("Исходные данные: ");
//        for (int i = 0; i < demographyDataList.size(); i++) {
//            System.out.println(demographyDataList.get(i).getId() + ") " + demographyDataList.get(i).getDistrict()
//                    + "; " + demographyDataList.get(i).getDoctorsSecurity() + "; " + demographyDataList.get(i).getBirthrate()
//            + "; " + demographyDataList.get(i).getMortality() + "; " + demographyDataList.get(i).getMortalityInTheWorkingAge());            
//        }
        //ср. ар. каждой строки
        double[] arithmeticMeanNumerator = new double[demographyDataList.size()];
        //ср. ар. каждого столбца
        double[] arithmeticMeanDenominator = new double[4];
        //числители
        double[][] numerator = new double[demographyDataList.size()][4];
        //знаменатели
        double[][] denominator = new double[demographyDataList.size()][4];

        // Нахождение ср. ах. значения по горизонтали
        double temp;
        for (int i = 0; i < demographyDataList.size(); i++) {
            temp = demographyDataList.get(i).getDoctorsSecurity() + demographyDataList.get(i).getBirthrate() + demographyDataList.get(i).getMortality() + demographyDataList.get(i).getMortalityInTheWorkingAge();
            arithmeticMeanNumerator[i] = temp / 4;
        }
        // Завершение нахождения ср. ах. значения по горизонтали 

        // Нахождение ср. ах. значения по вертикале        
        for (int i = 0; i < arithmeticMeanDenominator.length; i++) {
            arithmeticMeanDenominator[i] = 0;
        }

        for (Demography demographyDataList1 : demographyDataList) {
            arithmeticMeanDenominator[0] += demographyDataList1.getDoctorsSecurity();
        }

        for (Demography demographyDataList1 : demographyDataList) {
            arithmeticMeanDenominator[1] += demographyDataList1.getBirthrate();
        }

        for (Demography demographyDataList1 : demographyDataList) {
            arithmeticMeanDenominator[2] += demographyDataList1.getMortality();
        }

        for (Demography demographyDataList1 : demographyDataList) {
            arithmeticMeanDenominator[3] += demographyDataList1.getMortalityInTheWorkingAge();
        }

        for (int i = 0; i < arithmeticMeanDenominator.length; i++) {
            arithmeticMeanDenominator[i] = arithmeticMeanDenominator[i] / demographyDataList.size();
        }
        // Завершение нахождения ср. ах. значения по вертикале 

        //Нахождение числителя путем разности каждого числа и ср. ар. по горизонтали
        for (int i = 0; i < numerator.length; i++) {
            numerator[i][0] = demographyDataList.get(i).getDoctorsSecurity() - arithmeticMeanNumerator[i];
            numerator[i][1] = demographyDataList.get(i).getBirthrate() - arithmeticMeanNumerator[i];
            numerator[i][2] = demographyDataList.get(i).getMortality() - arithmeticMeanNumerator[i];
            numerator[i][3] = demographyDataList.get(i).getMortalityInTheWorkingAge() - arithmeticMeanNumerator[i];
        }
        //Завершение нахождения числителя путем разности каждого числа и ср. ар. по горизонтали

        //Нахождение знаменателя путем разности каждого числа и ср. ар. по вертикале, 
        //нахождение квадратного корня из модуля полученного числа и затем
        //делением полученного числа на demographyDataList.size() (или n)
        for (int i = 0; i < denominator.length; i++) {
            denominator[i][0] = (Math.sqrt(Math.abs(demographyDataList.get(i).getDoctorsSecurity() - arithmeticMeanDenominator[0]))) / demographyDataList.size();
            denominator[i][1] = (Math.sqrt(Math.abs(demographyDataList.get(i).getBirthrate() - arithmeticMeanDenominator[1]))) / demographyDataList.size();
            denominator[i][2] = (Math.sqrt(Math.abs(demographyDataList.get(i).getMortality() - arithmeticMeanDenominator[2]))) / demographyDataList.size();
            denominator[i][3] = (Math.sqrt(Math.abs(demographyDataList.get(i).getMortalityInTheWorkingAge() - arithmeticMeanDenominator[3]))) / demographyDataList.size();
        }
        //Завершение нахождения знаменателя путем разности каждого числа и ср. ар. по вертикале, 
        //нахождение квадратного корня из модуля полученного числа и затем
        //делением полученного числа на demographyDataList.size() (или n)

        //Нахождение суммы по горизонтале и вертикале числа на позиции которой находимся.
        //??????????????????????????????????????????????????????????????????????
        //Завершение нахождения суммы по горизонтале и вертикале числа на позиции которой находимся.
        preparedDataList = new ArrayList<PreparedDemography>();

        //Деление числителя на знаменатель с проверкой на 0 знаменателя
        for (int i = 0; i < numerator.length; i++) {
            PreparedDemography demographyData = new PreparedDemography();
            demographyData.setId(demographyDataList.get(i).getId());
            demographyData.setDistrict(demographyDataList.get(i).getDistrict());

            if (denominator[i][0] == 0) {
                demographyData.setDoctorsSecurity(0);
            } else {
                demographyData.setDoctorsSecurity(numerator[i][0] / denominator[i][0]);
            }

            if (denominator[i][1] == 0) {
                demographyData.setBirthrate(0);
            } else {
                demographyData.setBirthrate(numerator[i][1] / denominator[i][1]);
            }

            if (denominator[i][2] == 0) {
                demographyData.setMortality(0);
            } else {
                demographyData.setMortality(numerator[i][2] / denominator[i][2]);
            }

            if (denominator[i][3] == 0) {
                demographyData.setMortalityInTheWorkingAge(0);
            } else {
                demographyData.setMortalityInTheWorkingAge(numerator[i][3] / denominator[i][3]);
            }

            preparedDataList.add(demographyData);
        }
        //Завершение деления числителя на знаменатель с проверкой на 0 знаменателя

//        System.out.println("Приведение данных: ");
//        for (int i = 0; i < preparedDataList.size(); i++) {
//            System.out.println(preparedDataList.get(i).getId() + ") " + preparedDataList.get(i).getDistrict()
//                    + "; " + preparedDataList.get(i).getDoctorsSecurity() + "; " + preparedDataList.get(i).getBirthrate()
//            + "; " + preparedDataList.get(i).getMortality() + "; " + preparedDataList.get(i).getMortalityInTheWorkingAge());            
//        }
        return preparedDataList;
    }

    private static MatrixReduction self = new MatrixReduction();

    public static MatrixReduction getInstance() {
        return self;
    }
}