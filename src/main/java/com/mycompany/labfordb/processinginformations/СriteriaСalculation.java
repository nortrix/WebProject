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
//                resultList.add(rd);
                System.out.println("1");
            }
            if (selectedList1.get(i).getValue().equals("2")) {
                criteriiSevidj(demographyDataList);
//                resultList.add(rd);
                System.out.println("2");
            }
            if (selectedList1.get(i).getValue().equals("3")) {
                criteriiBaiesaLaplasa(demographyDataList);
                System.out.println("3");
            }
            if (selectedList1.get(i).getValue().equals("4")) {
                criteriiRasshirenniiMM(demographyDataList);
                System.out.println("4");
            }
            if (selectedList1.get(i).getValue().equals("5")) {
                criteriiProizvedenii(demographyDataList);
//                resultList.add(rd);
                System.out.println("5");
            }
            if (selectedList1.get(i).getValue().equals("6")) {
                criteriiGermeiera(demographyDataList);
                System.out.println("6");
            }
            if (selectedList1.get(i).getValue().equals("7")) {
                criteriiGurvitsa(demographyDataList);
                System.out.println("7");
            }
            if (selectedList1.get(i).getValue().equals("8")) {
                criteriiSostavnoiBaiesaLaplasaMM(demographyDataList);
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
        
//        ResultDemography resDem = new ResultDemography();
//        resDem.setId(demographyDataList.get(index).getId());
//        resDem.setCriteria("Минимаксный критерий");
//        resDem.setMinOrMax("max");
//        resDem.setResult(max);
//        resDem.setNameObject(demographyDataList.get(index).getDistrict());
        
//        return resDem;
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
        
//        ResultDemography resDem = new ResultDemography();
//        resDem.setId(demographyDataList.get(index).getId());
//        resDem.setCriteria("Критерий Сэвиджа");
//        resDem.setMinOrMax("min");
//        resDem.setResult(min);
//        resDem.setNameObject(demographyDataList.get(index).getDistrict());
//        
//        return resDem;
    }
    
    public void criteriiBaiesaLaplasa(List<PreparedDemography> demographyDataList) {
        
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
        
//        ResultDemography resDem = new ResultDemography();
//        resDem.setId(demographyDataList.get(index).getId());
//        resDem.setCriteria("Критерий произведений");
//        resDem.setMinOrMax("max");
//        resDem.setResult(max);
//        resDem.setNameObject(demographyDataList.get(index).getDistrict());
//        
//        return resDem;
    }
    
    public void criteriiGermeiera(List<PreparedDemography> demographyDataList) {
        
    }
    
    public void criteriiGurvitsa(List<PreparedDemography> demographyDataList) {
        
    }
    
    public void criteriiSostavnoiBaiesaLaplasaMM(List<PreparedDemography> demographyDataList) {
        
    }
    
    
    
    private СriteriaСalculation() {        
    }    
    private static СriteriaСalculation self = new СriteriaСalculation();
    public static СriteriaСalculation getInstance() {
        return self;
    }
}
