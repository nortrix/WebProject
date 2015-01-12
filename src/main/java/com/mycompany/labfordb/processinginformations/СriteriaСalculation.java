/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.processinginformations;

import com.mycompany.labfordb.demography.DemographyDao;
import com.mycompany.labfordb.demography.PreparedDemography;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Валик
 */
public class СriteriaСalculation {
    
    private static List<Criteria> selectedList1 = new ArrayList<Criteria>();
    private List<ResultDemography> resultList = new ArrayList<ResultDemography>();
    private double[] probArray = new double[4];
    private double[] probGermeierArray = new double[4];
    private double degreeOfOptimism, acceptableRisk;

    public void setDegreeOfOptimism(double degreeOfOptimism) {
        this.degreeOfOptimism = degreeOfOptimism;
    }
    
    public void setAcceptableRisk(double acceptableRisk) {
        this.acceptableRisk = acceptableRisk;
    }
    
    public void setProbArray(double[] probArray) {
        this.probArray = probArray;
    } 
    
    public void setProbGermeierArray(double[] probGermeierArray) {
        this.probGermeierArray = probGermeierArray;
    }
    
    public List<Criteria> getSelectedList() {
        return selectedList1;
    }
    
    public List<ResultDemography> getResultList() {
        return resultList;
    }
        
    public void setSelectedList(List<Criteria> selectedCriteriaList1) {
        selectedList1 = selectedCriteriaList1;
        
        List<PreparedDemography> demographyDataList = MatrixReduction.getInstance().demographyMatrixReduction(DemographyDao.getInstance().getDemographyData());
        for (int i = 0; i < selectedList1.size(); i++) {
            if (selectedList1.get(i).getValue().equals("1")) {
                criteriiMinimacsnii(demographyDataList);
                System.out.println("1");
            }
            if (selectedList1.get(i).getValue().equals("2")) {
                criteriiSevidj(demographyDataList);
                System.out.println("2");
            }
            if (selectedList1.get(i).getValue().equals("3")) {
                criteriiBaiesaLaplasa(demographyDataList, probArray);
                System.out.println("3");
            }
            if (selectedList1.get(i).getValue().equals("4")) {
                criteriiRasshirenniiMM(demographyDataList);
                System.out.println("4");
            }
            if (selectedList1.get(i).getValue().equals("5")) {
                criteriiProizvedenii(demographyDataList);
                System.out.println("5");
            }
            if (selectedList1.get(i).getValue().equals("6")) {
                criteriiGermeiera(demographyDataList, probGermeierArray);
                System.out.println("6");
            }
            if (selectedList1.get(i).getValue().equals("7")) {
                criteriiGurvitsa(demographyDataList, degreeOfOptimism);
                System.out.println("7");
            }
            if (selectedList1.get(i).getValue().equals("8")) {
                criteriiSostavnoiBaiesaLaplasaMM(demographyDataList, acceptableRisk);
                System.out.println("8");
            }
        }
    }
 
    public void criteriiMinimacsnii(List<PreparedDemography> demographyDataList) {
        double[] min = new double[demographyDataList.size()];
        
        double min1 = demographyDataList.get(0).getDoctorsSecurity();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (min1 > demographyDataList.get(i).getDoctorsSecurity()) {
                min1 = demographyDataList.get(i).getDoctorsSecurity();
            }
            if (min1 > demographyDataList.get(i).getBirthrate()) {
                min1 = demographyDataList.get(i).getBirthrate();
            }
            if (min1 > demographyDataList.get(i).getMortality()) {
                min1 = demographyDataList.get(i).getMortality();
            }
            if (min1 > demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                min1 = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
            
            min[i] = min1;
        }
        
        double max = min[0];
        int index = 0;
        for (int i = 0; i < min.length; i++) {
            if (max < min[i]) {
                max = min[i];
                index = i;
            }            
        }
        
        System.out.println("criteriiMinimacsnii max = " + max + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Минимаксный критерий", 
                "max", max, demographyDataList.get(index).getDistrict());
    }
    
