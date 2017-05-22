/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.ReporteAlumno;
import com.segundo.piso.daos.DAOReport;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Sarai
 */
@Component
public class HomeService {
    
    @Autowired
    private DAOReport daoReport;
    
    public List<ReporteAlumno> getStudentExpired(int clasesRestantes) {
        System.out.println("GetStudents service");
        return daoReport.getStudentsByExpiredMovement(clasesRestantes);
    }
}
