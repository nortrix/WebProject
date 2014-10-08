/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labfordb;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Валик
 */
@ManagedBean
@SessionScoped
public class TableData implements Serializable {
    private final static Name[] names = new Name[] {
        new Name("Valentin", "Stratula", 22),
        new Name("Alexei", "Stratula", 20),
        new Name("Larisa", "Stratula", 49),
        new Name("Igor", "Stratula", 52)        
    };

    public Name[] getNames() {
        return names;
    }    
}
