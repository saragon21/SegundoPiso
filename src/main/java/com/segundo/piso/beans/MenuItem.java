/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SARAGON
 */
@Entity
@Table(name = "menu_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenuItem.findAll", query = "SELECT m FROM MenuItem m"),
    @NamedQuery(name = "MenuItem.findByIdMenuItem", query = "SELECT m FROM MenuItem m WHERE m.idMenuItem = :idMenuItem"),
    @NamedQuery(name = "MenuItem.findByMenuItem", query = "SELECT m FROM MenuItem m WHERE m.menuItem = :menuItem"),
    @NamedQuery(name = "MenuItem.findByEvento", query = "SELECT m FROM MenuItem m WHERE m.evento = :evento"),
    @NamedQuery(name = "MenuItem.findByStatus", query = "SELECT m FROM MenuItem m WHERE m.status = :status")})
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MENU_ITEM")
    private Integer idMenuItem;
    @Basic(optional = false)
    @Column(name = "MENU_ITEM")
    private String menuItem;
    @Column(name = "EVENTO")
    private String evento;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @ManyToMany(mappedBy = "menuItemList")
    private List<Rol> rolList;
    @JoinColumn(name = "ID_MENU", referencedColumnName = "ID_MENU")
    @ManyToOne(optional = false)
    private Menu idMenu;

    public MenuItem() {
    }

    public MenuItem(Integer idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    public MenuItem(Integer idMenuItem, String menuItem, String status) {
        this.idMenuItem = idMenuItem;
        this.menuItem = menuItem;
        this.status = status;
    }

    public Integer getIdMenuItem() {
        return idMenuItem;
    }

    public void setIdMenuItem(Integer idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public Menu getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Menu idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuItem != null ? idMenuItem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenuItem)) {
            return false;
        }
        MenuItem other = (MenuItem) object;
        if ((this.idMenuItem == null && other.idMenuItem != null) || (this.idMenuItem != null && !this.idMenuItem.equals(other.idMenuItem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.escuela.beans.MenuItem[ idMenuItem=" + idMenuItem + " ]";
    }
    
}
