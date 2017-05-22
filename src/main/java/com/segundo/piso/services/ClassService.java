/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.Clase;
import com.segundo.piso.daos.DAOClass;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class ClassService {
    
    @Autowired
    private DAOClass daoClass;

    public List<Clase> getClases() {
        return this.daoClass.getAllRecords(Clase.class,
                Order.asc("id"));
    }
    
    public Clase saveUpdateClase(Clase clase, boolean isModification) {
        clase.setLastModTime(new Date());
        //TODO
        clase.setLastModUser("saragon");

        if (!isModification) {
            clase = this.daoClass.save(clase);
        } else {
            clase = this.daoClass.update(clase);
        }

        return clase;
    }
    
    public List<Clase> getClasesByStatus(boolean status) {
        return this.daoClass.getAllRecords(Clase.class, status, Order.asc("nombreClase"));
    }
    
    public List<Clase> getClasesByTeacher(int idMaestro, boolean status) {
        List<Criterion> restrictions = new ArrayList<>();
        restrictions.add(Restrictions.eq("idMaestro.idMaestro", idMaestro));
        restrictions.add(Restrictions.eq("status", status));
        return this.daoClass.getAllRecords(Clase.class, (Criterion[])restrictions.toArray());
    }
}
