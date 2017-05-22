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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimiento.findAll", query = "SELECT m FROM Movimiento m"),
    @NamedQuery(name = "Movimiento.findByIdMovimiento", query = "SELECT m FROM Movimiento m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "Movimiento.findByCosto", query = "SELECT m FROM Movimiento m WHERE m.costo = :costo"),
    @NamedQuery(name = "Movimiento.findByDescuento", query = "SELECT m FROM Movimiento m WHERE m.descuento = :descuento"),
    @NamedQuery(name = "Movimiento.findByPago", query = "SELECT m FROM Movimiento m WHERE m.pago = :pago"),
    @NamedQuery(name = "Movimiento.findByFechaInicio", query = "SELECT m FROM Movimiento m WHERE m.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Movimiento.findByStatus", query = "SELECT m FROM Movimiento m WHERE m.status = :status"),
    @NamedQuery(name = "Movimiento.findByLastModTime", query = "SELECT m FROM Movimiento m WHERE m.lastModTime = :lastModTime"),
    @NamedQuery(name = "Movimiento.findByLastModUser", query = "SELECT m FROM Movimiento m WHERE m.lastModUser = :lastModUser")})
public class Movimiento implements Serializable {
    @OneToMany(mappedBy = "idMovimiento")
    private List<EntradasSalidas> entradassalidasList;
    @Column(name = "PORCENTAJE")
    private boolean porcentaje;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private boolean activo;
    @OneToMany(mappedBy = "idMovimiento", fetch = FetchType.EAGER)
    private List<Asistencia> asistenciaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MOVIMIENTO")
    private Integer idMovimiento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    @XmlElement
    private Float costo;
    @Column(name = "DESCUENTO")
    private Integer descuento;
    @Column(name = "PAGO")
    private Float pago;
    @Basic(optional = false)
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
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
    @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "ID_ALUMNO")
    @ManyToOne(optional = false)
    private Alumno idAlumno;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO")
    @ManyToOne(optional = false)
    private Evento idEvento;

    public Movimiento() {
    }

    public Movimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimiento(Integer idMovimiento, Date fechaInicio, boolean status, Date lastModTime, String lastModUser) {
        this.idMovimiento = idMovimiento;
        this.fechaInicio = fechaInicio;
        this.status = status;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Float getPago() {
        return pago;
    }

    public void setPago(Float pago) {
        this.pago = pago;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimiento)) {
            return false;
        }
        Movimiento other = (Movimiento) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.escuela.beans.Movimiento[ idMovimiento=" + idMovimiento + " ]";
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    @XmlTransient
    @JsonIgnore
    public List<EntradasSalidas> getEntradassalidasList() {
        return entradassalidasList;
    }

    public void setEntradassalidasList(List<EntradasSalidas> entradassalidasList) {
        this.entradassalidasList = entradassalidasList;
    }
    
}
