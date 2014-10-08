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
    private String authorization;

//    public String getAuthorization() {
//        if(login.equals(User.getInstance().getLogin()) && password.equals(User.getInstance().getPassword())) {
//            authorization = "demography.xhtml";
//            return authorization;
//        } else {
//            authorization = "welcome.xhtml";
//            return authorization;
//        }        
//    }

//    public void setAuthorization(String authorization) {
//        this.authorization = authorization;

    public String getAuthorization() {
        
        if(login.equals(User.getInstance().getLogin()) && password.equals(User.getInstance().getPassword())) {
            authorization = "demography.xhtml";
            return authorization;
        } else {
            authorization = "welcome.xhtml";
            return authorization;
        }  
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

//    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
