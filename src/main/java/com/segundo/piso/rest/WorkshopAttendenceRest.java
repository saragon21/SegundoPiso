/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.AsistenciaTaller;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.WorkshopAttendenceService;
import com.segundo.piso.util.CTEApp;
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
@Path("/workshopAttendence")
public class WorkshopAttendenceRest {
    
    @Autowired
    private WorkshopAttendenceService workshopAttendeService;
    
    @Path("/persistWorkshopAttendence")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistWorkshopAttendence(AsistenciaTaller asistenciaTaller) {
        return workshopAttendeService.save(asistenciaTaller);
    }
    
        
    @Path("/getWorkshopAttendence/byId")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AsistenciaTaller> getWorkShopAttendenceById(@QueryParam("idWorkshop") int id) {
        List<AsistenciaTaller> workshopAttendence = workshopAttendeService.getWorkshopAttendenceById(id, CTEApp.ACTIVE);
         return workshopAttendence;
    }
    
    @Path("/deleteWorkshopAttendence")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteWorkshopAttendence(AsistenciaTaller asistenciaTaller) {
        return workshopAttendeService.deleteWorkshopAttendence(asistenciaTaller);
    }
}
