/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import com.segundo.piso.beans.Usuario;

/**
 *
 * @author SARAGON
 */
public interface DAOUser extends DAOBase<Usuario> {
    
    Usuario getUser(String usuario);
    
}
