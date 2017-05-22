/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.services;

import com.segundo.piso.beans.PagoMaestro;
import com.segundo.piso.beans.ReporteAsistencias;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOTeachersPayment;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author saragon
 */
@Component
public class TeachersPaymentService {
    
    @Autowired
    private DAOTeachersPayment daoTeachersPayment;
    
    public List<PagoMaestro> getTeachersPaymentsByStatus(boolean status) {
        return this.daoTeachersPayment.getAllRecords(PagoMaestro.class, status, Order.desc("fecha"));
    }
    
    public Response persistPayment(ReporteAsistencias reporte) {
        Response response;
        PagoMaestro payment = new PagoMaestro();
        payment.setStatus(true);
        payment.setFecha(new Date());
        payment.setLastModTime(new Date());
        payment.setLastModUser("saragon");
        payment.setIdClase(reporte.getClase());
        payment.setIdMaestro(reporte.getMaestro());
        payment.setPago(reporte.getPago());
        
        response = this.daoTeachersPayment.save(payment, reporte.getAttendence());
        
        return response;
    }
    
    public Response deletePayment(PagoMaestro payment) {
        Response response = new Response();
        payment.setStatus(false);
        payment.setLastModTime(new Date());
        payment.setLastModUser("saragon");
        
        payment = this.daoTeachersPayment.update(payment);
        
        if (payment == null) {
            response.setValid(false);
            response.setMessage("Error al eliminar el registro");
        } else {
            response.setValid(true);
            response.setMessage("Registro eliminado exitosamente");
        }
        
        return response;
    }
}
