/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Usuario;
import com.segundo.piso.daos.DAOUser;
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
public class DAOUserImpl extends DAOBaseImpl<Usuario> implements DAOUser {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Usuario getUser(String userStr) {
        try {
            Usuario user = (Usuario) sessionFactory.getCurrentSession()
                    .createCriteria(Usuario.class)
                    .add(Restrictions.eq("usuario", userStr))
                    .uniqueResult();

            return user;
        } catch (NoResultException e) {
            //TODO Logging
            return null;
        }
    }
}
