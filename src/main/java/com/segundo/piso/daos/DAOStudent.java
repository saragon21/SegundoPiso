/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Alumno;
import java.util.List;

/**
 *
 * @author SARAGON
 */
public interface DAOStudent extends DAOBase<Alumno> {
    
    Alumno getEventByCode(String code, boolean alumno);
    
    List<Alumno> getStudentsByNameCode(String student, String code);
    
    List<Alumno> getStudentsByName(String student);
    
    List<Alumno> getStudentsByCode(String code);
    
    List<Alumno> getStudentsByStudent(boolean student);
    
    String getLastCode(boolean alumno);
    
    Alumno getStudentById(int idAlumno);
}
