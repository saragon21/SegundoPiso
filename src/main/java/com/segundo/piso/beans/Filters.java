/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.beans;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saragon
 */
@XmlRootElement
public class Filters {
    
    @XmlElement
    private Date fechaInicio;
    
    @XmlElement
    private Date fechaFin;
    
    @XmlElement
    private int evento;

    @XmlElement
    private int clases;
    
    @XmlElement
    private int maestro;
    
    @XmlElement
    private boolean status;
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getClases() {
        return clases;
    }

    public void setClases(int clases) {
        this.clases = clases;
    }

    public int getMaestro() {
        return maestro;
    }

    public void setMaestro(int maestro) {
        this.maestro = maestro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
