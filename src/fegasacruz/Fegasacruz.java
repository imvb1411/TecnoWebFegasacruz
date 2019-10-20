/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import Models.Socket.ClientPOP;
import Models.Socket.ClientSMTP;
import Models.Socket.MailMessage;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mijael
 */
public class Fegasacruz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientPOP clientPOP = new ClientPOP();
        ClientSMTP clientSMTP;
        System.out.println("Esperando");
        while (true) {
            if (clientPOP.connect()) {
                List<MailMessage> list = clientPOP.getIndexMessagesList();
                while (list.size() > 0) {
                    MailMessage mail = list.get(0);
                    String message = clientPOP.getMessage(mail.getIndex());
                    String asunto = clientPOP.getCommand(message).trim();
                    Commands commandModel = new Commands(asunto);
                    System.out.println("Asunto: "+asunto);
                    Map<String,Object> result = commandModel.executeCommand();
                    Object r=result.get("result");
                    int inserted;
                    List<Object> listR;
                    String from=clientPOP.getFrom(message);
                    clientSMTP=new ClientSMTP(from);
                    if(r instanceof Integer){
                        inserted=Integer.valueOf(r.toString());
                        if(inserted>0){
                        clientSMTP.sendHTMLMessage("Respuesta al comando " +asunto, ": Se ejecutó correctamente.");
                    }else{
                        clientSMTP.sendMessage("Respuesta al comando " +asunto, ": Error al ejecutar el comando.");
                    }
                    }else if(r instanceof List){
                        listR=(List)r;
                        if(listR.size()>0){
                            for(Object o:listR){
                                System.out.println("Object: "+(o).toString());
                            }
                            String html=clientSMTP.writeRptHtml(listR);
                            System.out.println("Correo------------------------------------------------------:");
                            System.out.println(html);
                            clientSMTP.sendMessage("Respuesta al comando " +asunto, html);
                        }else{
                            clientSMTP.sendMessage("Respuesta al comando " +asunto, ": No existen datos para listar.");
                        }
                    }
                    list.remove(0);
                    clientPOP.deleteMessage(mail.getIndex());
                }
                clientPOP.disconnect();
            }
        }

    }
    
}
