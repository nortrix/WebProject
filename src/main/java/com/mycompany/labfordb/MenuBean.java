/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb;

import com.mycompany.labfordb.authorization.AuthorizationUser;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean
@SessionScoped
public class MenuBean implements Serializable{
    
    //??????????? поменять логику в AuthorizationUser
    //да вроде работает...
    public boolean isLogged() {
        System.out.println(AuthorizationUser.isLogged + "");
        return AuthorizationUser.isLogged;
    }
}
