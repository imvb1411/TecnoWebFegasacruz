/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import Models.Socket.ClientPOP;
import Models.Socket.Mail;
import Models.Socket.MailMessage;
import Utils.Utils;
import Utils.ValidatorCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.table.DefaultTableModel;

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
        Mail mailManager = new Mail();
        System.out.println("Esperando");
        while (true) {
            if (clientPOP.connect()) {
                List<MailMessage> list = clientPOP.getIndexMessagesList();
                while (list.size() > 0) {
                    MailMessage mail = list.get(0);
                    String message = clientPOP.getMessage(mail.getIndex());
                    String asunto = clientPOP.getCommand(message).trim();
                    CommandManager commandManager = new CommandManager(asunto);
                    System.out.println("Asunto: " + asunto);
                    String from = clientPOP.getFrom(message);
                    commandManager.executeCommand(new ValidatorCommand() {
                        @Override
                        public void onSuccess() {
                            try {
                                String html = "";
                                switch (commandManager.getTipoComando()) {
                                    case Insercion:
                                        int idInserted = (int) commandManager.getResult().get("result");
                                        html = "EJECUTADO CORRECTAMENTE: LLAVE GENERADA=" + idInserted;
                                        break;
                                    case Reporte:
                                        html = Utils.dibujarTablawithHTML((DefaultTableModel) commandManager.getResult().get("result"));
                                        break;
                                    case Estadistica:
                                        html = Utils.dibujarTablawithHTML2((DefaultTableModel) commandManager.getResult().get("result"));
                                        break;
                                }
                                System.out.println(html);
                                mailManager.sendHtmlEmail(from, "INFORME DE EJECUCION DEL COMANDO " + commandManager.getCommand(), html);
                            } catch (MessagingException | IOException ex) {
                                Logger.getLogger(Fegasacruz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        @Override
                        public void onError() {
                            try {
                                String html = "Error al ejecutar el comando";
                                mailManager.sendHtmlEmail(from, "INFORME DE EJECUCION DEL COMANDO " + commandManager.getCommand(), html);
                            } catch (MessagingException | IOException ex) {
                                Logger.getLogger(Fegasacruz.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

                    //<editor-fold desc="Version Anterior" defaultstate="collapsed">
//                    Object r = result.get("result");
//                    int inserted;
//                    String from = clientPOP.getFrom(message);
//                    clientSMTP = new ClientSMTP(from);
//                    if (r instanceof Integer) {
//                        inserted = Integer.valueOf(r.toString());
//                        if (inserted > 0) {
//                            String respuestaHtml = Utils.mailResponse(Utils.TIPO_RESPUESTA.Success, commandModel.getCommand(), inserted);
//                            mail2.sendHtmlEmail(from, "Respuesta al comando " + commandModel.getCommand(), respuestaHtml);
//                        } else {
//                            String respuestaHtml = Utils.mailResponse(Utils.TIPO_RESPUESTA.Error, commandModel.getCommand(), inserted);
//                            mail2.sendHtmlEmail(from, "Respuesta al comando " + commandModel.getCommand(), respuestaHtml);
//                        }
//                    } else if (r instanceof DefaultTableModel) {
//                        String html = "";
//                        if (commandModel.getCommand().contains("RPT")) {
//                            html = Utils.dibujarTablawithHTML((DefaultTableModel) r);
//                            System.out.println("Correo------------------------------------------------------:");
//                            System.out.println(html);
//
//                        } else if (commandModel.getCommand().contains("EST")) {
//                            html = Utils.dibujarTablawithHTML2((DefaultTableModel) r);
//                            System.out.println("Correo------------------------------------------------------:");
//                            System.out.println(html);
//                        } else if (commandModel.getCommand().contains("AYUDA")) {
//                            html = "";
//                        }
//                        mail2.sendHtmlEmail(from, "Respuesta al comando " + commandModel.getCommand(), html);
//                    }
//</editor-fold>
                    list.remove(0);
                    clientPOP.deleteMessage(mail.getIndex());
                }
                clientPOP.disconnect();
            }
        }
    }
}
