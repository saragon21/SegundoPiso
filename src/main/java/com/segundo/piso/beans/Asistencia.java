/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import com.segundo.piso.util.DateUtil;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByLastModTime", query = "SELECT a FROM Asistencia a WHERE a.lastModTime = :lastModTime"),
    @NamedQuery(name = "Asistencia.findByLastModUser", query = "SELECT a FROM Asistencia a WHERE a.lastModUser = :lastModUser")})
public class Asistencia implements Serializable {
    @JoinColumn(name = "ID_MOVIMIENTO", referencedColumnName = "ID_MOVIMIENTO")
    @ManyToOne
    private Movimiento idMovimiento;
    @Column(name = "DIAS_RESTANTES")
    private Integer diasRestantes;
    @JoinColumn(name = "ID_MAESTRO", referencedColumnName = "ID_MAESTRO")
    @ManyToOne(optional = false)
    private Maestro idMaestro;
    @JoinColumn(name = "ID_CLASE", referencedColumnName = "ID_CLASE")
    @ManyToOne
    private Clase idClase;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASISTENCIA")
    private Integer idAsistencia;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModTime;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_USER")
    private String lastModUser;
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne
    private Alumno idAlumno;
    @Column(name = "PAGADA")
    private boolean pagada;
    @Transient
    private String fechaStr;

    public Asistencia() {
    }

    public Asistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Asistencia(Integer idAsistencia, Date fecha, Date lastModTime, String lastModUser) {
        this.idAsistencia = idAsistencia;
        this.fecha = fecha;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
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

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.escuela.beans.Asistencia[ idAsistencia=" + idAsistencia + " ]";
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

    public Integer getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(Integer diasRestantes) {
        this.diasRestantes = diasRestantes;
    }

    public Movimiento getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Movimiento idMovimiento) {
        this.idMovimiento = idMovimiento;
    }
    
    public boolean getPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }
    
    public String getFechaStr() {
        fechaStr = DateUtil.formatDate(fecha, DateUtil.DD_MM_YYYY);
        return fechaStr;
    }
}
