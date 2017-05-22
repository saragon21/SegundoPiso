/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.services;

import com.segundo.piso.beans.Response;
import com.segundo.piso.beans.Usuario;
import com.segundo.piso.daos.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SARAGON
 */
@Component
public class LoginService {
    
    @Autowired
    private DAOUser daoUser;
    
    public Response validateCredentials(Usuario loginUser) {
        Response response = new Response();
        boolean valid = false;
        
        if (!loginUser.getUsuario().isEmpty()) {
            if (loginUser.getPassword() != null && !loginUser.getPassword().isEmpty()) {
                Usuario objUser = daoUser.getUser(loginUser.getUsuario());
                if (objUser != null) {
                    if (loginUser.getPassword().equals(objUser.getPassword())) {
                        if (objUser.getStatus()) {
                            //TODO Session CTEUser.LOGGED_USER = objUser;
                            response.setMessage("");
                            valid = true;
                        } else {
                            response.setMessage("Usuario inexistente");
                        }
                    } else {
                        response.setMessage("Contraseña inválida");
                    }
                } else {
                    response.setMessage("Usuario inválido");
                }
            } else {
                response.setMessage("Contraseña requerida");
            }
        } else {
            response.setMessage("Usuario requerido");
        }
        
        response.setValid(valid);
        
        return response;
    }
}
