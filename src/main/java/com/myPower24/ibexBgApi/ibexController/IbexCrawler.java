/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.ibexController;

import com.myPower24.ibexBgApi.ejb.entity.EskomFeeder;
import com.myPower24.ibexBgApi.ejb.entity.EskomLoadsheddingSchedule;
import com.myPower24.ibexBgApi.ejb.entity.MarketPrice;
import com.myPower24.ibexBgApi.ejb.entity.ScheduleDay;
import com.myPower24.ibexBgApi.ejb.entity.Suburb;
import com.myPower24.ibexBgApi.email.SendEmail;
import com.myPower24.ibexBgApi.eskomController.EskomLoadSheddingController;
import com.myPower24.ibexBgApi.eskomController.EskomFeederDOA;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author natha
 */
@Singleton
public class IbexCrawler {

    private int unsuccessCon = 0;
    private Long successTimeStamp;
    private boolean isinit = false;

    @EJB
    MarketPriceDAO mpDao;

    @EJB
    EskomFeederDOA efDao;
    
    

    @Schedule(hour = "*", minute = "*", second = "*/2", persistent = false)
    public void onUpdate() {

        try {
            TreeSet<MarketPrice> ibexSchedual = getIbexSchedual();

            List<MarketPrice> dbRecordsInPeriod = mpDao.findPeriod(ibexSchedual.first().getPeriod(), ibexSchedual.last().getPeriod());

            compareRecords(dbRecordsInPeriod, ibexSchedual);
            unsuccessCon = 0;
        } catch (IOException | RuntimeException ex) {
            System.out.println("Fail fetch unsuccess count: " + unsuccessCon);
            unsuccessCon++;
            if (unsuccessCon == 360) {
                SendEmail se = new SendEmail();
                se.setMessage("offline");
                try {
                    se.sendMail();
                    System.out.println("Email Sent");

                } catch (NullPointerException e) {

                    System.out.println("Failed to send email");
                }
            }

        }
//        if (!isinit) {
//            setIsinit(true);
////            uploadFeederDate();
////            System.out.println("Getting Feeder");
//            String area = "Table View";
//            getLoadscheddingSchedule(area);
//        }

    }

    public void getLoadscheddingSchedule(String area) {
//        EskomFeederDOA efd = new EskomFeederDOA();
        // 
        EskomFeeder feeder = efDao.findBySuburbName(area);
//        System.out.println(feeder.getSuburbId());
        
        
        EskomLoadSheddingController elsc = new EskomLoadSheddingController();
        int stage = 3;
        try {
            elsc.convertScheduleToJson(feeder.getSuburbId(), stage, feeder.getProvinceId(), feeder.getTotalMunicipalities());
        } catch (IOException ex) {
            System.out.println("this failed, you need to try again");
            Logger.getLogger(IbexCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIsinit(boolean isinit) {
        this.isinit = isinit;
    }

    private void uploadFeederDate() {

        EskomLoadSheddingController ec = new EskomLoadSheddingController();
        try {
            int subIDTemp = 244;
            List<Suburb> suburbList = ec.getSuburb(subIDTemp);

            for (int i = 0; i < suburbList.size(); i++) {
                try {
                    int subId = Integer.parseInt(suburbList.get(i).getId());
                    EskomFeeder feeder = EskomFeeder.create("Limpopo", 5, "Greater Tubatse", subIDTemp, 40, suburbList.get(i).getText(), subId);
                    efDao.create(feeder);
                } catch (ParseException ex) {
                    Logger.getLogger(IbexCrawler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("Added all suburbs to db");
        } catch (IOException ex) {
            Logger.getLogger(IbexCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void onFetchFaile() {

        successTimeStamp = System.currentTimeMillis();

    }

//    public static void main(String args[]) {
//        IbexCrawler ibx = new IbexCrawler();
//        ibx.onUpdate();
//    }
    private void compareRecords(List<MarketPrice> dbRecordsInPeriod, TreeSet<MarketPrice> ibexSchedual) {
        for (MarketPrice ibexPer : ibexSchedual) {
            int idx = dbRecordsInPeriod.indexOf(ibexPer);

            if (idx == -1) {
                mpDao.create(ibexPer);
                System.out.println("Creating value: " + ibexPer);
            } else {
                MarketPrice dbRec = dbRecordsInPeriod.get(idx);
                if (!Objects.equals(dbRec.getVolume(), ibexPer.getVolume())
                        || !Objects.equals(dbRec.getPriceEuro(), ibexPer.getPriceEuro())) {

                    dbRec.setPriceEuro(ibexPer.getPriceEuro());
                    dbRec.setVolume(ibexPer.getVolume());
                    mpDao.edit(dbRec);
                }
            }

        }
    }

    private TreeSet<MarketPrice> getIbexSchedual() throws IOException {

        String webPage = "https://ibex.bg/markets/dam/day-ahead-prices-and-volumes-v2-0-2/";
//        String webPage = "https://i.bkets/dam/day-ahead-prices-and-volumes-v2-0-2/";

        Document doc = Jsoup.connect(webPage).get();

        Element el = doc.getElementById("dam-php-table");
        Element tableBody = el.getElementsByTag("tbody").first();
        Elements trIter = tableBody.getElementsByTag("tr");

        TreeSet<MarketPrice> set = new TreeSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (Iterator<Element> iterator = trIter.iterator(); iterator.hasNext();) {
            try {
                Element row = iterator.next();
                String date = row.getElementsByClass("column-date_part").html();
                String time = row.getElementsByClass("column-time_part").html();
                String price = row.getElementsByClass("column-price_eur").html();
                String volume = row.getElementsByClass("column-volume").html();

                Date d = sdf.parse(date + " " + time);
                MarketPrice unit = MarketPrice.create(d, price, volume);
                if (!set.contains(unit)) {
                    set.add(unit);
                } else {
                    System.out.println("look here!!!!!!!!!!!!!!");
                }
            } catch (ParseException ex) {
                Logger.getLogger(IbexCrawler.class
                        .getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ibex cant be reched");
            }
        }
        return set;
    }
}
