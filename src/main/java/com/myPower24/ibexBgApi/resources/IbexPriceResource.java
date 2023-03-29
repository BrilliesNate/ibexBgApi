package com.myPower24.ibexBgApi.resources;

import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import com.myPower24.ibexBgApi.ibexController.MarketPriceDAO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *  /price //filter time /price/{time}
 *
 * @author
 */
@Path("price")
public class IbexPriceResource {

    private static final Logger LOG = Logger.getLogger(IbexPriceResource.class.getName());

    @EJB
    MarketPriceDAO mpd;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doGet(@QueryParam(value = "time") String time) throws Exception {

        try {

            LocalDateTime ldt = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
            ldt = ldt.withMinute(0);
            ldt = ldt.withSecond(0);
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            MarketPrice findByTime = mpd.findByTime(out);


//            ZoneId cetAllYear = ZoneId.of("Africa/Algiers");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
//            DateTimeFormatter demoFormatter
//                    = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z", Locale.ROOT);
//             SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//            String pattern = "dd.MM.yyyy HH:mm:ss";
//            DateFormat df = new SimpleDateFormat(pattern);
//            String todayAsString = df.format(findByTime.getPeriod());
//            
//            ZonedDateTime dateTime = LocalDateTime.parse(todayAsString, formatter).atZone(cetAllYear);
             
            
            System.out.println("Connection Testing " + ldt);
            Response resp = Response.ok(findByTime).build();
            
            System.out.println(findByTime);
//            Date date = resp.getDate();
            System.out.println(resp);

            return resp;
        } catch (DateTimeParseException e) {
            throw new Exception(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String form) {

        System.out.println("POST FORM: " + form);
        Response resp = Response.ok().build();

        return resp;
    }
}
