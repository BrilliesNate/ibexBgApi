/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.resources;

import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import com.myPower24.ibexBgApi.email.SendEmail;
import com.myPower24.ibexBgApi.ibexController.MarketPriceDAO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
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
 * @author natha
 */
@Path("email")
public class EmailResource {

    @EJB
    MarketPriceDAO mpd;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(
            @QueryParam(value = "username") String username,
            @QueryParam(value = "password") String password,
            @QueryParam(value = "receiver") String receiver
    ) {

        SendEmail se = new SendEmail(username, password, receiver);
        se.setMessage("welcome");
        se.sendMail();
        Response resp = Response.ok("Email Set up").build();

        return resp;
    }

}
