/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.Filters;
import java.util.List;

/**
 *
 * @author SARAGON
 */
public interface DAOAttendence extends DAOBase<Asistencia> {
    
    List<Asistencia> getAttendenceByIdStudent(int idStudent);
    
    long getDaysUsed(int idStudent, int idMovement);
    
    List<Asistencia> getAttendenceByClass(int idClase, int idMaestro, boolean pagadas);
    
    List<Asistencia> getAttendenceFiltered(Filters filters);
}
