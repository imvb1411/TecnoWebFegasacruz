/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mijael
 */
public class ClientSMTP {

    //<editor-fold desc="Attributes" defaultstate="collapsed">
    private Socket socket;
    private final String SERVER;
    private final String RECEIVING_USER;
    private final String ISSUER_USER;
    private BufferedReader in;
    private DataOutputStream out;
    private final int PORT = 25;
    private static ClientSMTP instance = null;
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public ClientSMTP() {
        SERVER = "mail.ficct.uagrm.edu.bo";
        RECEIVING_USER = "grupo06sc@mail.ficct.uagrm.edu.bo";
        ISSUER_USER = "grupo06sc@mail.ficct.uagrm.edu.bo";
    }
    
    public ClientSMTP(String receivingUser) {
        //SERVER="smtp.gmail.com";
        //ISSUER_USER ="imvb14@gmail.com";
        SERVER = "mail.ficct.uagrm.edu.bo";
        RECEIVING_USER = receivingUser;
        ISSUER_USER = "grupo06sc@mail.ficct.uagrm.edu.bo";
        
    }
    //</editor-fold>

    public static ClientSMTP getInstance() {
        if (ClientSMTP.instance == null) {
            instance = new ClientSMTP();
        }
        return ClientSMTP.instance;
    }

    public boolean connect() {
        boolean sw = false;
        try {
            socket = new Socket(SERVER, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            sw = true;
            System.out.println("S: " + in.readLine());
        } catch (IOException ex) {
            System.out.println("Client.connectSMTP: Error= " + ex.getMessage());
            Logger.getLogger(ClientSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    public boolean disconnect(){
        boolean sw=false;
        try {
            String comando = "QUIT\r\n";            
            System.out.print("C: "+comando);
            out.writeBytes(comando);
            System.out.println("S: "+ in.readLine()+"\r\n");
            out.close();
            in.close();
            socket.close();
            sw=true;
        } catch (IOException ex) {
            System.out.println("ClientPOP.disconnect:Error =" + ex.getMessage());
            Logger.getLogger(ClientPOP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }
    public String getCommand(String message) {
        int indexSubject = message.toUpperCase().indexOf("SUBJECT: ");
        String SubSubject = message.substring(indexSubject + 9);
        String aux = "";
        for (int i = 0; i < SubSubject.length() - 10; i++) {
            if (SubSubject.charAt(i) == '\n') {
                aux = SubSubject.substring(0, i);
                break;
            }
        }
        return aux;
    }

    public boolean sendMessage(String subject, String data) {
        boolean sw = false;
        try {
            connect();
            TimeUnit.SECONDS.sleep(1);
            String comando = "HELO " + SERVER + "\r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "MAIL FROM : " + ISSUER_USER + " \r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "RCPT TO : " + RECEIVING_USER + " \r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "DATA \r\n ";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "Subject:"+subject+" \r\n";
                comando = comando + data+" \r\n";
                comando = comando + ".\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());
            sw = true;
            disconnect();
        } catch (IOException ex) {
            disconnect();
            Logger.getLogger(ClientSMTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }

    public boolean sendHTMLMessage(String subject, String data) {
        boolean sw = false;
        try {
            connect();
            TimeUnit.SECONDS.sleep(1);
            String comando = "HELO " + SERVER + "\r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "MAIL FROM : " + ISSUER_USER + " \r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "RCPT TO : " + RECEIVING_USER + " \r\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "DATA \r\n ";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());

                comando = "From: _"+ISSUER_USER+"\nTo: "+RECEIVING_USER+"ContentType: text/html;\nSubject: Hola\n<html><b>Prueba correo</b></html> \r\n";
                comando = comando + data+" \r\n";
                comando = comando + ".\n";
                System.out.print("C: " + comando + "\r\n");
                out.writeBytes(comando);
                System.out.println("S: " + in.readLine());
            sw = true;
            disconnect();
        } catch (IOException ex) {
            disconnect();
            Logger.getLogger(ClientSMTP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientSMTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw;
    }
    
    public String writeRptHtml(List<Object> listR) {
//        S="\nTo: "+asdf[2]+"\nContent-Type:
//text/html;\nSubject: test\n<HTML><B>This is in BOLD tag</B><BR><I>This is
//in Italic tag</I><BR><U>This is in UnderLine Tag</U></HTML>\n.\n");
        String body="\nContent-Type:text/html;\nSubject:<body><table>";
        String head="<tr><th>Nro.</th><th>Descripcion</th></tr>";
        body+=head;
        String row;
        int index=1;
        for(Object o:listR){
            row="<tr>"+"<td>"+index+"</td>"+"<td>"+o.toString()+"</td></tr>";
            index++;
            body+=row;
        }
        body+="</table></body>";
        return body;
    }
}
