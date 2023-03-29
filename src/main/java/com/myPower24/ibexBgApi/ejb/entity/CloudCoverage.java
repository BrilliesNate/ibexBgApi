/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class CloudCoverage {

    @JsonAlias(value = "time")
    private String hours;
    @JsonAlias(value = "cloudcover")
    private ArrayList<Integer> cloudcover;

    public CloudCoverage() {
    }

    public CloudCoverage(String hours, ArrayList cloudcover) {
        this.hours = hours;
        this.cloudcover = cloudcover;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public ArrayList<Integer> getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(ArrayList<Integer> cloudcover) {
        this.cloudcover = cloudcover;
    }

}
