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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.id = :idEvento"),
    @NamedQuery(name = "Evento.findByDescripcion", query = "SELECT e FROM Evento e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Evento.findByCosto", query = "SELECT e FROM Evento e WHERE e.costo = :costo"),
    @NamedQuery(name = "Evento.findByDiasMes", query = "SELECT e FROM Evento e WHERE e.diasMes = :diasMes"),
    @NamedQuery(name = "Evento.findByStatus", query = "SELECT e FROM Evento e WHERE e.status = :status"),
    @NamedQuery(name = "Evento.findByLastModTime", query = "SELECT e FROM Evento e WHERE e.lastModTime = :lastModTime"),
    @NamedQuery(name = "Evento.findByLastModUser", query = "SELECT e FROM Evento e WHERE e.lastModUser = :lastModUser")})
public class Evento extends Base implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLASES")
    private boolean clases;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<EntradasSalidas> entradassalidasList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EVENTO")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private Float costo;
    @Column(name = "DIAS_MES")
    private Integer diasMes;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEvento")
    private List<Movimiento> movimientoList;

    public Evento() {
        status = true;
        diasMes = 0;
        costo = 0.0f;
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, String descripcion, boolean status, Date lastModTime, String lastModUser) {
        this.id = id;
        this.descripcion = descripcion;
        this.status = status;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer getDiasMes() {
        return diasMes;
    }

    public void setDiasMes(Integer diasMes) {
        this.diasMes = diasMes;
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

    @XmlTransient
    public List<Movimiento> getMovimientoList() {
        return movimientoList;
    }

    public void setMovimientoList(List<Movimiento> movimientoList) {
        this.movimientoList = movimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<EntradasSalidas> getEntradassalidasList() {
        return entradassalidasList;
    }

    public void setEntradassalidasList(List<EntradasSalidas> entradassalidasList) {
        this.entradassalidasList = entradassalidasList;
    }

    public boolean getClases() {
        return clases;
    }

    public void setClases(boolean clases) {
        this.clases = clases;
    }
    
}
