/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Movimiento;
import com.segundo.piso.daos.DAOMovement;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 */
@Repository
public class DAOMovementImpl extends DAOBaseImpl<Movimiento> implements DAOMovement {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Movimiento> getMovementsByIdStudent(int idStudent, boolean status) {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Movimiento.class, "movement")
                .createAlias("movement.idAlumno", "student")
                .add(Restrictions.eq("student.idAlumno", idStudent))
                .add(Restrictions.eq("movement.status", status))
                .addOrder(Order.desc("movement.fechaInicio")) 
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    @Transactional
    public List<Movimiento> getAvailableMovements(int idStudent, boolean active) {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Movimiento.class, "movement")
                .createAlias("movement.idEvento", "event")
                .createAlias("movement.idAlumno", "student")
                .add(Restrictions.eq("student.idAlumno", idStudent))
                .add(Restrictions.gt("event.diasMes", 0))
                .add(Restrictions.eq("movement.activo", active))
                .add(Restrictions.eq("movement.status", active))
                .addOrder(Order.asc("movement.fechaInicio"))
                .list();
    }
}
