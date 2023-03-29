/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author natha
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EskomSuburbResp implements Serializable {
    
    

    public EskomSuburbResp() {
    }

    @JsonAlias(value = "Results")
    private List<Suburb> results;

    public List<Suburb> getResults() {
        return results;
    }

}
