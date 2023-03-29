/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author natha
 */
@Entity
@Table(name = "market_price")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MarketPrice.findAll", query = "SELECT m FROM MarketPrice m"),
    @NamedQuery(name = "MarketPrice.findInPeriod", query = "SELECT m FROM MarketPrice m WHERE m.period >= :beginPeriod AND m.period <= :endPeriod ORDER BY m.period "),
    @NamedQuery(name = "MarketPrice.findByTime", query = "SELECT m FROM MarketPrice m WHERE m.period = :time"),
    @NamedQuery(name = "MarketPrice.findByDate", query = "SELECT m FROM MarketPrice m WHERE m.period = :date")

})
public class MarketPrice implements Comparable<MarketPrice>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "period")
    @Temporal(TemporalType.TIMESTAMP)
    private Date period;

    @Column(name = "priceEuro")
    private Double priceEuro;
    
    

    @Column(name = "volume")
    private Double volume;

    public Integer getId() {
        
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Double getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(Double priceEuro) {
        this.priceEuro = priceEuro;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MarketPrice)) {
            return false;
        }
        MarketPrice mp = (MarketPrice) obj;

        if (mp.getPeriod() == null || period == null) {
            return false;
        }

        return mp.getPeriod().equals(period) && mp.getPriceEuro().equals(priceEuro) && mp.getVolume().equals(volume);
//        }

//        return mp.getPeriod().getTime() == period.getTime();
    }

    @Override
    public int compareTo(MarketPrice o) {
        if (o == null || o.getPeriod() == null) {
            return 1;
        }
        if (this.period == null) {
            return -1;
        }
        return o.getPeriod().getTime() > period.getTime() ? -1 : 1;
    }

//    public static MarketPrice create(String date, String time, String priceEur, String volume) throws ParseException {
    public static MarketPrice create(Date date, String priceEur, String volume) throws ParseException {
        MarketPrice mp = new MarketPrice();
        mp.priceEuro = Double.valueOf(priceEur);
        mp.volume = Double.valueOf(volume);

//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

//        mp.period = sdf.parse(date + " " + time);
        mp.period = date;
//        System.out.println("Parsing Date: " + date + " Result " + this.period);
        return mp;
    }

    @Override
    public String toString() {
        return "MarketPrice{" + "id=" + id + ", period=" + period + ", priceEuro=" + priceEuro + ", volume=" + volume + '}';
    }

}
