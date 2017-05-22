/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.Movimiento;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOMovement;
import com.segundo.piso.util.CTEMessages;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class MovementService {

    @Autowired
    private DAOMovement daoMovement;
    
    public Movimiento saveMovement(Movimiento movement) {
        movement.setLastModTime(new Date());
        movement.setLastModUser("saragon");
        movement.setActivo(true);
        movement.setStatus(true);

        return daoMovement.save(movement);
    }
    
    public List<Movimiento> getMovementsByIdStudent(int idStudent, boolean active) {
        return daoMovement.getMovementsByIdStudent(idStudent, active);
    }
    
    public Response deleteMovement(int idMovement) {
        Response response = new Response();
        Movimiento movement = this.daoMovement.getRecordById(idMovement, Movimiento.class);

        if (movement.getAsistenciaList().isEmpty()) {
            movement.setStatus(false);
            movement.setLastModUser("saragon");
            this.daoMovement.update(movement);
            response.setMessage(CTEMessages.MOVEMENT_DELETED);
            response.setValid(true);
        } else {
            response.setMessage(CTEMessages.DELETE_MOVEMENT);
            response.setValid(false);
        }

        return response;
    }
}
