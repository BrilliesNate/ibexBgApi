package com.myPower24.loggerVRouter;

import com.myPower24.loggerVRouter.resources.CustomServerErrorException;
import com.myPower24.loggerVRouter.resources.LvLoadBalResource;
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
        resources.add(LvLoadBalResource.class);
      
        return resources;
    }
}
