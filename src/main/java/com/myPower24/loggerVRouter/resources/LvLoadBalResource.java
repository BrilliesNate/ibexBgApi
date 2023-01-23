package com.myPower24.loggerVRouter.resources;

import com.myPower24.loggerVRouter.loggerConn.LoggerConnController;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("rest")
public class LvLoadBalResource {

    private static final Logger LOG = Logger.getLogger(LoggerConnController.class.getName());

    @EJB
    private LoggerConnController connController;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet(
            @QueryParam(value = "conType") String conType
    //            @QueryParam(value = "config") HashMap<String, Object> confMap
    ) throws Exception {
//        System.out.println("getParam serNum " + conType);

        if (conType == null) {

            // just respond with all con types.
        }
        if (conType.equals("ws")) {
            LOG.log(Level.INFO, "This is a websocket type: {0}", conType);

        }
        if (conType.equals("os")) {
            
            LOG.info("This is a raw object socket type: " + conType);
        }
//        System.out.println("Config " + confMap);
        Response resp = Response.ok(connController.getLwsConnInstance()).build();

        return resp;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String form) {
//        Response resp;
//        try {
        System.out.println("POST FORM: " + form);

        Response resp = Response.ok(connController.getLwsConnInstance()).build();
//        } catch (Exception ex) {
//            resp = Response.ok(ex.getMessage()).build();
//            Logger.getLogger(JakartaEE8Resource.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return resp;
    }
}
