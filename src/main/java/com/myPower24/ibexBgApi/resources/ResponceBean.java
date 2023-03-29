/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.resources;

import javax.ws.rs.core.Response;

/**
 *
 * @author natha
 */
public class ResponceBean {

    private Object data;
    private Integer statusCode;
    private String message;
    private int errorCode;
    private boolean success = true;
//    private HashMap<String, String> statusCode = new HashMap<String, String>();

    public ResponceBean() {
        System.out.println("I am responding");

    }

    public ResponceBean(Object data) {
        this.data = data;
        this.statusCode = Response.Status.OK.getStatusCode();
        this.message = "Great Success";
    }

    public ResponceBean(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getErrorCode() {
        
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

}
