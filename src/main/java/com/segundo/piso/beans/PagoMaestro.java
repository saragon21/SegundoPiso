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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saragon
 */
@Entity
@Table(name = "PAGO_MAESTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagoMaestro.findAll", query = "SELECT p FROM PagoMaestro p"),
    @NamedQuery(name = "PagoMaestro.findByIdPagoMaestro", query = "SELECT p FROM PagoMaestro p WHERE p.idPagoMaestro = :idPagoMaestro"),
    @NamedQuery(name = "PagoMaestro.findByPago", query = "SELECT p FROM PagoMaestro p WHERE p.pago = :pago"),
    @NamedQuery(name = "PagoMaestro.findByStatus", query = "SELECT p FROM PagoMaestro p WHERE p.status = :status"),
    @NamedQuery(name = "PagoMaestro.findByFecha", query = "SELECT p FROM PagoMaestro p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "PagoMaestro.findByLastModTime", query = "SELECT p FROM PagoMaestro p WHERE p.lastModTime = :lastModTime"),
    @NamedQuery(name = "PagoMaestro.findByLastModUser", query = "SELECT p FROM PagoMaestro p WHERE p.lastModUser = :lastModUser")})
public class PagoMaestro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PAGO_MAESTRO")
    private Integer idPagoMaestro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAGO")
    private Float pago;
    @Column(name = "STATUS")
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAST_MOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModTime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LAST_MOD_USER")
    private String lastModUser;
    @JoinColumn(name = "ID_MAESTRO", referencedColumnName = "ID_MAESTRO")
    @ManyToOne(optional = false)
    private Maestro idMaestro;
    @JoinColumn(name = "ID_CLASE", referencedColumnName = "ID_CLASE")
    @ManyToOne(optional = false)
    private Clase idClase;

    public PagoMaestro() {
    }

    public PagoMaestro(Integer idPagoMaestro) {
        this.idPagoMaestro = idPagoMaestro;
    }

    public PagoMaestro(Integer idPagoMaestro, Date fecha, Date lastModTime, String lastModUser) {
        this.idPagoMaestro = idPagoMaestro;
        this.fecha = fecha;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdPagoMaestro() {
        return idPagoMaestro;
    }

    public void setIdPagoMaestro(Integer idPagoMaestro) {
        this.idPagoMaestro = idPagoMaestro;
    }

    public Float getPago() {
        return pago;
    }

    public void setPago(Float pago) {
        this.pago = pago;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Clase getIdClase() {
        return idClase;
    }

    public void setIdClase(Clase idClase) {
        this.idClase = idClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPagoMaestro != null ? idPagoMaestro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagoMaestro)) {
            return false;
        }
        PagoMaestro other = (PagoMaestro) object;
        if ((this.idPagoMaestro == null && other.idPagoMaestro != null) || (this.idPagoMaestro != null && !this.idPagoMaestro.equals(other.idPagoMaestro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.segundo.piso.beans.PagoMaestro[ idPagoMaestro=" + idPagoMaestro + " ]";
    }
    
}
