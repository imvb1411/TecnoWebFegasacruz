/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mijael
 */
public class Utils {

    public static enum TIPO_RESPUESTA {
        Success, Error
    }

    public static DefaultTableModel toTable(List list) {
        DefaultTableModel model = new DefaultTableModel();
        return model;
    }

    public static String dibujarTablawithHTML(DefaultTableModel tabla) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<style>\n"
                + ".button {\n"
                + "    background-color: #ADA6A4; /* YA NO GREEN */\n"
                + "    border: none;\n"
                + "    color: white;\n"
                + "    padding: 15px 32px;\n"
                + "    text-align: center;\n"
                + "    text-decoration: none;\n"
                + "    display: inline-block;\n"
                + "    font-size: 16px;\n"
                + "    margin: 4px 2px;\n"
                + "    cursor: pointer;\n"
                + "}\n"
                + "\n"
                + ".button2 {background-color: #008CBA;} /* Blue */\n"
                + ".button3 {background-color: #f44336;} /* Red */ \n"
                + ".button4 {background-color: #e7e7e7; color: black;} /* Gray */ \n"
                + ".button5 {background-color: #555555;} /* Black */"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #ADA6A4;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
        tableString += "</tr> \n"
                + "</thead> \n";
        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tabla.getValueAt(i, j)))
                        + "</td> \n";
            }
            tableString += "</tr> \n";
        }
        if (tabla.getRowCount() < 1) {
            return "(Tabla Vacia)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";
        return tableString;
    }

    public static String dibujarTablawithHTML2(DefaultTableModel tabla) {
        String tableString = "";
        tableString += "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n"
                + "<script type=\"text/javascript\">\n"
                + "google.charts.load('current', {packages: ['corechart']});\n"
                + "google.charts.setOnLoadCallback(drawChart);\n"
                + "function drawChart() {\n"
                + "var data = new google.visualization.DataTable();\n"
                + "data.addColumn('string', 'Element');\n"
                + "data.addColumn('number', 'Percentage');\n"
                +"data.addRows([\n";
                for (int i = 0; i < tabla.getRowCount(); i++) {
                tableString += "[" + (tabla.getValueAt(i, 0)) + "," + (tabla.getValueAt(i, 1)) + "],";
                }
                tableString +="]);"
                + "var chart = new google.visualization.PieChart(document.getElementById('myPieChart'));\n"
                + "chart.draw(data, null);\n"
                + "}\n"
                + "</script>\n"
                + "<style>\n"
                + ".button {\n"
                + "    background-color: #4CAF50; /* Green */\n"
                + "    border: none;\n"
                + "    color: white;\n"
                + "    padding: 15px 32px;\n"
                + "    text-align: center;\n"
                + "    text-decoration: none;\n"
                + "    display: inline-block;\n"
                + "    font-size: 16px;\n"
                + "    margin: 4px 2px;\n"
                + "    cursor: pointer;\n"
                + "}\n"
                + "\n"
                + ".button2 {background-color: #008CBA;} /* Blue */\n"
                + ".button3 {background-color: #f44336;} /* Red */ \n"
                + ".button4 {background-color: #e7e7e7; color: black;} /* Gray */ \n"
                + ".button5 {background-color: #555555;} /* Black */"
                + "table {\n"
                + "    border-collapse: collapse;\n"
                + "    width: 100%;\n"
                + "}\n"
                + "\n"
                + "th, td {\n"
                + "    text-align: left;\n"
                + "    padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even){background-color: #f2f2f2}\n"
                + "\n"
                + "th {\n"
                + "    background-color: #4CAF50;\n"
                + "    color: white;\n"
                + "}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<div id=\"myPieChart\"/>\n"       
                + "<div class=\"w3-container\">\n"
                + "\n"
                + "  <table class=\"w3-table-all\">\n"
                + "    <thead>\n"
                + "<tr class=\"w3-red\">\n";
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tableString += "<th>" + (tabla.getColumnName(i)) + "</th> \n";
        }
        tableString += "<th> Opciones </th> \n";
        tableString += "</tr> \n"
                + "</thead> \n";

        for (int i = 0; i < tabla.getRowCount(); i++) {
            tableString += "<tr> \n";
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                tableString += "<td>"
                        + (String.valueOf(tabla.getValueAt(i, j)))
                        + "</td> \n";
            }
            //<a href=\"mailto:" + Constants.MAIL_USERMAIL + "?subject=ELIMINAR\"><button class=\"button button3\">ELIMINAR</button></a>
            tableString += "<td><a href=\"mailto:" + Constantes.MAIL_USERMAIL + "?subject=MODIFICAR\"> <button class=\"button button5\">MODIFICAR</button>  "
                    + "<a href=\"mailto:" + Constantes.MAIL_USERMAIL + "?subject=ELIMINAR\"> <button class=\"button button3\">ELIMINAR</button> </td> \n";

            tableString += "</tr> \n";

        }

        if (tabla.getRowCount() < 1) {
            return "(Tabla Vacia)";
        }
        tableString += "</table>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html> ";

        return tableString;
    }
    
    public static String mailResponse(TIPO_RESPUESTA tipo, String comando, int identity) {
        String html = "";
        try {
            String classAlert, tittle, content;
            if (tipo.equals(TIPO_RESPUESTA.Success)) {
                classAlert = "card text-white bg-success mb-3";
                tittle = "Registro exitoso";
                content = "Se registro con el codigo " + identity;
            } else {
                classAlert = "card text-white bg-danger mb-3";
                tittle = "Algo salio mal";
                content = "No se pudo ejecutar el comando";
            }
            String style = new String(Files.readAllBytes(Paths.get("src/Utils/style.txt")));
            html = "<!DOCTYPE html>\n"
                    + "<html>\n"
                    + "<head>\n"
                    + style
                    + "</head>\n"
                    + "<body>\n"
                    + "<div class=\"card text-white bg-success mb-3\" style=\"max-width: 18rem;\">\n"
                    + "  <div class=\"card-header\">hi</div>\n"
                    + "  <div class=\"card-body\">\n"
                    + "    <h5 class=\"card-title\">hi</h5>\n"
                    + "    <p class=\"card-text\">hi.</p>\n"
                    + "  </div>\n"
                    + "</div>\n"
                    + "</body>\n"
                    + "</html>";
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return html;
    }
}
