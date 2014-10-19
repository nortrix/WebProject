/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.demography;

import com.mycompany.labfordb.util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Валик
 */

public class DemographyDao {
    
    @SuppressWarnings("empty-statement")
    public void setDemographyData(String district, int doctorsSecurity, 
            int birthrate, int mortality, int mortalityInTheWorkingAge) {
        
        String QUERY_FOR_UPDATE_DEMOGRAPHY_TABLE = 
                "BEGIN; "
                + "INSERT INTO DISTRICTS (NAME) VALUES('" + district + "'); \n" +
                "INSERT INTO DEMOGRAPHY (DOCTORS_SECURITY, BIRTHRATE, MORTALITY, MORTALITY_IN_THE_WORKING_AGE, ID_DISTRICT)\n" +
                "VALUES(" + doctorsSecurity + ", " + birthrate + ", " + mortality + ", " + mortalityInTheWorkingAge + ", LAST_INSERT_ID());\n" +
                "COMMIT;";
        
        //!!!!!!!!!!! Применение транзакции !!!!!!!!!!!!!!
        
        try {   
            Connection conn = DataSource.instance().connection();
//            conn.setAutoCommit(false);
//            boolean commited = false;
            Statement st = null;
            try {                
                st = conn.createStatement();
                st.executeUpdate(QUERY_FOR_UPDATE_DEMOGRAPHY_TABLE);
//                conn.commit();
//                commited = true;
            }catch (SQLException exe){
                exe.printStackTrace();
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
//                if (!commited) {
//                    conn.rollback();
//                }
            }
        } catch (Exception ex) {
            System.out.println("ERROR WITH INSERT NEW DemographyData");
            ex.printStackTrace();
        }
    }
    
//    public List<Demography> getDemographyData() {
//        
//        List<Demography> demographyDataList = new ArrayList<Demography>();
//        
//        String QUERY_GET_DEMOGRAPHY_DATA = "SELECT DEMOGRAPHY.ID, DEMOGRAPHY.DOCTORS_SECURITY,"
//                + " DEMOGRAPHY.BIRTHRATE, DEMOGRAPHY.MORTALITY, DEMOGRAPHY.MORTALITY_IN_THE_WORKING_AGE, DISTRICTS.NAME\n"
//                + "FROM DEMOGRAPHY, DISTRICTS WHERE DEMOGRAPHY.ID_DISTRICT = DISTRICTS.ID;";
//        
//        try {   
//            Connection conn = DataSource.instance().connection();
//            Statement st = null;
//            try {                
//                st = conn.createStatement();
//                ResultSet rs = st.executeQuery(QUERY_GET_DEMOGRAPHY_DATA);
//                while (rs.next()) {
//                    Demography demData = new Demography();
//                    
//                    int id = rs.getInt("DEMOGRAPHY.ID");
//                    String districtName = rs.getString("DISTRICTS.NAME");
//                    int doctorsSecurity = rs.getInt("DEMOGRAPHY.DOCTORS_SECURITY");
//                    int birthrate = rs.getInt("DEMOGRAPHY.BIRTHRATE");
//                    int mortality = rs.getInt("DEMOGRAPHY.MORTALITY");
//                    int mortalityInTheWorkingAge = rs.getInt("DEMOGRAPHY.MORTALITY_IN_THE_WORKING_AGE");
//                    
//                    demData.setId(id);
//                    demData.setDistrict(districtName);
//                    demData.setDoctorsSecurity(doctorsSecurity);
//                    demData.setBirthrate(birthrate);
//                    demData.setMortality(mortality);
//                    demData.setMortalityInTheWorkingAge(mortalityInTheWorkingAge);
//                    
//                    demographyDataList.add(demData);
//                }
//            }catch (SQLException exe){
//                exe.printStackTrace();
//            } finally {
//                try { 
//                    if (st != null) st.close(); 
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("Problems with closing statement");
//                };
//                try { 
//                    if (conn != null) conn.close(); 
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("Problems with closing connection");
//                };
//            }
//        } catch (Exception ex) {
//            System.out.println("ERROR WITH SELECT DemographyData");
//            ex.printStackTrace();
//        }
//        
//        return demographyDataList;
//    }
       
    private DemographyDao() {        
    }
    
    private static DemographyDao self = new DemographyDao();
    public static DemographyDao getInstance() {
        return self;
    }
}
