/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import com.segundo.piso.util.CTEApp;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author SARAGON
 */
public class Base {
    @XmlElement
    @Transient
    private String statusStr;
    
    @XmlElement
    @Transient
    private String clasesStr;
    
    public void setStatusStr(boolean status) {
        this.statusStr = status ? CTEApp.YES :  CTEApp.NO;
    }
    
    public String getStatusStr() {
        return this.statusStr;
    }
    
    public void setClaseStr(boolean status) {
        this.clasesStr = status ? CTEApp.YES :  CTEApp.NO;
    }
    
    public String getClasesStr() {
        return this.clasesStr;
    }
}
