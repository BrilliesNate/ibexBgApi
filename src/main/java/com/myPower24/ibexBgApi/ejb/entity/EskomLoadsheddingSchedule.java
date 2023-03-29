/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ejb.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author natha
 */
public class EskomLoadsheddingSchedule {
     private String stage;
    private ArrayList<ScheduleDay> scheduleList;

    public EskomLoadsheddingSchedule(String stage, String link) {
        this.stage = stage;
        String webPage = link;
        try {
            Document doc = Jsoup.connect(webPage).get();
            this.scheduleList = buildScheduleList(doc);
        } catch (IOException ex) {
            Logger.getLogger(EskomLoadsheddingSchedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getStage() {
        return stage;
    }
    
    public ArrayList<ScheduleDay> getScheduleList() {
        return scheduleList;
    }
    
    private ArrayList buildScheduleList(Document doc) {
        ArrayList<ScheduleDay> schedule = new ArrayList();
        Elements elements = doc.select("*");
        
        ScheduleDay day = new ScheduleDay("Empty Day");
        
        for(Element e : elements) {
            String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            
            if (Arrays.stream(days).anyMatch(e.text()::contains) && e.text().length() == 11) {
               if (!day.getDay().equals("Empty Day")) {
                   schedule.add(day);
               }
               day = new ScheduleDay(e.text());
            }
            
            else if (e.text().contains(":") && e.text().contains("-") && e.text().length() == 13){
                if (!day.getTimes().contains(e.text())) {
                    day.addTime(e.text());
                }
            }
        }
        return schedule;
    }
    
    public String toJSON() {
        
        String json = "\"" + stage + "\":";
        
        for (ScheduleDay day : scheduleList) {
            if (scheduleList.indexOf(day) == 0) {
                json += "[" + day.toJSON() + ",";
            }
            else if (scheduleList.indexOf(day) == scheduleList.size() - 1) {
                json += day.toJSON() + "]";
            }
            else {
                json += day.toJSON() + ",";
            }
        }
         
        return json;
    }
 
}
