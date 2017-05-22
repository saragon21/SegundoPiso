/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos;

import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteAlumno;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.beans.ReporteMovimientosAlumno;
import com.segundo.piso.beans.ReportePagoMaestros;
import java.util.List;

/**
 *
 * @author saragon
 */
public interface DAOReport {
    List<ReporteAlumno> getStudentsByExpiredMovement(int clasesRestantes);
    
    ReporteMovimientosAlumno movementsReport(Filters filters);
    
    List<ReporteClases> classesReport(Filters filters);
    
    ReportePagoMaestros teachersPayment(Filters filters, boolean pagadas);
}
