/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb.authorization;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean
@SessionScoped
public class AuthorizationBean implements Serializable{
    
    private String login;
    private String password;
    private String feedBack;

    public void setLogin(String login) {
        this.login = login;
        System.out.println("" + login);
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("" + AuthorizationUser.getId() + " " +AuthorizationUser.getFirstName());
        AuthorizationDao.getInstance().userInitialization(login, password);
        System.out.println("" + AuthorizationUser.getId() + " " +AuthorizationUser.getFirstName());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogged() {
        System.out.println("" + AuthorizationUser.isLogged);
        return AuthorizationUser.isLogged;
        
    }

    public String getFeedBack() {
        if (AuthorizationUser.isLogged) {
            feedBack = "Authorization success";
            return feedBack;
        } else {
            feedBack = "";
            return feedBack;
        }        
    }       
    
}
