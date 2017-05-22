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
@Table(name = "logged")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logged.findAll", query = "SELECT l FROM Logged l"),
    @NamedQuery(name = "Logged.findByIdlogged", query = "SELECT l FROM Logged l WHERE l.idlogged = :idlogged"),
    @NamedQuery(name = "Logged.findByIp", query = "SELECT l FROM Logged l WHERE l.ip = :ip"),
    @NamedQuery(name = "Logged.findByLogged", query = "SELECT l FROM Logged l WHERE l.logged = :logged"),
    @NamedQuery(name = "Logged.findByStartlogged", query = "SELECT l FROM Logged l WHERE l.startlogged = :startlogged"),
    @NamedQuery(name = "Logged.findByEndlogged", query = "SELECT l FROM Logged l WHERE l.endlogged = :endlogged")})
public class Logged implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDLOGGED")
    private Integer idlogged;
    @Basic(optional = false)
    @Column(name = "IP")
    private String ip;
    @Basic(optional = false)
    @Column(name = "LOGGED")
    private boolean logged;
    @Basic(optional = false)
    @Column(name = "STARTLOGGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startlogged;
    @Basic(optional = false)
    @Column(name = "ENDLOGGED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endlogged;

    public Logged() {
    }

    public Logged(Integer idlogged) {
        this.idlogged = idlogged;
    }

    public Logged(Integer idlogged, String ip, boolean logged, Date startlogged, Date endlogged) {
        this.idlogged = idlogged;
        this.ip = ip;
        this.logged = logged;
        this.startlogged = startlogged;
        this.endlogged = endlogged;
    }

    public Integer getIdlogged() {
        return idlogged;
    }

    public void setIdlogged(Integer idlogged) {
        this.idlogged = idlogged;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Date getStartlogged() {
        return startlogged;
    }

    public void setStartlogged(Date startlogged) {
        this.startlogged = startlogged;
    }

    public Date getEndlogged() {
        return endlogged;
    }

    public void setEndlogged(Date endlogged) {
        this.endlogged = endlogged;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlogged != null ? idlogged.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logged)) {
            return false;
        }
        Logged other = (Logged) object;
        if ((this.idlogged == null && other.idlogged != null) || (this.idlogged != null && !this.idlogged.equals(other.idlogged))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.escuela.beans.Logged[ idlogged=" + idlogged + " ]";
    }
    
}
