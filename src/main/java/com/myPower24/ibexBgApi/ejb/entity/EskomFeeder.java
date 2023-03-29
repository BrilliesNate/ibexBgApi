/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import java.io.Serializable;
import java.text.ParseException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author natha
 */
@Entity
@Table(name = "eskom_feeder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EskomFeeder.findBySuburbName", query = "SELECT m FROM EskomFeeder m WHERE m.suburbName = :sName"),})

public class EskomFeeder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "provinceName")
    private String provinceName;

    @Column(name = "provinceId")
    private int provinceId;

    @Column(name = "municipalityName")
    private String municipalityName;

    @Column(name = "municipalityId")
    private int municipalityId;

    @Column(name = "totalMunicipalities")
    private int totalMunicipalities;

    @Column(name = "suburbName")
    private String suburbName;

    @Column(name = "suburbId")
    private int suburbId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public int getTotalMunicipalities() {
        return totalMunicipalities;
    }

    public void setTotalMunicipalities(int totalMunicipalities) {
        this.totalMunicipalities = totalMunicipalities;
    }

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public int getSuburbId() {
        return suburbId;
    }

    public void setSuburbId(int suburbId) {
        this.suburbId = suburbId;
    }

    public static EskomFeeder create(String provinceName, int provinceId, String municipalityName, int municipalityId, int totalMunicipalities, String suburbName, int suburbId) throws ParseException {
        EskomFeeder ef = new EskomFeeder();

        ef.provinceName = provinceName;
        ef.provinceId = provinceId;
        ef.municipalityName = municipalityName;
        ef.municipalityId = municipalityId;
        ef.totalMunicipalities = totalMunicipalities;
        ef.suburbName = suburbName;
        ef.suburbId = suburbId;

//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
//        mp.period = sdf.parse(date + " " + time);
//        System.out.println("Parsing Date: " + date + " Result " + this.period);
        return ef;
    }

}