    public void criteriiSevidj(List<PreparedDemography> demographyDataList) {
        double maxForColumn;
        //максимальное изкаждого столбца
        double[] maxForColums = new double[4];
        
        maxForColumn = demographyDataList.get(0).getDoctorsSecurity();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (maxForColumn < demographyDataList.get(i).getDoctorsSecurity()) {
                maxForColumn = demographyDataList.get(i).getDoctorsSecurity();
            }
        }
        maxForColums[0] = maxForColumn;
        
        maxForColumn = demographyDataList.get(0).getBirthrate();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (maxForColumn < demographyDataList.get(i).getBirthrate()) {
                maxForColumn = demographyDataList.get(i).getBirthrate();
            }
        }
        maxForColums[1] = maxForColumn;
        
        maxForColumn = demographyDataList.get(0).getMortality();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (maxForColumn < demographyDataList.get(i).getMortality()) {
                maxForColumn = demographyDataList.get(i).getMortality();
            }
        }
        maxForColums[2] = maxForColumn;
        
        maxForColumn = demographyDataList.get(0).getMortalityInTheWorkingAge();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (maxForColumn < demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                maxForColumn = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
        }
        maxForColums[3] = maxForColumn;
        
        //!!!!!!!!!!!!!!!!!!!
        
        //отнимаю от максимума соответствующего столбца значение на котором нахожусь 
        List<PreparedDemography> tempDemographyDataList = new ArrayList<PreparedDemography>();
        
        for (int i = 0; i < demographyDataList.size(); i++) {
            
            PreparedDemography prepDem = new PreparedDemography();
            
            prepDem.setId(demographyDataList.get(i).getId());
            prepDem.setDistrict(demographyDataList.get(i).getDistrict());
            prepDem.setDoctorsSecurity(maxForColums[0] - demographyDataList.get(i).getDoctorsSecurity());
            prepDem.setBirthrate(maxForColums[1] - demographyDataList.get(i).getBirthrate());
            prepDem.setMortality(maxForColums[2] - demographyDataList.get(i).getMortality());
            prepDem.setMortalityInTheWorkingAge(maxForColums[3] - demographyDataList.get(i).getMortalityInTheWorkingAge());
            
            tempDemographyDataList.add(prepDem);
        }
        
        //!!!!!!!!!
        
        //Нахождение максимум из каждой строки tempDemographyDataList
        double maxForRows[] = new double[tempDemographyDataList.size()];
        double max = tempDemographyDataList.get(0).getDoctorsSecurity();
        for (int i = 0; i < tempDemographyDataList.size(); i++) {
            if (max < tempDemographyDataList.get(i).getDoctorsSecurity()) {
                max = tempDemographyDataList.get(i).getDoctorsSecurity();
            }
            if (max < tempDemographyDataList.get(i).getBirthrate()) {
                max = tempDemographyDataList.get(i).getBirthrate();
            }
            if (max < tempDemographyDataList.get(i).getMortality()) {
                max = tempDemographyDataList.get(i).getMortality();
            }
            if (max < tempDemographyDataList.get(i).getMortalityInTheWorkingAge()) {
                max = tempDemographyDataList.get(i).getMortalityInTheWorkingAge();
            }
            
            maxForRows[i] = max;
        }
        
        double min = maxForRows[0];
        int index = 0;
        for (int i = 0; i < maxForRows.length; i++) {
            if (min > maxForRows[i]) {
                min = maxForRows[i];
                index = i;
            }            
        }
        
        System.out.println("criteriiSevidj min = " + min + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Критерий Сэвиджа", 
                "min", min, demographyDataList.get(index).getDistrict());
    }
    
    public void criteriiBaiesaLaplasa(List<PreparedDemography> demographyDataList, double[] probArray) {
        
        double[] result = new double[demographyDataList.size()];
        
        for (int i = 0; i < demographyDataList.size(); i++) {
            result[i] = (demographyDataList.get(i).getDoctorsSecurity() * probArray[0] + demographyDataList.get(i).getBirthrate() * probArray[1] + 
                    demographyDataList.get(i).getMortality() * probArray[2] + demographyDataList.get(i).getMortalityInTheWorkingAge() * probArray[3]);
        }
        
        double max = result[0];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            if (max < result[i]) {
                max = result[i];
                index = i;
            }            
        }
        
        System.out.println("criteriiBaiesaLaplasa max = " + max + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Критерий Байеса-Лапласа", 
                "max", max, demographyDataList.get(index).getDistrict());
    }
    
    public void criteriiRasshirenniiMM(List<PreparedDemography> demographyDataList) {
        
    }
    
    public void criteriiProizvedenii(List<PreparedDemography> demographyDataList) {
        //произведение чисел каждой строки
        double[] productOfTheNumbersOfRows = new double[demographyDataList.size()];
        
        for (int i = 0; i < demographyDataList.size(); i++) {
            productOfTheNumbersOfRows[i] = demographyDataList.get(i).getDoctorsSecurity() * 
                    demographyDataList.get(i).getBirthrate() * demographyDataList.get(i).getMortality() * 
                    demographyDataList.get(i).getMortalityInTheWorkingAge();
        }
        
        //максимальное из productOfTheNumbersOfRows
        double max = productOfTheNumbersOfRows[0];
        for (int i = 0; i < productOfTheNumbersOfRows.length; i++) {
            if (max < productOfTheNumbersOfRows[i]) {
                max = productOfTheNumbersOfRows[i];
            }            
        }
        
        //от максимума отнимаю каждое из чисел productOfTheNumbersOfRows
        for (int i = 0; i < productOfTheNumbersOfRows.length; i++) {
            productOfTheNumbersOfRows[i] = max - productOfTheNumbersOfRows[i];            
        }
        
        max = productOfTheNumbersOfRows[0];
        int index = 0;
        for (int i = 0; i < productOfTheNumbersOfRows.length; i++) {
            if (max < productOfTheNumbersOfRows[i]) {
                max = productOfTheNumbersOfRows[i];
                index = i;
            }
        }
        
        System.out.println("criteriiProizvedenii max = " + max + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Критерий произведений", 
                "max", max, demographyDataList.get(index).getDistrict());
    }
    
    public void criteriiGermeiera(List<PreparedDemography> demographyDataList, double[] probGermeierArray) {
        double tempMax = demographyDataList.get(0).getDoctorsSecurity();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (tempMax < demographyDataList.get(i).getBirthrate()) {
                tempMax = demographyDataList.get(i).getBirthrate();
            }
            if (tempMax < demographyDataList.get(i).getMortality()) {
                tempMax = demographyDataList.get(i).getMortality();
            }
            if (tempMax < demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                tempMax = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
        }
        
        tempMax = tempMax + 1;
        
        List<PreparedDemography> tempDemographyDataList = new ArrayList<PreparedDemography>();
         
        for (int i = 0; i < demographyDataList.size(); i++) {
            
            PreparedDemography prepDem = new PreparedDemography();
            prepDem.setId(demographyDataList.get(i).getId());
            prepDem.setDistrict(demographyDataList.get(i).getDistrict());
            prepDem.setDoctorsSecurity(demographyDataList.get(i).getDoctorsSecurity() - tempMax);
            prepDem.setBirthrate(demographyDataList.get(i).getBirthrate() - tempMax);
            prepDem.setMortality(demographyDataList.get(i).getMortality() - tempMax);
            prepDem.setMortalityInTheWorkingAge(demographyDataList.get(i).getMortalityInTheWorkingAge() - tempMax);
            
            tempDemographyDataList.add(prepDem);
        }
        
         for (int i = 0; i < tempDemographyDataList.size(); i++) {            
            tempDemographyDataList.get(i).setDoctorsSecurity(demographyDataList.get(i).getDoctorsSecurity() * probGermeierArray[0]);
            tempDemographyDataList.get(i).setBirthrate(demographyDataList.get(i).getBirthrate() * probGermeierArray[1]);
            tempDemographyDataList.get(i).setMortality(demographyDataList.get(i).getMortality() * probGermeierArray[2]);
            tempDemographyDataList.get(i).setMortalityInTheWorkingAge(demographyDataList.get(i).getMortalityInTheWorkingAge() * probGermeierArray[3]);            
        }
         
         double[] min = new double[tempDemographyDataList.size()];
         double tempMin = tempDemographyDataList.get(0).getDoctorsSecurity();
        for (int i = 0; i < tempDemographyDataList.size(); i++) {
            if (tempMin > tempDemographyDataList.get(i).getBirthrate()) {
                tempMin = tempDemographyDataList.get(i).getBirthrate();
            }
            if (tempMin > tempDemographyDataList.get(i).getMortality()) {
                tempMin = tempDemographyDataList.get(i).getMortality();
            }
            if (tempMin > tempDemographyDataList.get(i).getMortalityInTheWorkingAge()) {
                tempMin = tempDemographyDataList.get(i).getMortalityInTheWorkingAge();
            }
            
            min[i] = tempMin;
        }
        
        double max = min[0];
        int index = 0;
        for (int i = 0; i < min.length; i++) {
            if (tempMax < min[i]) {
                tempMax = min[i];
                index = i;
            }
        }
        
        System.out.println("criteriiGermeiera max = " + max + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Критерий Гермейера", 
                "max", max, demographyDataList.get(index).getDistrict());
         
    }
    
    public void criteriiGurvitsa(List<PreparedDemography> demographyDataList, double degreeOfOptimism) {
        double[] maxOfRows = new double[demographyDataList.size()];
        double[] minOfRows = new double[demographyDataList.size()];
        double[] result = new double[demographyDataList.size()];
        
        double max;
        for (int i = 0; i < demographyDataList.size(); i++) {
            max = demographyDataList.get(i).getDoctorsSecurity();
            if (max < demographyDataList.get(i).getBirthrate()) {
                max = demographyDataList.get(i).getBirthrate();
            }
            if (max < demographyDataList.get(i).getMortality()) {
                max = demographyDataList.get(i).getMortality();
            }
            if (max < demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                max = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
            maxOfRows[i] = max;
        }
        
        double min;
        for (int i = 0; i < demographyDataList.size(); i++) {
            min = demographyDataList.get(i).getDoctorsSecurity();
            if (min > demographyDataList.get(i).getBirthrate()) {
                min = demographyDataList.get(i).getBirthrate();
            }
            if (min > demographyDataList.get(i).getMortality()) {
                min = demographyDataList.get(i).getMortality();
            }
            if (min > demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                min = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
            minOfRows[i] = min;
        }
        
        for (int i = 0; i < demographyDataList.size(); i++) {
            result[i] = degreeOfOptimism * minOfRows[i] + (1 - degreeOfOptimism) * maxOfRows[i];            
        }
        
        max = result[0];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            if (max < result[i]) {
                max = result[i];
            }            
        }
        
        System.out.println("criteriiGurvitsa max = " + max + " index = " + index);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(index).getId(), "Критерий Гурвица", 
                "max", max, demographyDataList.get(index).getDistrict());
    }
    
    public void criteriiSostavnoiBaiesaLaplasaMM(List<PreparedDemography> demographyDataList, double acceptableRisk) {
        List<ValueWithIndexes> min = new ArrayList<ValueWithIndexes>();
        
        double min1;
        int jIndexMax;
        for (int i = 0; i < demographyDataList.size(); i++) {
            min1 = demographyDataList.get(i).getDoctorsSecurity();
            jIndexMax = i;
            if (min1 > demographyDataList.get(i).getDoctorsSecurity()) {
                min1 = demographyDataList.get(i).getDoctorsSecurity();
                jIndexMax = 0;
            }
            if (min1 > demographyDataList.get(i).getBirthrate()) {
                min1 = demographyDataList.get(i).getBirthrate();
                jIndexMax = 1;
            }
            if (min1 > demographyDataList.get(i).getMortality()) {
                min1 = demographyDataList.get(i).getMortality();
                jIndexMax = 2;
            }
            if (min1 > demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                min1 = demographyDataList.get(i).getMortalityInTheWorkingAge();
                jIndexMax = 3;
            }
            
            ValueWithIndexes vwi = new ValueWithIndexes();
            vwi.setValue(min1);
            vwi.setiIndex(i);
            vwi.setjIndex(jIndexMax);
            
            min.add(vwi);
        }
        
        double max = min.get(0).getValue();
        int iIndexMax = min.get(0).getiIndex();
        jIndexMax = min.get(0).getjIndex();
        for (int i = 0; i < min.size(); i++) {
            if (max < min.get(i).getValue()) {
                max = min.get(i).getValue();
                iIndexMax = min.get(i).getiIndex();
                jIndexMax = min.get(i).getjIndex();
            }            
        }
               
        List<PreparedDemography> tempDemographyDataList = new ArrayList<PreparedDemography>();
        for (int i = 0; i < demographyDataList.size(); i++) {
            PreparedDemography prepDem = new PreparedDemography();
            prepDem.setId(demographyDataList.get(i).getId());
            prepDem.setDistrict(demographyDataList.get(i).getDistrict());
            prepDem.setDoctorsSecurity(max - demographyDataList.get(i).getDoctorsSecurity());
            prepDem.setBirthrate(max - demographyDataList.get(i).getBirthrate());
            prepDem.setMortality(max - demographyDataList.get(i).getMortality());
            prepDem.setMortalityInTheWorkingAge(max - demographyDataList.get(i).getMortalityInTheWorkingAge());   
            
            tempDemographyDataList.add(prepDem);
        }
        
        List<ValueWithIndexes> j1 = new ArrayList<ValueWithIndexes>();
        ValueWithIndexes vwi = new ValueWithIndexes();
        for (int i = 0; i < tempDemographyDataList.size(); i++) {
            if (tempDemographyDataList.get(i).getDoctorsSecurity() > acceptableRisk) {
                vwi.setValue(tempDemographyDataList.get(i).getDoctorsSecurity());
                vwi.setiIndex(i);
                vwi.setjIndex(0);
                j1.add(vwi);
            }
            if (tempDemographyDataList.get(i).getBirthrate()> acceptableRisk) {
                vwi.setValue(tempDemographyDataList.get(i).getBirthrate());
                vwi.setiIndex(i);
                vwi.setjIndex(1);
                j1.add(vwi);
            }
            if (tempDemographyDataList.get(i).getMortality()> acceptableRisk) {
                vwi.setValue(tempDemographyDataList.get(i).getMortality());
                vwi.setiIndex(i);
                vwi.setjIndex(2);
                j1.add(vwi);
            }
            if (tempDemographyDataList.get(i).getMortalityInTheWorkingAge()> acceptableRisk) {
                vwi.setValue(tempDemographyDataList.get(i).getMortalityInTheWorkingAge());
                vwi.setiIndex(i);
                vwi.setjIndex(3);
                j1.add(vwi);
            }
        }
        
        double[] maxColumn = new double[4];
        
        double max2 = demographyDataList.get(0).getDoctorsSecurity();        
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (max2 < demographyDataList.get(i).getDoctorsSecurity()) {
                max2 = demographyDataList.get(i).getDoctorsSecurity();               
            }
        }
        maxColumn[0] = max2;
        
        max2 = demographyDataList.get(0).getBirthrate();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (max2 < demographyDataList.get(i).getBirthrate()) {
                max2 = demographyDataList.get(i).getBirthrate();
            }
        }
        maxColumn[1] = max2;
        
        max2 = demographyDataList.get(0).getMortality();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (max2 < demographyDataList.get(i).getMortality()) {
                max2 = demographyDataList.get(i).getMortality();
            }
        }
        maxColumn[2] = max2;
        
        max2 = demographyDataList.get(0).getMortalityInTheWorkingAge();
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (max2 < demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                max2 = demographyDataList.get(i).getMortalityInTheWorkingAge();
            }
        }
        maxColumn[3] = max2;
