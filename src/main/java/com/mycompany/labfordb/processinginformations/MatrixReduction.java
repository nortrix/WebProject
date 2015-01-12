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

        int columnNum = 4;
        int rowNum = demographyDataList.size();        
        double[][] data = new double[rowNum][columnNum];
        
        for (int i = 0; i < rowNum; i++) {
            data[i][0] = demographyDataList.get(i).getDoctorsSecurity();
            data[i][1] = demographyDataList.get(i).getBirthrate();
            data[i][2] = demographyDataList.get(i).getMortality();
            data[i][3] = demographyDataList.get(i).getMortalityInTheWorkingAge();
        }
        
        double[][] E1 = new double[data.length][data[0].length];//копия исходных данных
        for (int i = 0; i < data.length; i++) {
          System.arraycopy(data[i], 0, E1[i], 0, data[0].length);
        }
        double[] xsr = new double[data.length];

        for (int j = 0; j < data[0].length; j++) {
          for (int i = 0; i < data.length; i++) {
            xsr[j] = xsr[j] + E1[i][j];
          }
          xsr[j] = xsr[j] / data.length;
        }

        for (int j = 0; j < data[0].length; j++) {
          double c = 0;
          for (int i = 0; i < data.length; i++) {
            c += Math.pow(E1[i][j] - xsr[j], 2);
          }
          c = Math.sqrt(c) / 5;
          for (int i = 0; i < data.length; i++) {
            data[i][j] = (E1[i][j] - xsr[j]) / c;
          }
        }
                
        preparedDataList = new ArrayList<PreparedDemography>();
        
        for (int i = 0; i < data.length; i++) {
            PreparedDemography pd = new PreparedDemography();
            
            pd.setId(demographyDataList.get(i).getId());
            pd.setDistrict(demographyDataList.get(i).getDistrict());
            pd.setDoctorsSecurity(data[i][0]);
            pd.setBirthrate(data[i][1]);
            pd.setMortality(data[i][2]);
            pd.setMortalityInTheWorkingAge(data[i][3]);
            
        }
        return preparedDataList;
    }

    private static MatrixReduction self = new MatrixReduction();

    public static MatrixReduction getInstance() {
        return self;
    }
}
