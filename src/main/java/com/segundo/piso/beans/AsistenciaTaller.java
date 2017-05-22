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
@Table(name = "ASISTENCIA_TALLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsistenciaTaller.findAll", query = "SELECT a FROM AsistenciaTaller a"),
    @NamedQuery(name = "AsistenciaTaller.findByIdAsistenciaTaller", query = "SELECT a FROM AsistenciaTaller a WHERE a.idAsistenciaTaller = :idAsistenciaTaller"),
    @NamedQuery(name = "AsistenciaTaller.findByPago", query = "SELECT a FROM AsistenciaTaller a WHERE a.pago = :pago"),
    @NamedQuery(name = "AsistenciaTaller.findByLastModTime", query = "SELECT a FROM AsistenciaTaller a WHERE a.lastModTime = :lastModTime"),
    @NamedQuery(name = "AsistenciaTaller.findByLastModUser", query = "SELECT a FROM AsistenciaTaller a WHERE a.lastModUser = :lastModUser")})
public class AsistenciaTaller implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAGO")
    private Float pago;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "STATUS")
    private Boolean status;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASISTENCIA_TALLER")
    private Integer idAsistenciaTaller;
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
    @JoinColumn(name = "ID_TALLER", referencedColumnName = "ID_TALLER")
    @ManyToOne
    private Taller idTaller;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne
    private Alumno idAlumno;

    public AsistenciaTaller() {
    }

    public AsistenciaTaller(Integer idAsistenciaTaller) {
        this.idAsistenciaTaller = idAsistenciaTaller;
    }

    public AsistenciaTaller(Integer idAsistenciaTaller, Date lastModTime, String lastModUser) {
        this.idAsistenciaTaller = idAsistenciaTaller;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdAsistenciaTaller() {
        return idAsistenciaTaller;
    }

    public void setIdAsistenciaTaller(Integer idAsistenciaTaller) {
        this.idAsistenciaTaller = idAsistenciaTaller;
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

    public Taller getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Taller idTaller) {
        this.idTaller = idTaller;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistenciaTaller != null ? idAsistenciaTaller.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsistenciaTaller)) {
            return false;
        }
        AsistenciaTaller other = (AsistenciaTaller) object;
        if ((this.idAsistenciaTaller == null && other.idAsistenciaTaller != null) || (this.idAsistenciaTaller != null && !this.idAsistenciaTaller.equals(other.idAsistenciaTaller))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.segundo.piso.beans.AsistenciaTaller[ idAsistenciaTaller=" + idAsistenciaTaller + " ]";
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getPago() {
        return pago;
    }

    public void setPago(Float pago) {
        this.pago = pago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
