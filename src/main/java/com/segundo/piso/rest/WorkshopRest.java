/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.rest;

import com.segundo.piso.beans.Response;
import com.segundo.piso.beans.Taller;
import com.segundo.piso.services.WorkshopService;
import com.segundo.piso.util.CTEMessages;
import com.segundo.piso.util.DateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
@Path("/workshop")
public class WorkshopRest {

    @Autowired
    private WorkshopService workshopService;

    @Path("/getWorkshops")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Taller> getWorkshops() {
        return workshopService.getWorkshops();
    }

    @Path("/persistWorkshop")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistWork(Taller taller) {
        boolean isModification = taller.getIdTaller() != null;
        Response response = new Response();
        taller = workshopService.saveUpdateWorkshop(taller, isModification);

        if (taller == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }
        
        return response;
    }
    
    @Path("/getWorkshops/byDate")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Taller> getWorkShopsByDate(@QueryParam("date") String date) {
        date = date.substring(0, 10);
        Date today = DateUtil.formatDate(date, DateUtil.YYYY_MM_DD);
        return workshopService.getWorkshopsByDate(today);
    }
}
