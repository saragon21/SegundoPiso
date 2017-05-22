/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saragon
 */
@XmlRootElement
public class ReporteAsistencias {
    
    @XmlElement
    private List<Asistencia> attendence;
    
    @XmlElement
    private float pago;
    
    @XmlElement
    private Clase clase;

    @XmlElement
    private Maestro maestro;
    
    public List<Asistencia> getAttendence() {
        return attendence;
    }

    public void setAttendence(List<Asistencia> attendence) {
        this.attendence = attendence;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }
}
