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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "entradassalidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EntradasSalidas.findAll", query = "SELECT e FROM EntradasSalidas e"),
    @NamedQuery(name = "EntradasSalidas.findByIdEntradaSalida", query = "SELECT e FROM EntradasSalidas e WHERE e.idEntradaSalida = :idEntradaSalida"),
    @NamedQuery(name = "EntradasSalidas.findByDescripcion", query = "SELECT e FROM EntradasSalidas e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EntradasSalidas.findByCantidad", query = "SELECT e FROM EntradasSalidas e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "EntradasSalidas.findByFecha", query = "SELECT e FROM EntradasSalidas e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EntradasSalidas.findByEntradaSalida", query = "SELECT e FROM EntradasSalidas e WHERE e.entradaSalida = :entradaSalida"),
    @NamedQuery(name = "EntradasSalidas.findByLastModTime", query = "SELECT e FROM EntradasSalidas e WHERE e.lastModTime = :lastModTime"),
    @NamedQuery(name = "EntradasSalidas.findByLastModUser", query = "SELECT e FROM EntradasSalidas e WHERE e.lastModUser = :lastModUser")})
public class EntradasSalidas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ENTRADA_SALIDA")
    private Integer idEntradaSalida;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private Float cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENTRADA_SALIDA")
    private boolean entradaSalida;
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
    @JoinColumn(name = "ID_MOVIMIENTO", referencedColumnName = "ID_MOVIMIENTO")
    @ManyToOne
    private Movimiento idMovimiento;
    @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO")
    @ManyToOne(optional = false)
    private Evento idEvento;
    @Transient
    private String entradaSalidaTxt;

    public EntradasSalidas() {
    }

    public EntradasSalidas(Integer idEntradaSalida) {
        this.idEntradaSalida = idEntradaSalida;
    }

    public EntradasSalidas(Integer idEntradaSalida, Date fecha, boolean entradaSalida, Date lastModTime, String lastModUser) {
        this.idEntradaSalida = idEntradaSalida;
        this.fecha = fecha;
        this.entradaSalida = entradaSalida;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getIdEntradaSalida() {
        return idEntradaSalida;
    }

    public void setIdEntradaSalida(Integer idEntradaSalida) {
        this.idEntradaSalida = idEntradaSalida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean getEntradaSalida() {
        return entradaSalida;
    }

    public void setEntradaSalida(boolean entradaSalida) {
        this.entradaSalida = entradaSalida;
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

    public Movimiento getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Movimiento idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public String getEntradaSalidaTxt() {
        return entradaSalidaTxt;
    }

    public void setEntradaSalidaTxt(String entradaSalidaTxt) {
        this.entradaSalidaTxt = entradaSalidaTxt;
    }

    @Override
    public String toString() {
        return "com.segundo.piso.beans.Entradassalidas[ idEntradaSalida=" + idEntradaSalida + " ]";
    }
    
}
