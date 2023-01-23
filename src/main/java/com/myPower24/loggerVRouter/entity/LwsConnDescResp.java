/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.loggerVRouter.entity;

import java.io.Serializable;

/**
 *
 * @author natha
 */
public class LwsConnDescResp implements Serializable {

    private final String appId;
    private final String ipAddress;
    private final String port;
    private final String connectionTypes;

    public LwsConnDescResp(LwsConnDesc conDesc) {
        this.appId = conDesc.getAppId();
        this.ipAddress = conDesc.getIpAddress();
        this.port = conDesc.getPort();
        this.connectionTypes = conDesc.getConnectionTypes();
    }

    public String getAppId() {
        return appId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getPort() {
        return port;
    }

    public String getConnectionTypes() {
        return connectionTypes;
    }

}
