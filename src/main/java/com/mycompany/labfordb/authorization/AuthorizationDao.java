/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.authorization;

import com.mycompany.labfordb.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Валик
 */
public class AuthorizationDao {
    
    public void userInitialization(String login, String pass) {
        String QUERY_USER_INITIALIZATION = "SELECT ID, LAST_NAME, FIRST_NAME, PASSWORD, EMAIL\n" +
"FROM CUSTOMERS where LOGIN = ?;";
        
        try {   
            Connection conn = DataSource.instance().connection();
            PreparedStatement st = null;
            try {                
                st = conn.prepareStatement(QUERY_USER_INITIALIZATION);
                st.setString(1, login);
                ResultSet rs = st.executeQuery();
                
                while (rs.next()) {                    
                    if (pass.equals(rs.getString("PASSWORD"))) {
                        AuthorizationUser.setId(rs.getInt("ID"));
                        AuthorizationUser.setLogin(login);
                        AuthorizationUser.setLastName(rs.getString("LAST_NAME"));
                        AuthorizationUser.setFirstName(rs.getString("FIRST_NAME"));
                        AuthorizationUser.setPassword(rs.getString("PASSWORD"));
                        AuthorizationUser.setMail(rs.getString("EMAIL"));
                        AuthorizationUser.isIsLogged();
                    }
//                        AuthorizationUser.setId(0);
//                        AuthorizationUser.setLogin(null);
//                        AuthorizationUser.setLastName(null);
//                        AuthorizationUser.setFirstName(null);
//                        AuthorizationUser.setPassword(null);
//                        AuthorizationUser.setMail(null);
//                        AuthorizationUser.isNotLogged();
//                    }
                    //System.out.println("" + AuthorizationUser.isLogged);

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
            System.out.println("ERROR WITH INSERT NEW DemographyData");
            ex.printStackTrace();
        }
    }
    
    
    private AuthorizationDao() {        
    }
    private static AuthorizationDao self = new AuthorizationDao();
    public static AuthorizationDao getInstance() {
        return self;
    }    
}
