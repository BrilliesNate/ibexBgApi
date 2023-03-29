/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.eskomController;

import com.myPower24.ibexBgApi.ejb.DaoAbstractFacade;
import com.myPower24.ibexBgApi.ejb.entity.EskomFeeder;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author natha
 */
@Stateless
public class EskomFeederDOA extends DaoAbstractFacade<EskomFeeder> {

    public EskomFeeder findBySuburbName(String suburbName) {
        TypedQuery<EskomFeeder> query = getEm().createNamedQuery("EskomFeeder.findBySuburbName", EskomFeeder.class).setParameter("sName", suburbName);
        return query.getSingleResult();
    }

}
