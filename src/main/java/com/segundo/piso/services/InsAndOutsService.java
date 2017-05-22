/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.EntradasSalidas;
import com.segundo.piso.beans.Response;
import com.segundo.piso.daos.DAOInAndOut;
import com.segundo.piso.util.CTEMessages;
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
public class InsAndOutsService {

    @Autowired
    private DAOInAndOut daoInAndOut;
    
    public List<EntradasSalidas> getInsAndOuts() {
        List<EntradasSalidas> entradasSalidas = daoInAndOut.getAllRecords(EntradasSalidas.class, Order.asc("fecha"));
        for (EntradasSalidas entradaSalida : entradasSalidas) {
            if (entradaSalida.getEntradaSalida()) {
                entradaSalida.setEntradaSalidaTxt(CTEMessages.ENTRADA);
            } else {
                entradaSalida.setEntradaSalidaTxt(CTEMessages.SALIDA);
            }
        }
        
        return entradasSalidas;
    }
    
    public Response saveInAndOut(EntradasSalidas inAndOut) {
        Response response = new Response();
        inAndOut.setEntradaSalida(inAndOut.getCantidad() >= 0);
        inAndOut.setLastModUser("saragon");
        inAndOut.setLastModTime(new Date());
        inAndOut = daoInAndOut.save(inAndOut);
        
        if (inAndOut == null) {
            response.setValid(false);
            response.setMessage(CTEMessages.ERROR_INSERT_UPDATE);
        } else {
            response.setValid(true);
            response.setMessage(CTEMessages.SUCCESS_INSERT_UPDATE);
        }
        
        return response;
    }
}
