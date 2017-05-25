/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.daos.DAOAttendence;
import com.segundo.piso.util.DateUtil;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SARAGON
 */
@Repository
public class DAOAttendenceImpl extends DAOBaseImpl<Asistencia> implements DAOAttendence {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Asistencia> getAttendenceByIdStudent(int idStudent) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Asistencia.class, "attendence")
                .createAlias("attendence.idAlumno", "student")
                .add(Restrictions.eq("student.idAlumno", idStudent))
                .addOrder(Order.desc("attendence.fecha"))
                .addOrder(Order.asc("attendence.diasRestantes"))
                .list();
    }

    @Override
    @Transactional
    public long getDaysUsed(int idStudent, int idMovement) {
        try {
            return (long) this.sessionFactory.getCurrentSession()
                    .createCriteria(Asistencia.class, "attendence")
                    .createAlias("attendence.idAlumno", "student")
                    .createAlias("attendence.idMovimiento", "movement")
                    .setProjection(Projections.count("attendence.diasRestantes"))
                    .add(Restrictions.eq("student.idAlumno", idStudent))
                    .add(Restrictions.eq("movement.idMovimiento", idMovement))
                    .uniqueResult();
        } catch (NoResultException | NullPointerException e) {
            //log
            return 0;
        }
    }
    
    @Override
    @Transactional
    public List<Asistencia> getAttendenceByClass(int idClase, int idMaestro, boolean pagadas) {
        return this.sessionFactory.getCurrentSession()
                .createCriteria(Asistencia.class, "attendence")
                .createAlias("attendence.idMaestro", "teacher")
                .createAlias("attendence.idClase", "clase")
                .add(Restrictions.eq("teacher.idMaestro", idMaestro))
                .add(Restrictions.eq("clase.idClase", idClase))
                .add(Restrictions.eq("pagada", pagadas))
                .addOrder(Order.asc("fecha"))
                .list();
    }
    
    @Override
    @Transactional
    public List<Asistencia> getAttendenceFiltered(Filters filters) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(Asistencia.class, "attendence");
        addFilters(filters, criteria);

        return criteria.list();
    }
    
    private void addFilters(Filters filters, Criteria criteria) {
        if (filters.getClases() > 0) {
            criteria.add(Restrictions.eq("idClase.id", filters.getClases()));
        }

        if (filters.getFechaInicio() != null) {
            String fechaInicio = DateUtil.formatDate(filters.getFechaInicio(), DateUtil.YYYY_MM_DD_HH_MM_SS);
            criteria.add(Restrictions.sqlRestriction("DATE(fecha) >= '" + fechaInicio + "' "));
        }

        if (filters.getFechaFin() != null) {
            String fechaFin = DateUtil.formatDate(filters.getFechaFin(), DateUtil.YYYY_MM_DD_HH_MM_SS);
            criteria.add(Restrictions.sqlRestriction("DATE(fecha) <= '" + fechaFin + "' "));
        }

        if (filters.getMaestro() > 0) {
            criteria.add(Restrictions.eq("idMaestro.id", filters.getMaestro()));
        }
        
        if (filters.getAlumno() > 0) {
            criteria.add(Restrictions.eq("idAlumno.id", filters.getAlumno()));
        }
    }
}
