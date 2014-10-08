///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.labfordb.util;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//
///**
// *
// * @author Валик
// */
//@ManagedBean(name = "userData", eager = true)
//@SessionScoped
//public class DataBase implements Serializable {
//
//   private static final long serialVersionUID = 1L;
//
//   public void getAuthors(){
//      ResultSet rs = null;
//      PreparedStatement pst = null;
//      Connection con = getConnection();
//      String stm = "SELECT ID, LAST_NAME, FIRST_NAME FROM CUSTOMERS;";
//      //List<Author> records = new ArrayList<Author>();
//      try {   
//         pst = con.prepareStatement(stm);
//         pst.execute();
//         rs = pst.getResultSet();
//         while(rs.next()){
//             int id = rs.getInt("ID");
//             String lastName = rs.getString("LAST_NAME");
//             String firstName = rs.getString("FIRST_NAME");
//             System.out.println(id + ") " + lastName + " " + firstName + ";");
//         }
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//
//   public Connection getConnection(){
//      Connection con = null;
//
//      String url = "jdbc:h2:./src/main/resources/database/database;AUTO_SERVER=TRUE";
//      String user = "sa";
//      String password = "sa";
//      try {
//         con = DriverManager.getConnection(url, user, password);
//         System.out.println("Connection completed.");
//      } catch (SQLException ex) {
//         System.out.println(ex.getMessage());
//      }
//      finally{
//      }
//      return con;
//   }
//}
//
