/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.Evento;
import com.segundo.piso.daos.DAOEvent;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class EventService {
    
    @Autowired
    DAOEvent daoEvent;
    
    public List<Evento> getEvents() {
        return this.daoEvent.getAllRecords(Evento.class,
                Order.asc("descripcion"));
    }
    
    public Evento saveUpdateEvent(Evento evento, boolean isModification) {
        evento.setLastModTime(new Date());
        evento.setLastModUser("saragon"); //TODO Autentificacion

        if (!isModification) {
            evento = this.daoEvent.save(evento);
        } else {
            evento = this.daoEvent.update(evento);
        }

        return evento;
    }
    
    public List<Evento> getEventsByStatus(boolean status, boolean clase) {
        return this.daoEvent.getEventsByStatusClass(clase, status);
    }
}
