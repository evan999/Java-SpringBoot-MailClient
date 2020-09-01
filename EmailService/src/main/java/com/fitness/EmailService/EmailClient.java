package com.fitness.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailClient {
    private String host = "";
    private int port = 0;
    private String username = "";
    private String password = "";
    public final String senderMailId = "emeshberge@gmail.com";

    public EmailClient(String host, int port){
        this.host = host;
        this.port = port;

    }

    public void sendMail() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(prop, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMailId));
            //message.setRecipients(Message.RecipientType.TO, InternetAddress.parse())
            message.setSubject("Welcome");
            message.setText("Welcome to FitHub!");

            Transport.send(message);
            System.out.println("Email sent out successfully");
        }
        catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }


}
