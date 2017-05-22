/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.util;

import com.segundo.piso.beans.Response;

/**
 *
 * @author SARAGON
 */
public class CommonValidations {

    public static Response validText(String value, String pattern, boolean required) {
        Response response = new Response();
        boolean valid = false;

        if (value != null && !value.isEmpty()) {
            if (value.matches(pattern)) {
                response.setMessage("");
                valid = true;
            } else {
                response.setMessage(CTEMessages.INVALID_FORMAT);
            }
        } else {
            if (required) {
                response.setMessage(CTEMessages.REQUIRED_FIELD);
            } else {
                valid = true;
            }
        }

        response.setValid(valid);

        return response;
    }

    public static Response validNumber(Number value, boolean required) {
        Response response = new Response();
        boolean valid = false;

        if (value != null) {
            float number = value.floatValue();
            if (number <= 0) {
                response.setMessage(CTEMessages.NUMBER_VALUE);
            } else {
                response.setMessage("");
                valid = true;
            }
        } else {
            if (required) {
                response.setMessage(CTEMessages.REQUIRED_FIELD);
            } else {
                valid = true;
            }
        }

        response.setValid(valid);
        return response;
    }

}
