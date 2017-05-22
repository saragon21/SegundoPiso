/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Evento;
import java.util.List;


/**
 *
 * @author SARAGON
 */
public interface DAOEvent extends DAOBase<Evento> {
    
    Evento getEventByName(String evento);
    
    List<Evento> getEventsByStatusClass(boolean status, boolean clase);
}
