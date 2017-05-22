/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.segundo.piso.daos;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 *
 * @author SARAGON
 * @param <E>
 */
public interface DAOBase<E> {
    
    E save(E object);
    
    E update(E object);
    
    E delete(E object);
    
    E merge(E object);
    
    E getRecordById(int id, Class clazz);
    
    List<E> getAllRecords(Class clazz, Order ... orderBy);
    
    List<E> getAllRecords(Class clazz, boolean status, Order ... orderBy);
    
    List<E> getAllRecords(Class clazz, Criterion ... restrictions);
}
