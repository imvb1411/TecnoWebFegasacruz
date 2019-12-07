/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Socket;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author fito3
 */
public class Mail {

    public void sendHtmlEmail(String toAddress,
            String subject, String message, String pathChart) throws AddressException,
            MessagingException,
            IOException {

        // sets SMTP server properties
        Properties properties = new Properties();
        //properties.put("mail.smtp.host", "mail.ficct.uagrm.edu.bo");
        //properties.put("mail.smtp.port", 25);
        //properties.put("mail.smtp.auth", "false");
        //properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                //return new PasswordAuthentication("grupo06sc" + "@virtual.fcet.uagrm.edu.bo", "grupo06grupo06");
                //return new PasswordAuthentication("grupo06sc" + "@mail.ficct.uagrm.edu.bo", "grupo06grupo06");
                return new PasswordAuthentication("imvb14" + "@gmail.com", "zulmfescworrkgms");
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        try {
            //<editor-fold desc="Solo html">
            //Message msg = new MimeMessage(session);
            //msg.setFrom(new InternetAddress("grupo06sc" + "@virtual.fcet.uagrm.edu.bo"));
            //msg.setFrom(new InternetAddress("grupo06sc" + "@mail.ficct.uagrm.edu.bo"));
            //msg.setFrom(new InternetAddress("imvb14" + "@gmail.com"));
            //InternetAddress[] toAddresses = {new InternetAddress(toAddress)};           
            //msg.setRecipients(Message.RecipientType.TO, toAddresses);
            //msg.setSubject(subject);
            //msg.setSentDate(new Date());
            //msg.setContent(message, "text/html");
            //</editor-fold>

            MimeMessage mime = new MimeMessage(session);
            mime.setFrom(new InternetAddress("imvb14" + "@gmail.com"));
            // Se rellenan los destinatarios
            mime.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
            // Se rellena el subject
            mime.setSubject(subject);
            // Se mete el texto y la foto adjunta.
            BodyPart texto = new MimeBodyPart();
            texto.setContent(message, "text/html");
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if (!pathChart.isEmpty()) {
                BodyPart adjunto = new MimeBodyPart();
                try {
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(new File(pathChart))));
                    multiParte.addBodyPart(adjunto);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                adjunto.setFileName("Grafico");
            }
            mime.setContent(multiParte);

            System.out.println("Envie MAIL: to=" + toAddress + " subject=" + subject + " data:" + message);
            // sends the e-mail
            Transport.send(mime);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
