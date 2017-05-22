/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.beans.ReporteMovimientosAlumno;
import com.segundo.piso.beans.ReportePagoMaestros;
import com.segundo.piso.daos.DAOReport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saragon
 */
@Component
public class ReportService {
    
    @Autowired
    private DAOReport daoReport;
    
    public ReporteMovimientosAlumno movementsReport(Filters filters) {
        return daoReport.movementsReport(filters);
    }
    
    public List<ReporteClases> classesReport(Filters filters) {
        return daoReport.classesReport(filters);
    }
    
    public ReportePagoMaestros teachersPaymentReport(Filters filters, boolean pagados) {
        return daoReport.teachersPayment(filters, pagados);
    }
}
