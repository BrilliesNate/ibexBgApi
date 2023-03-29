/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.resources;

import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import com.myPower24.ibexBgApi.ibexController.MarketPriceDAO;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author natha
 */
@Path("period")
public class IbexPeriodPriceResource {

    @EJB
    MarketPriceDAO mpd;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet(
            @QueryParam(value = "start") String startDate,
            @QueryParam(value = "end") String endDate
    ) throws Exception {

//        System.out.println("getParam serNum " + conType);
        try {

            LocalDateTime startLdt = parseDate(startDate);
            LocalDateTime endLdt = parseDate(endDate);

            if (startLdt.isAfter(endLdt)) {
                throw new Exception("Start Date is after End Date");
            }
            if (Math.abs(ChronoUnit.DAYS.between(endLdt, startLdt)) > 7) {
                throw new Exception("Maximum allow days in period = 7");
            }

             ZoneId cetZone = ZoneId.of("CET");
            Date startOut = Date.from(startLdt.atZone(cetZone).toInstant());
            Date endOut = Date.from(endLdt.atZone(cetZone).toInstant());

            List<MarketPrice> findByTime = mpd.findPeriod(startOut, endOut);

//            System.out.println("Connection Testing " + ldt);
            Response resp = Response.ok(findByTime).build();

            return resp;
        } catch (DateTimeParseException e) {
            throw new Exception(e);
        }
    }

//    public static void main(String args[]) {
//        while (true) {
//            try {
//                String date = "2023-04-20T23:00:00";
//                LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
//                System.out.println(ldt);
//                Thread.sleep(250);
//            } catch (InterruptedException ex) {
//            }
//        }
//    }

    LocalDateTime parseDate(String date) {


  LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
        ldt = ldt.withMinute(0);
        ldt = ldt.withSecond(0);
        return ldt;
    }

}
