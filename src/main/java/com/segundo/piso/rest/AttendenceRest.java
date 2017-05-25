/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteAsistencias;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.AttendenceService;
import com.segundo.piso.util.CTEMessages;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author SARAGON
 */
@Path("attendence")
public class AttendenceRest {
    
    @Autowired
    private AttendenceService attendenceService; 
    
    @Path("/getAttendence/byStudent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asistencia> getAttendenceByStudent(@QueryParam("idAlumno") int idAlumno) {
        return attendenceService.getAttendenceByStudent(idAlumno);
    }
    
    @Path("/persistAttendence")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistAttendence(Asistencia asistencia) {
        return attendenceService.saveAttendence(asistencia);
    }
    
    @Path("/deleteAttendence")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAttendence(@QueryParam("idAsistencia") int idAsistencia) {
        return attendenceService.deleteAttendence(idAsistencia);
    }
    
    @Path("/clase")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ReporteAsistencias getAttendenceByClass(@QueryParam("idClase") int idClase,
            @QueryParam("pagadas") boolean pagadas, @QueryParam("idMaestro") int idMaestro) {
        return attendenceService.getAttendenceByClass(idClase, idMaestro, pagadas);
    }
    
    @Path("/attendence/student")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asistencia> getAttendenceByStudent(Filters filters) {
        return attendenceService.getAttendenceFiltered(filters);
    }
}
