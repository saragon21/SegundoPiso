/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.AsistenciaTaller;
import com.segundo.piso.daos.DAOWorkshopAttendence;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author saragon
 */
@Repository
public class DAOWorkshopAttendenceImpl extends DAOBaseImpl<AsistenciaTaller> implements DAOWorkshopAttendence {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public List<AsistenciaTaller> getWorkshopAttendenceById(int idWorkshop, boolean status) {
        return this.sessionFactory.getCurrentSession().createCriteria(AsistenciaTaller.class, "workshopAttendence")
                .add(Restrictions.eq("idTaller.idTaller", idWorkshop))
                .add(Restrictions.eq("status", status))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.asc("idAlumno"))
                .list();
    }
}
