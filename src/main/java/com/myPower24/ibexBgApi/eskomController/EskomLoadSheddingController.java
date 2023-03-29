/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.eskomController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myPower24.ibexBgApi.ejb.entity.EskomFeeder;
import com.myPower24.ibexBgApi.ejb.entity.EskomLoadsheddingSchedule;
import com.myPower24.ibexBgApi.ejb.entity.EskomSuburbResp;
import com.myPower24.ibexBgApi.ejb.entity.ScheduleDay;
import com.myPower24.ibexBgApi.ejb.entity.Suburb;
import com.myPower24.ibexBgApi.ibexController.MarketPriceDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author natha
 */
public class EskomLoadSheddingController {

    private static int stage;
    private static int provinceId;
    private static int municipalityId;
    private static int suburb;

    // 1 058 862
    // This is a adress youl get from a logger;
    //                       suburn       province
    // Installation Address: Kuils River, Cape Town, South Africa
    // latitude: -33.922729
    // longitude: 18.689804
//    public static void main(String[] args) throws IOException, ParseException {
//
//    }
    private static void getStage() throws IOException {
        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetStatus");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println("Current Stage: " + response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }

    public EskomLoadSheddingController() {
    }

    private static void getMunicipalities() throws IOException {
//		URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetMunicipalities/?Id=5" + provinceId);
        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetMunicipalities/?Id=" + 4);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }

    public static List<Suburb> getSuburb(int subIdTemp) throws IOException {

//        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetSurburbData/?pageSize=100&pageNum=1&id=" + municipalityId);
        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetSurburbData/?pageSize=10000&pageNum=1&id=" + subIdTemp);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == 500) {
            return null;
        }
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            String resp = response.toString();
            EskomSuburbResp sub = new ObjectMapper().readValue(resp, EskomSuburbResp.class);
            List<Suburb> list = sub.getResults();
            return list;

//            JSONParser parser = new JSONParser();
//            try {
//                JSONObject json = (JSONObject) parser.parse(resp);
//                json.;
//                System.out.println("GreatJob");
//            } catch (org.json.simple.parser.ParseException ex) {
//                Logger.getLogger(EskomLoadSheddingController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }
        return null;
    }

    private static void getSchedule() throws IOException {
//        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetScheduleM/$sub/$stageId/$prov/$totalMun");
        URL obj = new URL("https://loadshedding.eskom.co.za/LoadShedding/GetScheduleM/1058862/6/9/47");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request did not work.");
        }

    }

    public static void convertScheduleToJson(int subId, int stage, int provanceId, int totalMunicipalities) throws IOException {
        
        String url = "https://loadshedding.eskom.co.za/LoadShedding/GetScheduleM/"+ subId +"/"+stage+"/"+provanceId+"/"+totalMunicipalities+"";
        
//        EskomLoadsheddingSchedule es = new EskomLoadsheddingSchedule("stage1", "https://loadshedding.eskom.co.za/LoadShedding/GetScheduleM/1058862/1/9/25");
        EskomLoadsheddingSchedule es = new EskomLoadsheddingSchedule("stage6",url);
        printSchedule("Stage 6", new EskomLoadsheddingSchedule("stage6", url).getScheduleList());

    }

    private static void printSchedule(String stage, ArrayList<ScheduleDay> scheduleList) {

        System.out.println(stage);
        System.out.println("___________________");

        for (ScheduleDay day : scheduleList) {
            System.out.println(day.getDay());

            for (Object time : day.getTimes()) {
                System.out.println(time);
            }
        }

        System.out.println("___________________");
    }

}
