package com.myPower24.ibexBgApi;

import com.myPower24.ibexBgApi.resources.CustomServerErrorException;
import com.myPower24.ibexBgApi.resources.EmailResource;
import com.myPower24.ibexBgApi.resources.IbexPeriodPriceResource;
import com.myPower24.ibexBgApi.resources.IbexPriceResource;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("")
public class JakartaRestConfiguration extends Application {
      @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(CustomServerErrorException.class);
        resources.add(IbexPeriodPriceResource.class);
        resources.add(IbexPriceResource.class);
        resources.add(EmailResource.class);
      
        return resources;
    }
}
