/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb;

import com.mycompany.labfordb.util.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;

/**
 *
 * @author Валик
 */

public class DemographyDao {
    
//    //datasource to access database
//    @Resource(name="jdbc/mydb")


    @SuppressWarnings("empty-statement")
    public void setDemographyData(String district, int doctorsSecurity, 
            int birthrate, int mortality, int mortalityInTheWorkingAge) {
        
        String QUERY_FOR_UPDATE_DEMOGRAPHY_TABLE =//"BEGIN; "
//                + "INSERT INTO PUBLIC.DISTRICTS (NAME) VALUES('" + district + "'); "
                /*+*/ "INSERT INTO DEMOGRAPHY (DOCTORS_SECURITY, BIRTHRATE, MORTALITY, MORTALITY_IN_THE_WORKING_AGE, ID_DISTRICT) "
                + "VALUES(" + doctorsSecurity + ", " + birthrate + ", " + mortality + ", " + mortalityInTheWorkingAge + ", LAST_INSERT_ID()) "
;//                + "COMMIT;";
        
        try {   
            Connection conn = DataSource.instance().connection();
            Statement st = null;
            try {                
                st = conn.createStatement();
                st.executeUpdate(QUERY_FOR_UPDATE_DEMOGRAPHY_TABLE);
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
        } catch (SQLException ex) {
            System.out.println("ERROR WITH INSERT NEW DemographyData");
            ex.printStackTrace();
        }
        
    }
       
    private DemographyDao() {        
    }
    
    private static DemographyDao self = new DemographyDao();
    public static DemographyDao getInstance() {
        return self;
    }
}
