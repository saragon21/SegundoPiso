/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.PagoMaestro;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOAttendence;
import com.segundo.piso.daos.DAOTeachersPayment;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saragon
 */
@Repository
public class DAOTeachersPaymentImpl extends DAOBaseImpl<PagoMaestro> implements DAOTeachersPayment {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private DAOAttendence daoAttendence;
    
    @Override
    @Transactional
    public Response save(PagoMaestro payment, List<Asistencia> attendences) {
        Response response = new Response();
        
        payment = save(payment);
        if (payment != null) {
            for (Asistencia attendence : attendences) {
                attendence.setPagada(true);
                attendence.setLastModTime(new Date());
                attendence = daoAttendence.update(attendence);
                if (attendence == null) {
                    response.setValid(false);
                    response.setMessage("Error al actualizar las asitencias");
                    break;
                } else {
                    response.setValid(true);
                    response.setMessage("Registro guardado correctamente");
                }
            }
        } else {
            response.setValid(false);
            response.setMessage("Error al guardar el registro");
        }
        
        return response;
    }
}
