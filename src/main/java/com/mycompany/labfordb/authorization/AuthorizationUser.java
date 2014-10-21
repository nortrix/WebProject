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
public class AuthorizationUser {
    private static int id;
    private static String login;
    private static String password;
    private static String lastName;
    private static String firstName;
    private static String mail;
    
    //????????????????? поменять логику boolean isLogged
    public static boolean isLogged = false;
    
    public static void isIsLogged() {
        isLogged = true;
    }

    public static void setId(int id) {
        AuthorizationUser.id = id;
    }

    public static void setLogin(String login) {
        AuthorizationUser.login = login;
    }

    public static void setPassword(String password) {
        AuthorizationUser.password = password;
    }

    public static void setLastName(String lastName) {
        AuthorizationUser.lastName = lastName;
    }

    public static void setFirstName(String firstName) {
        AuthorizationUser.firstName = firstName;
    }

    public static void setMail(String mail) {
        AuthorizationUser.mail = mail;
    }

    public static int getId() {
        return id;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getMail() {
        return mail;
    }

   
}
