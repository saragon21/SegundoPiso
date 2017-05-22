/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.util;

/**
 *
 * @author SARAGON
 */
public class Patterns {
    
    public static String DIGITS = "[\\d]+";
    
    public static String LETTERS = "[a-zA-Z\\sáéíóúÁÉÍÓÚñÑ]+";
    
    public static String CURRENCY = "$[\\d.]+";
    
    public static String LOWER_LETTERS = "[a-z]{1}";
    
    public static String LETTERS_PUNCT = "[a-zA-Z\\s\\.]+";
    
    public static String LETTERS_DIGITS = "[a-zA-Z\\s\\dáéíóúÁÉÍÓÚñÑ]+";
    
    public static String MAIL = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+";
    
    public static String PHONE = "[\\d-]+";
}
