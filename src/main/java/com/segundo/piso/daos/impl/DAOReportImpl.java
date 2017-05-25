/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Alumno;
import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.Movimiento;
import com.segundo.piso.beans.PagoMaestro;
import com.segundo.piso.beans.ReporteAlumno;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.beans.ReporteMovimientosAlumno;
import com.segundo.piso.beans.ReportePagoMaestros;
import com.segundo.piso.daos.DAOReport;
import com.segundo.piso.util.DateUtil;
import java.util.List;
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
 * @author saragon
 */
@Repository
public class DAOReportImpl implements DAOReport {

    @Autowired
    private SessionFactory sessionFactory;
    
    public DAOReportImpl() {
        System.out.println(sessionFactory);
    }
    
    @Override
    @Transactional
    public List<ReporteAlumno> getStudentsByExpiredMovement(int clasesRestantes) {
        System.out.println(sessionFactory);
        StringBuilder restriction = new StringBuilder();
        restriction.append("date_add(movimiento1_.fecha_inicio, interval 31 day) > curdate() ")
                .append("and date_add(movimiento1_.fecha_inicio, interval 31 day) < date_add(current_date(), interval 10 day)")
                .append("and movimiento1_.id_movimiento = asistencia2_.id_movimiento ");

        return this.sessionFactory.getCurrentSession()
                .createCriteria(Alumno.class, "alumno")
                .createAlias("alumno.movimientoList", "movimiento")
                .createAlias("alumno.asistenciaList", "asistencia")
                .createAlias("movimiento.idEvento", "evento")
                .setProjection(Projections.projectionList()
                        .add(Projections.property("alumno.codigo"), "codigo")
                        .add(Projections.property("alumno.nombre"), "nombre")
                        .add(Projections.min("asistencia.diasRestantes"), "clasesRestantes")
                        .add(Projections.max("movimiento.idMovimiento"))
                        .add(Projections.property("alumno.idAlumno"), "idAlumno")
                        .add(Projections.max("movimiento.fechaInicio"), "fecha")
                        .add(Projections.groupProperty("alumno.idAlumno")))
                .add(Restrictions.sqlRestriction(restriction.toString()))
                .add(Restrictions.eq("movimiento.activo", true))
                .add(Restrictions.gt("evento.diasMes", 1))
                .setResultTransformer(Transformers.aliasToBean(ReporteAlumno.class))
                .addOrder(Order.desc("asistencia.diasRestantes"))
                .list();
    }

    @Override
    @Transactional
    public ReporteMovimientosAlumno movementsReport(Filters filters) {
        ReporteMovimientosAlumno reporte = null;
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(Movimiento.class, "movimiento")
                .createAlias("movimiento.idEvento", "evento")
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.desc("fechaInicio"));

        if (filters.getEvento() > 0) {
            criteria.add(Restrictions.eq("idEvento.id", filters.getEvento()));
        } else {
            criteria.add(Restrictions.ge("evento.diasMes", 1));
        }

        if (filters.getFechaInicio() != null) {
            criteria.add(Restrictions.ge("fechaInicio", filters.getFechaInicio()));
        }

        if (filters.getFechaFin() != null) {
            criteria.add(Restrictions.le("fechaInicio", filters.getFechaFin()));
        }

        List<Movimiento> movements = criteria.list();
        if (movements != null && !movements.isEmpty()) {
            for (Movimiento movement : movements) {
                if (movement.getPorcentaje()) {
                    double percentage = movement.getDescuento() / 100;
                    double discount = movement.getCosto() * percentage;
                    movement.setDescuento((int) discount);
                }
            }

            reporte = totalMovements(criteria);
            reporte.setMovimientos(movements);
        }

        return reporte;
    }

    private ReporteMovimientosAlumno totalMovements(Criteria criteria) {
        ReporteMovimientosAlumno reporte
                = (ReporteMovimientosAlumno) criteria.setProjection(
                        Projections.projectionList()
                        .add(Projections.sum("costo"), "totalCosto")
                        .add(Projections.sum("pago"), "total")
                        .add(Projections.sum("descuento"), "totalDescuento"))
                .setResultTransformer(Transformers.aliasToBean(ReporteMovimientosAlumno.class))
                .uniqueResult();

        return reporte;
    }

    @Override
    @Transactional
    public List<ReporteClases> classesReport(Filters filters) {
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(Asistencia.class, "attendence")
                .createAlias("attendence.idClase", "clase")
                .createAlias("attendence.idAlumno", "alumno")
                .setProjection(Projections.projectionList()
                        .add(Projections.distinct(Projections.property("attendence.idAlumno")), "alumno")
                        .add(Projections.property("attendence.idClase"), "clase")
                        )
                .addOrder(Order.asc("clase.nombreClase"))
                .addOrder(Order.asc("alumno.nombre"))
                .setResultTransformer(Transformers.aliasToBean(ReporteClases.class));
        addFilters(filters, criteria);

        return criteria.list();
    }

    @Override
    @Transactional
    public ReportePagoMaestros teachersPayment(Filters filters, boolean pagadas) {
        ReportePagoMaestros report = new ReportePagoMaestros();
        float pago = 0.0f;
        Criteria criteria = this.sessionFactory.getCurrentSession()
                .createCriteria(PagoMaestro.class)
                .add(Restrictions.eq("status", pagadas))
                .addOrder(Order.asc("fecha"));
        addFilters(filters, criteria);

        report.setPagoMaestros(criteria.list());
        for (PagoMaestro payment : report.getPagoMaestros()) {
            if (payment.getPago() != null) {
                pago += payment.getPago();
            }
        }
        report.setPago(pago);

        return report;
    }

    private void addFilters(Filters filters, Criteria criteria) {
        if (filters.getClases() > 0) {
            criteria.add(Restrictions.eq("idClase.id", filters.getClases()));
        }

        if (filters.getFechaInicio() != null) {
            String fechaInicio = DateUtil.formatDate(filters.getFechaInicio(), DateUtil.YYYY_MM_DD_HH_MM_SS);
            System.out.println("FECHA INICIO" + fechaInicio);
            criteria.add(Restrictions.sqlRestriction("DATE(fecha) >= '" + fechaInicio + "' "));
        }

        if (filters.getFechaFin() != null) {
            String fechaFin = DateUtil.formatDate(filters.getFechaFin(), DateUtil.YYYY_MM_DD_HH_MM_SS);
            criteria.add(Restrictions.sqlRestriction("DATE(fecha) <= '" + fechaFin + "' "));
        }

        if (filters.getMaestro() > 0) {
            criteria.add(Restrictions.eq("idMaestro.id", filters.getMaestro()));
        }
    }
}
