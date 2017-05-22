/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.ReporteAlumno;
import com.segundo.piso.services.HomeService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sarai
 */
@Path("/home")
public class HomeRest {
    
    @Autowired
    private HomeService homeService;
    
    @Path("/students/expired")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ReporteAlumno> getStudentExpired(@QueryParam("clasesRestantes") int clasesRestantes) {
        System.out.println("GetStudents");
        List<ReporteAlumno> reportes = homeService.getStudentExpired(clasesRestantes);
        for (ReporteAlumno reporte : reportes) {
            System.out.println(reporte.getFechaVencimiento());
            reporte.setFechaVencimiento(reporte.getFechaVencimiento());
        }
        
        return reportes;
    }
}
