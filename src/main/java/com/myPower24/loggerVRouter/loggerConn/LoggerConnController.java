/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.loggerVRouter.loggerConn;

import com.hazelcast.map.IMap;
import com.myPower24.loggerVRouter.entity.LvRouterResponse;
import com.myPower24.loggerVRouter.entity.LwsConnDesc;
import com.myPower24.loggerVRouter.entity.LwsConnDescResp;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;
import org.jcluster.core.JcFactory;

/**
 *
 * @author natha
 */
@Singleton
public class LoggerConnController {

    private IMap<String, LwsConnDesc> lwsConnMap;
    private static final Logger LOG = Logger.getLogger(LoggerConnController.class.getName());

    @PostConstruct
    public void init() {

        lwsConnMap = JcFactory.getManager().getDistributedMap("onlineLoggerCountMap");
    }

    public LvRouterResponse getLwsConnInstance() {

        // check map to see connection ammounts per lws
        initDummyData();

        HashMap<String, String> result = new HashMap<>();

        //logic
        LwsConnDesc instance = null;
        for (Map.Entry<String, LwsConnDesc> entry : lwsConnMap.entrySet()) {
            String id = entry.getKey();
            LwsConnDesc connDesc = entry.getValue();

            if (instance == null || connDesc.getConCount() < instance.getConCount()) {
                instance = connDesc;
            }
        }

        LvRouterResponse responce;
        if (instance == null) {
            
//            LvRouterResponse responce = new LvRouterResponse();
            responce = new LvRouterResponse(Response.Status.NO_CONTENT.getStatusCode(), "No instance available to connect to!");
//            HashMap dataResponceMap = responce.getStatusCode();
//           
//            throw new Exception((String) dataResponceMap.get("204"));
        } else {
//            result.put("ipAddress", instance.getIpAddress());
//            result.put("port", instance.getPort());
//            result.put("appId", instance.getAppId());
//            LwsConnDescResp lwsConnDescResp = new LwsConnDescResp(instance);
            responce = new LvRouterResponse(new LwsConnDescResp(instance));
        }
        // connect with lws that has lowest amount of connections
        // if all lws connection amounts are the same then choose any one. 
        return responce;
    }

    //TODO remove this, currently for testing
    private void initDummyData() {
        
        

        //change max for more apps
        for (int i = 1; i <= 3; i++) {
            LwsConnDesc lwsConnDesc = new LwsConnDesc();
            lwsConnDesc.setAppId("app" + i);
            lwsConnDesc.setIpAddress("192.168.100." + (i + 2));
            lwsConnDesc.setPort("3000");
            lwsConnDesc.setCpuUsage(0.3);
            lwsConnDesc.setMemUsage(0.45);
            
            
            
            // need to create list of all lws
            
            
            

            if (i % 2 == 0) {
                lwsConnDesc.setConCount(130 + i);
                lwsConnDesc.setConnectionTypes("ws sock");
                LOG.info("Adding to: " + lwsConnDesc.getAppId() + " count=" + lwsConnDesc.getConCount());
            } else {
                lwsConnDesc.setConCount(135 + i);
                lwsConnDesc.setConnectionTypes("ws");
                LOG.info("Adding to: " + lwsConnDesc.getAppId() + " count=" + lwsConnDesc.getConCount());
            }

            lwsConnMap.put(lwsConnDesc.getAppId(), lwsConnDesc);
        }
    }
}
