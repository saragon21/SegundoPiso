/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.Maestro;
import com.segundo.piso.daos.DAOTeacher;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class TeacherService {
    @Autowired
    private DAOTeacher daoTeacher;
    
    public List<Maestro> getTeachers() {
        return this.daoTeacher.getAllRecords(Maestro.class,
               Order.asc("nombre"), Order.asc("id"));
    }
    
    public List<Maestro> getTeachers(boolean active) {
        return this.daoTeacher.getAllRecords(Maestro.class, active, Order.asc("nombre"));
    }
    
    public Maestro saveUpdateTeacher(Maestro maestro, boolean isModification) {
        maestro.setLastModTime(new Date());
        //TODO session
        maestro.setLastModUser("saragon");

        if (!isModification) {
            maestro = this.daoTeacher.save(maestro);
        } else {
            maestro = this.daoTeacher.update(maestro);
        }

        return maestro;
    }
    
    public List<Maestro> getTeachersByIdClase(int idClass, boolean status) {
        return this.daoTeacher.getTeachersByIdClass(idClass, status);
    }
}
