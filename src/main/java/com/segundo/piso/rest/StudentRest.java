/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Alumno;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.StudentService;
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
@Path("/student")
public class StudentRest {
    
    @Autowired
    private StudentService studentService;
    
    @Path("/getStudents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> getStudents(@QueryParam("alumnos") boolean alumnos) {
        List<Alumno> students = studentService.getStudents(alumnos);
        for(Alumno alumno : students) {
            alumno.setStatusStr(alumno.getStatus());
        }
        
        return students;
    }
    
    @Path("/persistStudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON) 
    public Response persistStudent(Alumno alumno) {
        boolean isModification = alumno.getId() != null;
        Response response = studentService.validateStudentNumber(alumno.getCodigo(), alumno.isFromStudent(), isModification);
        
        if (response.isValid()) {
            alumno = studentService.saveUpdateStudent(alumno, isModification);
            
            if (alumno == null) {
                response.setValid(false);
                response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);                
            } else {
                response.setValid(true);
                response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
            }
        } 
        
        return response;
    }
    
    @Path("/getStudents/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> getStudentsByStatus(@QueryParam("status") boolean status) {
        List<Alumno> students = studentService.getStudentsByStatus(status);
        return students;
    }
    
    @Path("/changeToStudent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeToStudent(Alumno alumno) {
        String code = studentService.getLastCode(true);
        alumno.setCodigo(code);
        alumno.setAlumno(true);
        alumno.setFromStudent(true);
        return persistStudent(alumno);
    }
    
    @Path("/getAllStudents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alumno> getAllStudents(@QueryParam("status") boolean status) {
        List<Alumno> students = studentService.getStudentsByStatus(status);
        for(Alumno alumno : students) {
            alumno.setStatusStr(alumno.getStatus());
        }
        
        return students;
    }
    
    @Path("/alumnoById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Alumno getStudent(@QueryParam("idAlumno") int idAlumno) {
        Alumno alumno = studentService.getStudentById(idAlumno);
        return alumno;
    }
}
