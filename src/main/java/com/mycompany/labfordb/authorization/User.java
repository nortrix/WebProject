/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.authorization;

/**
 *
 * @author Валик
 */
public class User {
    private static String login = "qwerty1";
    private static String password = "qwerty2";

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private User() {
    }    
   
    private static User self = new User();
    public static User getInstance() {
        return self;
    }
}
