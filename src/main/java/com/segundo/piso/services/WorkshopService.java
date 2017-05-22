/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.Response;
import com.segundo.piso.beans.Taller;
import com.segundo.piso.daos.DAOWorkshop;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saragon
 */
@Component
public class WorkshopService {

    @Autowired
    DAOWorkshop daoWorkshop;

    public List<Taller> getWorkshops() {
        return daoWorkshop.getAllRecords(Taller.class, Order.asc("fecha"));
    }

    public Taller saveUpdateWorkshop(Taller taller, boolean isModification) {
        taller.setLastModTime(new Date());
        taller.setLastModUser("saragon");
        if (isModification) {
            return daoWorkshop.update(taller);
        } else {
            return daoWorkshop.save(taller);
        }
    }
    
    public List<Taller> getWorkshopsByDate(Date date) {
        return daoWorkshop.getAllRecords(Taller.class, Restrictions.gt("fecha", date));
    }
}
