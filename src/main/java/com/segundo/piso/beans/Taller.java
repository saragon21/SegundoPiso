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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author saragon
 */
@Entity
@Table(name = "TALLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taller.findAll", query = "SELECT t FROM Taller t"),
    @NamedQuery(name = "Taller.findByIdTaller", query = "SELECT t FROM Taller t WHERE t.idTaller = :idTaller"),
    @NamedQuery(name = "Taller.findByTaller", query = "SELECT t FROM Taller t WHERE t.taller = :taller"),
    @NamedQuery(name = "Taller.findByFecha", query = "SELECT t FROM Taller t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Taller.findByCosto", query = "SELECT t FROM Taller t WHERE t.costo = :costo"),
    @NamedQuery(name = "Taller.findByPorcentajeArtista", query = "SELECT t FROM Taller t WHERE t.porcentajeArtista = :porcentajeArtista"),
    @NamedQuery(name = "Taller.findByPorcentajeSp", query = "SELECT t FROM Taller t WHERE t.porcentajeSp = :porcentajeSp"),
    @NamedQuery(name = "Taller.findByLastModTime", query = "SELECT t FROM Taller t WHERE t.lastModTime = :lastModTime"),
    @NamedQuery(name = "Taller.findByLastModUser", query = "SELECT t FROM Taller t WHERE t.lastModUser = :lastModUser")})
public class Taller implements Serializable {
    @OneToMany(mappedBy = "idTaller")
    private List<AsistenciaTaller> asistenciaTallerList;
    @Size(max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TALLER")
    private Integer idTaller;
    @Size(max = 255)
    @Column(name = "TALLER")
    private String taller;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private Float costo;
    @Column(name = "PORCENTAJE_ARTISTA")
    private Integer porcentajeArtista;
    @Column(name = "PORCENTAJE_SP")
    private Integer porcentajeSp;
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

    public Taller() {
    }

    public Taller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public Taller(Integer idTaller, Date fecha, Date lastModTime, String lastModUser) {
        this.idTaller = idTaller;
        this.fecha = fecha;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer getPorcentajeArtista() {
        return porcentajeArtista;
    }

    public void setPorcentajeArtista(Integer porcentajeArtista) {
        this.porcentajeArtista = porcentajeArtista;
    }

    public Integer getPorcentajeSp() {
        return porcentajeSp;
    }

    public void setPorcentajeSp(Integer porcentajeSp) {
        this.porcentajeSp = porcentajeSp;
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
        hash += (idTaller != null ? idTaller.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Taller)) {
            return false;
        }
        Taller other = (Taller) object;
        if ((this.idTaller == null && other.idTaller != null) || (this.idTaller != null && !this.idTaller.equals(other.idTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.segundo.piso.beans.Taller[ idTaller=" + idTaller + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<AsistenciaTaller> getAsistenciaTallerList() {
        return asistenciaTallerList;
    }

    public void setAsistenciaTallerList(List<AsistenciaTaller> asistenciaTallerList) {
        this.asistenciaTallerList = asistenciaTallerList;
    }
    
}
