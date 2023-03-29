///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.myPower24.ibexBgApi.ejb.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.NamedQueries;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.xml.bind.annotation.XmlRootElement;
//import org.eclipse.persistence.jpa.jpql.parser.DateTime;
//
///**
// *
// * @author natha
// */
//@Entity
//@Table(name = "eskom_schedule")
//@XmlRootElement
//@NamedQueries({})
//public class EskomSchedule implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "stage")
//    private int stage;
//    
//    @Column(name = "startTime")
//    
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date startTime;
//
//    @Column(name = "endTime")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date endTime;
//
//    @Column(name = "feeder")
//    private String feeder;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public int getStage() {
//        return stage;
//    }
//
//    public void setStage(int stage) {
//        this.stage = stage;
//    }
//
//    public Date getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Date startTime) {
//        
//        this.startTime = startTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }
//
//    public String getFeeder() {
//        return feeder;
//    }
//
//    public void setFeeder(String feeder) {
//        this.feeder = feeder;
//    }
//
//}
