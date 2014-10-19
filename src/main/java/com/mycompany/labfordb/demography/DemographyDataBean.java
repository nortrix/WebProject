/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.demography;

import com.mycompany.labfordb.util.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean(name="demographyData", eager = true)
@SessionScoped
public class DemographyDataBean implements Serializable{
    
    public List<Demography> getDemographyData() {        
        List<Demography> demographyDataList = new ArrayList<Demography>();
        
        String QUERY_GET_DEMOGRAPHY_DATA = "SELECT DEMOGRAPHY.ID, DEMOGRAPHY.DOCTORS_SECURITY,"
                + " DEMOGRAPHY.BIRTHRATE, DEMOGRAPHY.MORTALITY, DEMOGRAPHY.MORTALITY_IN_THE_WORKING_AGE, DISTRICTS.NAME\n"
                + "FROM DEMOGRAPHY, DISTRICTS WHERE DEMOGRAPHY.ID_DISTRICT = DISTRICTS.ID;";
        
        try {   
            Connection conn = DataSource.instance().connection();
            Statement st = null;
            try {                
                st = conn.createStatement();
                ResultSet rs = st.executeQuery(QUERY_GET_DEMOGRAPHY_DATA);
                while (rs.next()) {
                    Demography demData = new Demography();
                    
                    int id = rs.getInt("DEMOGRAPHY.ID");
                    String districtName = rs.getString("DISTRICTS.NAME");
                    int doctorsSecurity = rs.getInt("DEMOGRAPHY.DOCTORS_SECURITY");
                    int birthrate = rs.getInt("DEMOGRAPHY.BIRTHRATE");
                    int mortality = rs.getInt("DEMOGRAPHY.MORTALITY");
                    int mortalityInTheWorkingAge = rs.getInt("DEMOGRAPHY.MORTALITY_IN_THE_WORKING_AGE");
                    
                    demData.setId(id);
                    demData.setDistrict(districtName);
                    demData.setDoctorsSecurity(doctorsSecurity);
                    demData.setBirthrate(birthrate);
                    demData.setMortality(mortality);
                    demData.setMortalityInTheWorkingAge(mortalityInTheWorkingAge);
                    
                    demographyDataList.add(demData);
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            } finally {
                try { 
                    if (st != null) st.close(); 
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Problems with closing statement");
                };
                try { 
                    if (conn != null) conn.close(); 
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Problems with closing connection");
                };
            }
        } catch (Exception ex) {
            System.out.println("ERROR WITH SELECT DemographyData");
            ex.printStackTrace();
        }
        
        return demographyDataList;
    }
}
