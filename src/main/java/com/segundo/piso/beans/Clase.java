/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "clase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c"),
    @NamedQuery(name = "Clase.findByIdClase", query = "SELECT c FROM Clase c WHERE c.idClase = :idClase"),
    @NamedQuery(name = "Clase.findByNombreClase", query = "SELECT c FROM Clase c WHERE c.nombreClase = :nombreClase"),
    @NamedQuery(name = "Clase.findByStatus", query = "SELECT c FROM Clase c WHERE c.status = :status"),
    @NamedQuery(name = "Clase.findByLastModTime", query = "SELECT c FROM Clase c WHERE c.lastModTime = :lastModTime"),
    @NamedQuery(name = "Clase.findByLastModUser", query = "SELECT c FROM Clase c WHERE c.lastModUser = :lastModUser")})
public class Clase extends Base implements Serializable {
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "idClase")
    private List<Horario> horarioList;*/
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CLASE")
    @XmlElement
    private Integer idClase;
    @Column(name = "NOMBRE_CLASE")
    @XmlElement
    private String nombreClase;
    @Basic(optional = false)
    @Column(name = "STATUS")
    @XmlElement
    private boolean status;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModTime;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_USER")
    private String lastModUser;
    @JoinColumn(name = "ID_MAESTRO", referencedColumnName = "ID_MAESTRO")
    @ManyToOne(optional = false)
    private Maestro idMaestro;
    @OneToMany(mappedBy = "idClase")
    private List<Asistencia> asistenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClase")
    private List<PagoMaestro> pagoMaestroList;

    public Clase() {
        status = true;
    }

    public Clase(Integer idClase) {
        this.idClase = idClase;
    }

    public Clase(Integer idClase, boolean status, Date lastModTime, String lastModUser) {
        this.idClase = idClase;
        this.status = status;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getId() {
        return idClase;
    }

    public void setId(Integer idClase) {
        this.idClase = idClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
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

    public Maestro getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Maestro idMaestro) {
        this.idMaestro = idMaestro;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @XmlTransient
    @JsonIgnore
    public List<PagoMaestro> getPagoMaestroList() {
        return pagoMaestroList;
    }

    public void setPagoMaestroList(List<PagoMaestro> pagoMaestroList) {
        this.pagoMaestroList = pagoMaestroList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clase)) {
            return false;
        }
        Clase other = (Clase) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreClase;
    }

    /*@XmlTransient
    public List<Horario> getHorarioList() {
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }*/
    
}
