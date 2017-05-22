/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.Maestro;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.TeacherService;
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
@Path("/teacher")
public class TeacherRest {

    @Autowired
    private TeacherService teacherService;

    @Path("/getTeachers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Maestro> getStudents() {
        List<Maestro> teachers = teacherService.getTeachers();
        for(Maestro teacher : teachers) {
            teacher.setStatusStr(teacher.getStatus());
        }

        return teachers;
    }

    @Path("/persistTeacher")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistTeacher(Maestro maestro) {
        boolean isModification = maestro.getId() != null;
        Response response = new Response();
        maestro = teacherService.saveUpdateTeacher(maestro, isModification);

        if (maestro == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }

        return response;
    }
    
    @Path("/getTeachers/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Maestro> getTeachers(@QueryParam("status") boolean status) {
        List<Maestro> teachers = teacherService.getTeachers(status);
        for(Maestro teacher : teachers) {
            teacher.setStatusStr(teacher.getStatus());
        }

        return teachers;
    }
    
    @Path("/getTeachers/byClass")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Maestro> getTeachers(@QueryParam("idClase") int idClase, @QueryParam("status") boolean status) {
        List<Maestro> teachers = teacherService.getTeachersByIdClase(idClase, status);
        return teachers;
    }
}
