/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.AsistenciaTaller;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOWorkshopAttendence;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saragon
 */
@Component
public class WorkshopAttendenceService {
    
    @Autowired
    DAOWorkshopAttendence daoWorkshopAttendence;
    
    public Response save(AsistenciaTaller asistenciaTaller) {
        Response response = new Response();
        asistenciaTaller.setLastModUser("saragon");
        asistenciaTaller.setLastModTime(new Date());
        asistenciaTaller.setFecha(new Date());
        asistenciaTaller = daoWorkshopAttendence.save(asistenciaTaller);
        
        if (asistenciaTaller == null) {
            response.setValid(false);
            response.setMessage("Error al insertar la asistencia al taller");
        } else {
            response.setValid(true);
            response.setMessage("Registro insertado correctamente");
        }
        
        return response;
    }
    
    public List<AsistenciaTaller> getWorkshopAttendenceById(int idWorkshop, boolean active) {
        return daoWorkshopAttendence.getWorkshopAttendenceById(idWorkshop, active);
    }
    
    public Response deleteWorkshopAttendence(AsistenciaTaller asistenciaTaller) {
        Response response = new Response();
        asistenciaTaller.setLastModTime(new Date());
        asistenciaTaller.setLastModUser("saragon");
        asistenciaTaller.setStatus(false);
        
        asistenciaTaller = daoWorkshopAttendence.update(asistenciaTaller);
        
        if (asistenciaTaller == null) {
            response.setValid(false);
            response.setMessage("Error al eliminar la asistencia al taller");
        } else {
            response.setValid(true);
            response.setMessage("Registro eliminado correctamente");
        }
        
        return response; 
    }
    
}
