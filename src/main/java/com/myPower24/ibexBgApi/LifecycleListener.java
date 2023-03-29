/* Copyright (C) Solar MD (Pty) ltd - All Rights Reserved
 * 
 *  Unauthorized copying of this file, via any medium is strictly prohibited
 *  Proprietary and confidential
 *  
 *  Written by nathan, 2022
 *  
 *  For more information http://www.solarmd.co.za/ 
 *  email: info@solarmd.co.za
 *  7 Alternator Ave, Montague Gardens, Cape Town, 7441 South Africa
 *  Phone: 021 555 2181
 *  
 */
package com.myPower24.ibexBgApi;

import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author henry
 */
public class LifecycleListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(LifecycleListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        LOG.info("LifecycleListener: contextInitialized()");
//        JcFactory.initManager();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        LOG.info("LifecycleListener: contextDestroyed()");
//        JcFactory.getManager().destroy();
    }
}