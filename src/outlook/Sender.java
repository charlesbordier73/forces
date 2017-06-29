package outlook;

import bl.model.Feedback;

import java.security.GeneralSecurityException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Sender {

    public static Sender instance;
    final String host = "smtp-mail.outlook.com";// host address and protocol : here smtp
    final String user = "polytechpi@outlook.fr";// mail address
    final String password = "Polytech2017";// Password

    final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private Sender(){
    }

    public static Sender getInstance(){
        if (instance == null){
            instance = new Sender();
        }
        return instance;
    }

    public void send(String json, String type) throws GeneralSecurityException {
        // Get system properties
        /*
        Properties properties = new Properties();
        properties.setProperty("mail.store.protocol", "imap");
        properties.setProperty("mail.imap.user", user);
        properties.setProperty("mail.imap.host", host);
        properties.setProperty("mail.imap.port", "993");

        properties.setProperty("mail.imaps.socketFactory.class", SSL_FACTORY);
        properties.setProperty("mail.imaps.socketFactory.fallback", "false");
        properties.setProperty("mail.imaps.socketFactory.port", "993");
        properties.put("mail.imaps.host", "imap-mail.outlook.com");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.imaps.ssl.trust", "*");
        properties.put("mail.imaps.ssl.socketFactory", sf);
        System.out.println("Retrieving emails from " + user +" ...");
        Session session = Session.getDefaultInstance(properties);

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.ssl.trust", "smtp.outlook.com");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });*/

        //Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(props, auth);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(user));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("polytechpi@outlook.fr"));

            // Set Subject: header field
            message.setSubject(type);

            // Now set the actual message
            message.setText(json);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    private class SMTPAuthenticator extends javax.mail.Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            return new PasswordAuthentication(user, password);
        }
    }
}