//??? не корректно работает
        List<ValueWithIndexes> minColumn = new ArrayList<ValueWithIndexes>();
        
        ValueWithIndexes vwi1 = new ValueWithIndexes();        
        double min2 = demographyDataList.get(0).getDoctorsSecurity();
        int iIndx = 0;
        int jIndx = 0;
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (min2 > demographyDataList.get(i).getDoctorsSecurity()) {
                min2 = demographyDataList.get(i).getDoctorsSecurity();
                iIndx = i;
                jIndx = 0;
            }
        }
        vwi1.setValue(min2);
        vwi1.setiIndex(iIndx);
        vwi1.setjIndex(jIndx);
        minColumn.add(vwi1);
        
        ValueWithIndexes vwi2 = new ValueWithIndexes();
        min2 = demographyDataList.get(0).getBirthrate();
        iIndx = 0;
        jIndx = 1;
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (min2 > demographyDataList.get(i).getBirthrate()) {
                min2 = demographyDataList.get(i).getBirthrate();
                iIndx = i;
                jIndx = 1;
            }
        }
        vwi2.setValue(min2);
        vwi2.setiIndex(iIndx);
        vwi2.setjIndex(jIndx);
        minColumn.add(vwi2);
        
        ValueWithIndexes vwi3 = new ValueWithIndexes();
        min2 = demographyDataList.get(0).getMortality();
        iIndx = 0;
        jIndx = 2;
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (min2 > demographyDataList.get(i).getMortality()) {
                min2 = demographyDataList.get(i).getMortality();
                iIndx = i;
                jIndx = 2;
            }
        }
        vwi3.setValue(min2);
        vwi3.setiIndex(iIndx);
        vwi3.setjIndex(jIndx);
        minColumn.add(vwi3);
        
        ValueWithIndexes vwi4 = new ValueWithIndexes();
        min2 = demographyDataList.get(0).getMortalityInTheWorkingAge();
        iIndx = 0;
        jIndx = 3;
        for (int i = 0; i < demographyDataList.size(); i++) {
            if (min2 > demographyDataList.get(i).getMortalityInTheWorkingAge()) {
                min2 = demographyDataList.get(i).getMortalityInTheWorkingAge();
                iIndx = i;
                jIndx = 3;
            }
        }
        vwi4.setValue(min2);
        vwi4.setiIndex(iIndx);
        vwi4.setjIndex(jIndx);
        minColumn.add(vwi4);
           
        
        List<ValueWithIndexes> j2 = new ArrayList<ValueWithIndexes>();
        for (int i = 0; i < maxColumn.length; i++) {
            if ((maxColumn[i] - max) >= (max - minColumn.get(i).getValue())) {                
                ValueWithIndexes vwi5 = new ValueWithIndexes();                
                vwi5.setValue(minColumn.get(i).getValue());
                vwi5.setiIndex(minColumn.get(i).getiIndex());
                vwi5.setjIndex(minColumn.get(i).getjIndex());
                j2.add(vwi5);
            }
        }
        
        List<ValueWithIndexes> resultList = new ArrayList<ValueWithIndexes>();
        for (int i = 0; i < j1.size(); i++) {
            for (int j = 0; j < j2.size(); j++) {
                if (j1.get(i).getValue() == j2.get(i).getValue()) {
                    resultList.add(j1.get(i));
                }
            }            
        }
        
        double result;
        int iIndex = 0, jIndex = 0;
        if (resultList.isEmpty()) {
            result = 0;
        } else {
        result = resultList.get(0).getValue();
            for (int i = 0; i < resultList.size(); i++) {
                if (result < resultList.get(i).getValue()) {
                    result = resultList.get(i).getValue();
                    iIndex = resultList.get(i).getiIndex();
                    jIndex = resultList.get(i).getjIndex();
                }            
            }
        }
        
        System.out.println("criteriiSostavnoiBaiesaLaplasaMM max = " + result + " iIndex = " + iIndex + " jIndex = " + jIndex);
        
        ResultToList.getInstance().setResultToList(demographyDataList.get(iIndex).getId(), "Составной критерий Байеса-Лапласа минимаксный", 
                "max", result, demographyDataList.get(iIndex).getDistrict());
        
    }
    
    
    
    private СriteriaСalculation() {        
    }    
    private static СriteriaСalculation self = new СriteriaСalculation();
    public static СriteriaСalculation getInstance() {
        return self;
    }
}
