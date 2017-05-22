/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Clase;
import com.segundo.piso.beans.EntradasSalidas;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.InsAndOutsService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author SARAGON
 */
@Path("/inOut")
public class InsAndOutsRest {
    
    @Autowired
    InsAndOutsService insAndOutsService;
    
    @Path("/getInsAndOuts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntradasSalidas> getInsAndOuts() {
        return insAndOutsService.getInsAndOuts();
    } 
    
    @Path("/persistInAndOut")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistInAndOut(EntradasSalidas inAndOut) {
        return insAndOutsService.saveInAndOut(inAndOut);
    }
}
