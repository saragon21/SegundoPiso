/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import com.segundo.piso.util.CTEApp;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "maestro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maestro.findAll", query = "SELECT m FROM Maestro m"),
    @NamedQuery(name = "Maestro.findByIdMaestro", query = "SELECT m FROM Maestro m WHERE m.idMaestro = :idMaestro"),
    @NamedQuery(name = "Maestro.findByNombre", query = "SELECT m FROM Maestro m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Maestro.findByTelefono", query = "SELECT m FROM Maestro m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "Maestro.findByStatus", query = "SELECT m FROM Maestro m WHERE m.status = :status"),
    @NamedQuery(name = "Maestro.findByLastModTime", query = "SELECT m FROM Maestro m WHERE m.lastModTime = :lastModTime"),
    @NamedQuery(name = "Maestro.findByLastModUser", query = "SELECT m FROM Maestro m WHERE m.lastModUser = :lastModUser")})
public class Maestro extends Base implements Serializable {
    @Column(name = "CORREO")
    private String correo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MAESTRO")
    private Integer idMaestro;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModTime;
    @Basic(optional = false)
    @Column(name = "LAST_MOD_USER")
    private String lastModUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaestro" , fetch = FetchType.EAGER)
    private List<Clase> claseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaestro" , fetch = FetchType.EAGER)
    private List<Asistencia> asistenciaList;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaestro")
    private List<PagoMaestro> pagoMaestroList;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PORCENTAJE")
    private boolean porcentaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAGO")
    private float pago;
    
    public Maestro() {
        this.status = true;
    }

    public Maestro(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public Maestro(Integer idMaestro, String nombre, boolean status, Date lastModTime, String lastModUser) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.status = status;
        this.lastModTime = lastModTime;
        this.lastModUser = lastModUser;
    }

    public Integer getId() {
        return idMaestro;
    }

    public void setId(Integer idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
    @JsonIgnore
    public List<Clase> getClaseList() {
        return claseList;
    }

    public void setClaseList(List<Clase> claseList) {
        this.claseList = claseList;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaestro != null ? idMaestro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maestro)) {
            return false;
        }
        Maestro other = (Maestro) object;
        if ((this.idMaestro == null && other.idMaestro != null) || (this.idMaestro != null && !this.idMaestro.equals(other.idMaestro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @XmlTransient
    @JsonIgnore
    public List<PagoMaestro> getPagoMaestroList() {
        return pagoMaestroList;
    }

    public void setPagoMaestroList(List<PagoMaestro> pagoMaestroList) {
        this.pagoMaestroList = pagoMaestroList;
    }
    
    public boolean getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public float getPago() {
        return pago;
    }

    public void setPago(float pago) {
        this.pago = pago;
    }
}
