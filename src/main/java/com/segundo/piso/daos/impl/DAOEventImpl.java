/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Evento;
import com.segundo.piso.daos.DAOEvent;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 */
@Repository
public class DAOEventImpl extends DAOBaseImpl<Evento> implements DAOEvent {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Evento getEventByName(String paquete) {
        try {
            return (Evento) sessionFactory.getCurrentSession()
                    .createCriteria(Evento.class)
                    .add(Restrictions.eq("descripcion", paquete))
                    .uniqueResult();
        } catch (NoResultException e) {
            //TODO Log
            return null;
        }
    }

    @Override
    @Transactional
    public List<Evento> getEventsByStatusClass(boolean status, boolean clase) {
        return sessionFactory.getCurrentSession().createCriteria(Evento.class)
                .add(Restrictions.eq("status", status))
                .add(Restrictions.eq("clases", clase))
                .list();
    }
}
