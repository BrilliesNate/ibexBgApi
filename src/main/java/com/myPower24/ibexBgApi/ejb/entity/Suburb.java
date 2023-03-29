/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author natha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Suburb {

    @JsonAlias(value = "text")
    private String text;

    @JsonAlias(value = "id")
    private String id;

    public Suburb() {
    }

    public String getText() {
        return text;
    }

    public String getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

}
