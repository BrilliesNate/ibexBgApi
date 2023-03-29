/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPower24.ibexBgApi.email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author natha
 */
public class SendEmail {

//final String username = "nathan.brill@solarmd.co.za";
//        final String password = "Vicky!1994";
    private String emailReciver;
    private String messageInfo;
    private String title;
    private String username;
    private String password;

    public SendEmail( String senderEmail, String password,String emailReciver) {
        this.username = senderEmail;
        this.password = password;
        this.emailReciver = emailReciver;

    }
    
    public SendEmail() {
         
    }

    
   

    public  void setMessage(String messageCode) {
        
        if ("welcome".equals(messageCode)) {
            this.title = "Welcome to IbexBgApi";
            this.messageInfo = "Greetings, and welcome to the ibexBgAPI.\n"
                    + "/n"
                    + "This system is designed to keep you informed of any changes to the ibex schedule and will \n "
                    + "send notifications via email in the event of any unavailability of the ibex system.";
        }

        if ("offline".equals(messageCode)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            this.title = "Ibex is OFFLINE!!!";
            this.messageInfo = "API server went offline at: " + now;
        }
    }

    public void sendMail() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nathan.brill@solarmd.co.za"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailReciver)
            );
           
            message.setSubject(title);
            message.setText(messageInfo);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//
}
