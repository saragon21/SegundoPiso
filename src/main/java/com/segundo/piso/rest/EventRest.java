/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Evento;
import com.segundo.piso.beans.Response;
import com.segundo.piso.services.EventService;
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
@Path("/event")
public class EventRest {
    
    @Autowired
    EventService eventService;
    
    @Path("/getEvents")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getEvents() {
        List<Evento> events = eventService.getEvents();
        for(Evento event : events) {
            event.setStatusStr(event.getStatus());
            event.setClaseStr(event.getClases());
        }

        return events;
    }
    
    @Path("/persistEvent")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response persistClass(Evento evento) {
        boolean isModification = evento.getId() != null;
        Response response = new Response();
        evento = eventService.saveUpdateEvent(evento, isModification);

        if (evento == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }

        return response;
    }
    
    @Path("/getEvents/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Evento> getEventsByStatus(@QueryParam("status") boolean status, @QueryParam("clase") boolean clase) {
        return eventService.getEventsByStatus(status, clase);
    }
}
