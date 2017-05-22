/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Parametro;
import com.segundo.piso.daos.DAOParameter;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saragon
 */
@Repository
public class DAOParameterImpl extends DAOBaseImpl<Parametro> implements DAOParameter {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public Parametro getParameterByParameter(String parameter, boolean status) {
        return (Parametro) this.sessionFactory.getCurrentSession()
                .createCriteria(Parametro.class)
                .add(Restrictions.eq("parametro", parameter))
                .add(Restrictions.eq("status", status))
                .uniqueResult();
    }
}
