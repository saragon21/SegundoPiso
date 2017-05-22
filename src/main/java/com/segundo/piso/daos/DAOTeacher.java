/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Maestro;
import java.util.List;

/**
 *
 * @author SARAGON
 */
public interface DAOTeacher extends DAOBase<Maestro> {
    
    List<Maestro> getTeachersByName(String teacher);
    
    List<Maestro> getTeachersByIdClass(int id, boolean status);
}
