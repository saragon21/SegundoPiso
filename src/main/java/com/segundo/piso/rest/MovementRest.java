/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Movimiento;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.MovementService;
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
@Path("/movement")
public class MovementRest {
    
    @Autowired
    private MovementService movementService;
    
    @Path("/persistMovement")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistMovement(Movimiento movimiento) {
        Response response = new Response();
        movimiento = movementService.saveMovement(movimiento);

        if (movimiento == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }

        return response;
    }
    
    @Path("/getMovements/student")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movimiento> getMovementsByStudent(@QueryParam("idAlumno") int idStudent, @QueryParam("status") boolean status) {
        return movementService.getMovementsByIdStudent(idStudent, status);
    }
    
    @Path("/deleteMovement")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMovement(@QueryParam("idMovimiento") int idMovimiento) {
        return movementService.deleteMovement(idMovimiento);
    }
}
