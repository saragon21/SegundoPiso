/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos;

import com.segundo.piso.beans.Asistencia;
import com.segundo.piso.beans.PagoMaestro;
import com.segundo.piso.beans.Response;
import java.util.List;

/**
 *
 * @author saragon
 */
public interface DAOTeachersPayment extends DAOBase<PagoMaestro> {
    
    Response save(PagoMaestro payment, List<Asistencia> attendence);
}
