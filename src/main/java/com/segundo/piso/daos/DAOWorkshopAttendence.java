/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos;

import com.segundo.piso.beans.AsistenciaTaller;
import java.util.List;

/**
 *
 * @author saragon
 */
public interface DAOWorkshopAttendence extends DAOBase<AsistenciaTaller> {
    
    List<AsistenciaTaller> getWorkshopAttendenceById(int idWorkshop, boolean status);
}
