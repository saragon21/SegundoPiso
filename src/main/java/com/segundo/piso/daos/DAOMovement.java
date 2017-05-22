/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Movimiento;
import java.util.List;

/**
 *
 * @author SARAGON
 */
public interface DAOMovement extends DAOBase<Movimiento> {
    
    List<Movimiento> getMovementsByIdStudent(int idStudent, boolean active);
    
    List<Movimiento> getAvailableMovements(int idStudent, boolean active);
}
