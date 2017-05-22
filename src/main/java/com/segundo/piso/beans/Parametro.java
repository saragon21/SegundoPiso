/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p"),
    @NamedQuery(name = "Parametro.findByIdParametro", query = "SELECT p FROM Parametro p WHERE p.idParametro = :idParametro"),
    @NamedQuery(name = "Parametro.findByParametro", query = "SELECT p FROM Parametro p WHERE p.parametro = :parametro"),
    @NamedQuery(name = "Parametro.findByValor", query = "SELECT p FROM Parametro p WHERE p.valor = :valor"),
    @NamedQuery(name = "Parametro.findByDescripcion", query = "SELECT p FROM Parametro p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Parametro.findByPatron", query = "SELECT p FROM Parametro p WHERE p.patron = :patron"),
    @NamedQuery(name = "Parametro.findByStatus", query = "SELECT p FROM Parametro p WHERE p.status = :status"),
    @NamedQuery(name = "Parametro.findByLastModTime", query = "SELECT p FROM Parametro p WHERE p.lastModTime = :lastModTime"),
    @NamedQuery(name = "Parametro.findByLastModUser", query = "SELECT p FROM Parametro p WHERE p.lastModUser = :lastModUser")})
public class Parametro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO")
    private Integer idParametro;
    @Basic(optional = false)
    @Column(name = "PARAMETRO")
    private String parametro;
    @Column(name = "VALOR")
    private String valor;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "PATRON")
    private String patron;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private boolean status;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModTime;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_USER")
    private String lastModUser;

    public Parametro() {
    }

    public Parametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Parametro(Integer idParametro, String parametro, boolean status, Date lastModTime, String lastModUser) {
        this.idParametro = idParametro;
        this.parametro = parametro;
        this.status = status;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(Date lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getLastModUser() {
        return lastModUser;
    }

    public void setLastModUser(String lastModUser) {
        this.lastModUser = lastModUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametro != null ? idParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.idParametro == null && other.idParametro != null) || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.escuela.beans.Parametro[ idParametro=" + idParametro + " ]";
    }
    
}
