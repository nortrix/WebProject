/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.demography;

import com.mycompany.labfordb.processinginformations.MatrixReduction;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean(name="testMatrixReduction", eager = true)
@SessionScoped
public class TestMatrixReductionBean implements Serializable {
    public List<PreparedDemography> getDemographyData() {
        //??????? почему он несколько раз прогоняет результат
        List<PreparedDemography> demographyDataList = MatrixReduction.getInstance().demographyMatrixReduction(DemographyDao.getInstance().getDemographyData());
        return demographyDataList;
    }
}
