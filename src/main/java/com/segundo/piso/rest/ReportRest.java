/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.Alumno;
import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.beans.ReporteMovimientosAlumno;
import com.segundo.piso.beans.ReportePagoMaestros;
import com.segundo.piso.services.ReportService;
import java.util.List;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author saragon
 */
@Path("/report")
public class ReportRest {
    
    @Autowired
    private ReportService reportService;
    
    @Path("/movements")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ReporteMovimientosAlumno movementsReport(Filters filters) {
        return reportService.movementsReport(filters);
    }
    
    @Path("/classes")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<ReporteClases> classesReport(Filters filters) {
        System.out.println(filters);
        return reportService.classesReport(filters);
    }
    
    @Path("/teachers/payment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ReportePagoMaestros teachersPaymentReport(Filters filters) {
        System.out.println(filters.getMaestro() + " " + filters.getClases());
        return reportService.teachersPaymentReport(filters, filters.isStatus());
    }
}
