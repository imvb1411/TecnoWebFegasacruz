/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mijael
 */
public class ClientPOP {

    //<editor-fold desc="Attributes" defaultstate="collapsed">
    private Socket socket;
    private final String SERVER;
    private final String user = "grupo18sc";
    private final String pass = "grupo18grupo18";
    private BufferedReader in;
    private DataOutputStream out;
    private final int PORT = 110;
    private static ClientPOP instance = null;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public ClientPOP() {
        SERVER = "mail.ficct.uagrm.edu.bo";
    }
    //</editor-fold>

    public static ClientPOP getInstance() {
        if (ClientPOP.instance == null) {
            instance = new ClientPOP();
        }
        return ClientPOP.instance;
    }

    public boolean connect() {
        boolean sw = false;
        try {
            socket = new Socket(SERVER, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            String comando = "USER " + user + "\r\n";
            out.writeBytes(comando);
            comando = "PASS " + pass + "\r\n";
            out.writeBytes(comando);
            sw = true;
        } catch (IOException ex) {
            System.out.println("Client.connectPOP: Error= " + ex.getMessage());
            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    public boolean disconnect() {
        boolean sw = false;
        try {
            String comando = "QUIT\r\n";
            //System.out.print("C: " + comando);
            out.writeBytes(comando);
            //System.out.println("S: " + in.readLine() + "\r\n");
            out.close();
            in.close();
            socket.close();
            sw = true;
        } catch (IOException ex) {
            System.out.println("ClientPOP.disconnect:Error =" + ex.getMessage());
            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    public String getMessagesList() {
        String listMessages = "";
        try {

            String comando = "LIST \r\n";
            out.writeBytes(comando);
            listMessages = getMultiline(in);
            int indexBeginContent = listMessages.indexOf(":");
            listMessages = listMessages.substring(indexBeginContent + 2, listMessages.length());

        } catch (IOException ex) {
            System.out.println("ClientPOP.getMessagesList:Error= " + ex.getMessage());

            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMessages;
    }

    public String getMessage(int index) {
        String message = "";
        try {

            String comando = "RETR " + index + "\r\n";
            //System.out.print("C: " + comando);
            out.writeBytes(comando);
            message = getMultiline(in);
            //System.out.println("S: " +message + "\r\n");

        } catch (IOException ex) {
            System.out.println("ClientPOP.getMessage:Error= " + ex.getMessage());

            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    public List<MailMessage> getIndexMessagesList() {
        List<MailMessage> list = new ArrayList<>();
 
            String messagesList = getMessagesList();
            if (messagesList.length() > 1) {
                int indexAux = messagesList.indexOf("\r");
                messagesList = messagesList.substring(0, indexAux);
                String[] messages = messagesList.split("\n");
                for (String message : messages) {
                    list.add(MailMessage.format(message));
                }
            }

        return list;
    }

    public void deleteMessage(int index) {       
        try {

            String comando = "DELE " + index + "\r\n";
            //System.out.print("C: " + comando);
            out.writeBytes(comando);
            //message = getMultiline(in);
            //System.out.println("S: " +message + "\r\n");

        } catch (IOException ex) {
            System.out.println("ClientPOP.deleteMessage:Error= " + ex.getMessage());

            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //<editor-fold desc="Auxiliar Methods" defaultstate="collapsed">
    private String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                return lines;
            }
            if (line.equals(".")) {
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                line = line.substring(1);
            }
            lines = lines + "\n" + line;
        }
        return lines + "\r\n";
    }

    public String getFrom(String message){
        int indexFrom = message.toUpperCase().indexOf("FROM: ");
        String subSubject = message.substring(indexFrom + 5);
        int indexBegin=subSubject.indexOf("<");
        int indexEnd=subSubject.indexOf(">");
        String from=subSubject.substring(indexBegin+1,indexEnd);
        return from.trim();
    }
    
    public String getCommand(String message) {
        int indexSubject = message.toUpperCase().indexOf("SUBJECT: ");
        String SubSubject = message.substring(indexSubject + 8);
        String aux = "";
        for (int i = 0; i < SubSubject.length() - 10; i++) {
            if (SubSubject.charAt(i) == ')') {
                aux = SubSubject.substring(0, i+1);
                break;
            }
        }
        aux=aux.replace("\n", "");
        System.out.println("Aux:"+aux);
        return aux;
    }

    public String getContent(String message) {
        int indexSubject = message.toUpperCase().indexOf("SUBJECT:");
        String SubSubject = message.substring(indexSubject);
        int saltoLinea = SubSubject.indexOf("\n");
        String result = SubSubject.substring(saltoLinea + 1, SubSubject.length());
        return result;
    }
    //</editor-fold>

}
