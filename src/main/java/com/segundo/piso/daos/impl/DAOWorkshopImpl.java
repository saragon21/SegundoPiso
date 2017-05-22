/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segundo.piso.daos.impl;
import com.segundo.piso.beans.Taller;
import com.segundo.piso.daos.DAOWorkshop;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author saragon
 */
@Repository
public class DAOWorkshopImpl extends DAOBaseImpl<Taller> implements DAOWorkshop {
    
    @Autowired
    private SessionFactory sessionFactory;

}
