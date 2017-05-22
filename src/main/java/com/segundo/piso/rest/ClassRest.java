/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Clase;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.ClassService;
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
@Path("/clase")
public class ClassRest {
    
    @Autowired
    private ClassService classService;
    
    @Path("/getClases")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clase> getClasses() {
        List<Clase> classes = classService.getClases();
        for(Clase clase : classes) {
            clase.setStatusStr(clase.getStatus());
        }

        return classes;
    }
    
    @Path("/persistClass")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistClass(Clase clase) {
        boolean isModification = clase.getId() != null;
        Response response = new Response();
        clase = classService.saveUpdateClase(clase, isModification);

        if (clase == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }

        return response;
    }
    
    @Path("/getClases/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clase> getClassesByStatus(@QueryParam("status") boolean status) {
        List<Clase> classes = classService.getClasesByStatus(status);
        return classes;
    }
    
    @Path("/byMaestro")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clase> getClassesByStatus(@QueryParam("idMaestro") int idMaestro, @QueryParam("status") boolean status) {
        List<Clase> classes = classService.getClasesByTeacher(idMaestro, status);
        return classes;
    }
}
