/* Copyright (C) Solar MD (Pty) ltd - All Rights Reserved
 * 
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  
 *  Written by henry, 2021
 *  
 *  For more information http://www.solarmd.co.za/ 
 *  email: info@solarmd.co.za
 *  7 Alternator Ave, Montague Gardens, Cape Town, 7441 South Africa
 *  Phone: 021 555 2181
 *  
 */
package com.myPower24.ibexBgApi.resources;

import com.myPower24.ibexBgApi.ibexController.MarketPriceDAO;
import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author henry
 */
@Provider
public class CustomServerErrorException implements ExceptionMapper<Exception> {

    @EJB
    MarketPriceDAO marketPriceDao;

    @Override
    public Response toResponse(Exception exception) {
        ResponceBean resp = new ResponceBean();
        resp.setErrorCode(Response.Status.BAD_REQUEST.getStatusCode());
        resp.setMessage("Server Error: " + exception.getMessage());
        resp.setSuccess(false);
        List<MarketPrice> findAll = marketPriceDao.findAll();
        System.out.println("findall size " + findAll.size());
        System.out.println("Custom server exception " + exception.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(resp)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
