/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Maestro;
import com.segundo.piso.daos.DAOTeacher;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 */
@Repository
public class DAOTeacherImpl extends DAOBaseImpl<Maestro> implements DAOTeacher {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Maestro> getTeachersByName(String student) {
        return this.sessionFactory.getCurrentSession().createCriteria(Maestro.class)
                .add(Restrictions.like("nombre", student, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    @Transactional
    public List<Maestro> getTeachersByIdClass(int id, boolean status) {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Maestro.class, "teacher")
                .createAlias("teacher.claseList", "class")
                .add(Restrictions.eq("class.idClase", id))
                .add(Restrictions.eq("teacher.status", status))
                .list();
    }
}
