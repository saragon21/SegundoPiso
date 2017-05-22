/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.PagoMaestro;
import com.segundo.piso.beans.ReporteAsistencias;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.TeachersPaymentService;
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
 * @author saragon
 */
@Path("/pagos")
public class PaymentsRest {
    
    @Autowired
    TeachersPaymentService teachersPaymentService;
    
    @Path("/maestros") 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PagoMaestro> getTeachersPayments(@QueryParam("status") boolean status){
        return teachersPaymentService.getTeachersPaymentsByStatus(status);
    }
    
    @Path("/maestros/persist")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistPayment(ReporteAsistencias payment) {
        return teachersPaymentService.persistPayment(payment);
    }
    
    @Path("/maestros/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePayment(PagoMaestro payment) {
        return teachersPaymentService.deletePayment(payment);
    }
}
