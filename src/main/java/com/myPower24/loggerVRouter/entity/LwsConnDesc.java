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
public class LwsConnDesc implements Serializable {

    private String appId;
    private Integer conCount;
    private Double cpuUsage;
    private Double memUsage;
    private Double netRxTraffic;
    private Double netTxTraffic;
    private String ipAddress;
    private String port;
    private String connectionTypes;
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getConCount() {
        return conCount;
    }

    public void setConCount(Integer conCount) {
        this.conCount = conCount;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getConnectionTypes() {
        return connectionTypes;
    }

    public void setConnectionTypes(String connectionTypes) {
        this.connectionTypes = connectionTypes;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(Double memUsage) {
        this.memUsage = memUsage;
    }

    public Double getNetRxTraffic() {
        return netRxTraffic;
    }

    public void setNetRxTraffic(Double netRxTraffic) {
        this.netRxTraffic = netRxTraffic;
    }

    public Double getNetTxTraffic() {
        return netTxTraffic;
    }

    public void setNetTxTraffic(Double netTxTraffic) {
        this.netTxTraffic = netTxTraffic;
    }
    
    

}
