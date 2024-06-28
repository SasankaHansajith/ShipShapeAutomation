package com.shipshape.notifications;

import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;

public class NotificationService {
//    public static void sendEmail(String recipient, String subject, String content) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.example.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        // Correct the Authenticator import and usage
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("your-email@example.com", "your-password");
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("your-email@example.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//            message.setText(content);
//
//            Transport.send(message);
//            System.out.println("Email sent successfully!");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}

/*
* you have rework your code for sending emails. I'd rather suggest you to utilize Google GMail API in this case,
* since it is extremely easy to implement and configure.
*/