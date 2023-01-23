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
package com.myPower24.loggerVRouter.resources;

import com.myPower24.loggerVRouter.entity.LvRouterResponse;
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
    

    @Override
    public Response toResponse(Exception exception) {
        LvRouterResponse resp = new LvRouterResponse();
        resp.setErrorCode(Response.Status.BAD_REQUEST.getStatusCode());
        resp.setMessage("Server Error: " + exception.getMessage());
        resp.setSuccess(false);
        System.out.println("Custom server exception " + exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(resp)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
