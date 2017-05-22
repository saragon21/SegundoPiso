/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.beans;

import com.segundo.piso.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sarai
 */
@XmlRootElement
public class ReporteAlumno {
    @XmlElement
    private String codigo;
    @XmlElement
    private String nombre;
    @XmlElement
    private Date fecha;
    @XmlElement
    private int clasesRestantes;
    @XmlElement
    private int idAlumno;
    @XmlElement
    private Date fechaVencimiento;
    
    private Calendar calendar = Calendar.getInstance();
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getClasesRestantes() {
        return clasesRestantes;
    }

    public void setClasesRestantes(int clasesRestantes) {
        this.clasesRestantes = clasesRestantes;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Date getFechaVencimiento() {
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_MONTH, 31);
        this.fechaVencimiento = calendar.getTime();
        return this.fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    
    @Override
    public String toString() {
        return "Codigo " + codigo + " nombre " + nombre + " fecha " + getFecha() + 
                " clases restantes " + clasesRestantes;
    }
}
