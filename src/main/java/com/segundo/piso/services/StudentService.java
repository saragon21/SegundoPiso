/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.Alumno;
import com.segundo.piso.beans.Filters;
import com.segundo.piso.beans.ReporteClases;
import com.segundo.piso.beans.ReporteMovimientosAlumno;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOStudent;
import com.segundo.piso.util.CommonValidations;
import com.segundo.piso.util.Patterns;
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
public class StudentService {

    @Autowired
    private DAOStudent daoStudent;

    public List<Alumno> getStudents(boolean alumnos) {
        return this.daoStudent.getStudentsByStudent(alumnos);
    }

    public Response validateStudentNumber(String studentNumber, boolean isStudent, boolean isModification) {
        boolean valid = true;
        Response response = CommonValidations.validText(studentNumber,
                Patterns.DIGITS, true);

        if (response.isValid()) {
            boolean correctCode = isStudent ? (Integer.valueOf(studentNumber) >= 1000)
                    : (Integer.valueOf(studentNumber) > 0 && Integer.valueOf(studentNumber) < 1000);
            if (correctCode) {
                if (!isModification) {
                    Alumno alumno = this.daoStudent.getEventByCode(studentNumber, isStudent);
                    if (alumno == null) {
                        valid = true;
                        response.setMessage("");
                    } else {
                        response.setMessage("El número de alumno ya esta asignado");
                        valid = false;
                    }
                } else {
                    response.setMessage("");
                }
            } else {
                String range = isStudent ? " mayor a 1000. " : " menor a 1000. ";
                response.setMessage("El código debe ser " + range);
                valid = false;
            }
        }

        response.setValid(valid);
        return response;
    }

    public Alumno saveUpdateStudent(Alumno alumno, boolean isModification) {
        alumno.setLastModTime(new Date());
        //TODO Session
        alumno.setLastModUser("saragon");

        if (!isModification) {
            alumno = this.daoStudent.save(alumno);
        } else {
            alumno = this.daoStudent.update(alumno);
        }

        return alumno;
    }

    public List<Alumno> getStudentsByStatus(boolean status) {
        return this.daoStudent.getAllRecords(Alumno.class, status, Order.asc("codigo"));
    }
    
    public String getLastCode(boolean alumno) {
        return this.daoStudent.getLastCode(alumno);
    }   
    
    public Alumno getStudentById(int idAlumno) {
        return this.daoStudent.getStudentById(idAlumno);
    }
}
