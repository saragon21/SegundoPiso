/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.rest;

import com.segundo.piso.beans.Response;
import com.segundo.piso.beans.Usuario;
import com.segundo.piso.services.LoginService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author SARAGON
 */
@Path("/login")
public class LoginRest {
    
    @Autowired
    private LoginService loginService; 
    
    @Path("/validate")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateLogin(Usuario usuario) {
        Response response = loginService.validateCredentials(usuario);
        return response;
    }
}
