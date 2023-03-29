/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ibexController;

import com.myPower24.ibexBgApi.ejb.DaoAbstractFacade;
import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author natha
 */
@Stateless
public class MarketPriceDAO extends DaoAbstractFacade<MarketPrice> {
    
    public List<MarketPrice> findByDate(Date date) {
    TypedQuery<MarketPrice> query = getEm().createNamedQuery("MarketPrice.findByDate", MarketPrice.class).setParameter("date", date);
    
    List<MarketPrice> resultList = query.getResultList(); 
    return resultList;
    }

    public MarketPrice findByTime(Date time) {
        return getEm().createNamedQuery("MarketPrice.findByTime", MarketPrice.class)
                .setParameter("time", time)
                .getSingleResult();
    }

    public List<MarketPrice> findAll() {
        TypedQuery<MarketPrice> query = getEm().createNamedQuery("MarketPrice.findAll", MarketPrice.class);
//        getEm().createNativeQuery("MarketPrice.findAll", MarketPrice.class);
        List<MarketPrice> resultList = query.getResultList();
        return resultList;
    }

    public List<MarketPrice> findPeriod(Date beginDate, Date endDate) {
//
        TypedQuery<MarketPrice> query = getEm()
                .createNamedQuery("MarketPrice.findInPeriod", MarketPrice.class)
                .setParameter("beginPeriod", beginDate)
                .setParameter("endPeriod", endDate);

        List<MarketPrice> resultList = query.getResultList();
        return resultList;
//        
//        return getEm()
//                .createNamedQuery("MarketPrice.findInPeriod", MarketPrice.class)
//                .setParameter("beginPeriod", beginDate)
//                .setParameter("endPeriod", endDate)
//                .getResultList();
    }

}
