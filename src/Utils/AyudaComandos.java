/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.AyudaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mijael
 */
public class AyudaComandos {

    List<AyudaEntity> list = new ArrayList<>();

    public AyudaComandos() {
        list.add(new AyudaEntity("INSERTAR ACTIVIDAD", "Nombre: String<br>Codigo: Integer<br>Descripcion: String", "Comando para insertar una actividad.", "INSERTAR_ACTIVIDAD(Nombre,Codigo,Descripcion)", "INSERTAR_ACTIVIDAD(Nombre,Codigo,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR PLANO", "SolicitudId: Integer<br>Descripcion: String", "Comando para insertar un plano.", "INSERTAR_PLANO(SolicitudId,Descripcion)", "INSERTAR_PLANO(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR MARCA", "SolicitudId: Integer<br>Descripcion: String", "Comando para insertar una marca.", "INSERTAR_MARCA(SolicitudId,Descripcion)", "INSERTAR_MARCA(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR TITULO", "SolicitudId: Integer<br>Descripcion: String", "Comando para insertar un titulo.", "INSERTAR_TITULO(SolicitudId,Descripcion)", "INSERTAR_TITULO(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR RUBRO", "Nombre: String<br>Codigo: Integer<br>Descripcion: String", "Comando para insertar un rubro.", "INSERTAR_RUBRO(Nombre,Descripcion)", "INSERTAR_RUBRO(Nombre,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR DETALLE RUBRO", "RubroId: Integer<br>Codigo: Integer<br>Nombre: String", "Comando para insertar un detalle rubro.", "INSERTAR_DETALLERUBRO(RubroId,Codigo,Nombre)", "INSERTAR_DETALLERUBRO(RubroId,Codigo,Nombre)"));
        list.add(new AyudaEntity("INSERTAR CLIENTE", "CI: Integer<br>Nombre: String<br>ApellidoPaterno: String<br>ApellidoMaterno: String<br>Telefono: String<br>Email: String", "Comando para insertar un cliente.", "INSERTAR_CLIENTE(CI,Nombre,ApellidoPat,ApellidoMat,Telefono,Email)", "INSERTAR_CLIENTE(CI,Nombre,ApellidoPat,ApellidoMat,Telefono,Email)"));
        list.add(new AyudaEntity("INSERTAR PERSONAL", "CI: Integer<br>Nombre: String<br>ApellidoPaterno: String<br>ApellidoMaterno: String<br>Telefono: String<br>Email: String<br>Nick: String<br>Contrasena: String ", "Comando para insertar un personal.", "INSERTAR_PERSONAL(CI,Nombre,ApellidoPat,ApellidoMat,<br>Telefono,Email,Nick,Contrasena)", "INSERTAR_PERSONAL(CI,Nombre,ApellidoPat,ApellidoMat,Telefono,Email,Nick,Contrasena)"));
        list.add(new AyudaEntity("INSERTAR UBICACION", "UbicacionId: Integer<br>Nombre: String<br>Tipo: Enum (Departamento,Provincia,<br>Municipio,Sitio,Zona,Subzona)", "Comando para insertar una ubicacion.", "INSERTAR_UBICACION(UbicacionId,Nombre,Tipo)", "INSERTAR_UBICACION(UbicacionId,Nombre,Tipo)"));
        list.add(new AyudaEntity("INSERTAR SOLICITUD280", "ActividadId: Int<br>ClienteId: Integer<br>PersonalId: Integer<br>TituloId: Integer<br>UbicacionId: Integer<br>NroOrden: String<br>Gestion: Integer<br>NroHectareas: Double<br>FechaSolicitud: Date (yyyy-MM-dd)<br>NitDependencia: String<br>NroDocumento: String<br>NroBoletaPago: String", "Comando para insertar una solicitud 280.", "INSERTAR_SOLICITUD280(ActividadId,ClienteId,PersonalId,<br>TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,FechaSolicitud)", "INSERTAR_SOLICITUD280(ActividadId,ClienteId,PersonalId,TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,FechaSolicitud)"));
        list.add(new AyudaEntity("INSERTAR SOLICITUD701", "ActividadId: Int<br>ClienteId: Integer<br>PersonalId: Integer<br>TituloId: Integer<br>UbicacionId: Integer<br>NroOrden: String<br>Gestion: Integer<br>NroHectareas: Double<br>FechaSolicitud: Date (yyyy-MM-dd)<br>DDjjOriginal: Integer<br>Folio: Integer<br>NroTituloPropiedad: String", "Comando para insertar una solicitud 701.", "INSERTAR_SOLICITUD701(ActividadId,ClienteId,PersonalId,<br>TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,<br>FechaSolicitud,ddjjOriginal,Folio,NroTituloPropiedad)", "INSERTAR_SOLICITUD701(ActividadId,ClienteId,PersonalId,TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,FechaSolicitud,ddjjOriginal,Folio,NroTituloPropiedad)"));
        list.add(new AyudaEntity("INSERTAR CLIENTE SOLICITUD701", "DetalleRubroId: Integer<br>Solicitud701Id: Integer<br>Valor: Double", "Comando para insertar un detalle entre cliente y solicitud701.", "INSERTAR_CLIENTESOLICITUD701(DetalleRubroId,Solicitud701Id,Valor)", "INSERTAR_CLIENTESOLICITUD701(DetalleRubroId,Solicitud701Id,Valor)"));
        list.add(new AyudaEntity("REPORTE DE ACTIVIDADES", "Ninguno", "Comando para generar un reporte de actividades.", "RPT_ACTIVIDADES()", "RPT_ACTIVIDADES()"));
        list.add(new AyudaEntity("REPORTE DE RUBROS", "Ninguno", "Comando para generar un reporte de rubros.", "RPT_RUBROS()", "RPT_RUBROS()"));
        list.add(new AyudaEntity("REPORTE DE CLIENTES", "Ninguno", "Comando para generar un reporte de clientes.", "RPT_CLIENTES()", "RPT_CLIENTES()"));
        list.add(new AyudaEntity("REPORTE DE PERSONALES", "Ninguno", "Comando para generar un reporte del personal que trabaja <br>en la empresa.", "RPT_PERSONALES()", "RPT_PERSONALES()"));
        list.add(new AyudaEntity("REPORTE DE SOLICITUDES", "Ninguno", "Comando para generar un reporte de solicitudes.", "RPT_SOLICITUDES()", "RPT_SOLICITUDES()"));
        list.add(new AyudaEntity("REPORTE EST. DE SOLICITUDES POR FECHA", "Desde: Date (yyyy-MM-dd)<br>Hasta: Date (yyyy-MM-dd)", "Comando para generar un grafico estadistico de solicitudes<br>realizadas en un rango de fechas.", "EST_SOLICITUDES_FECHAS(Desde,Hasta)", "EST_SOLICITUDES_FECHAS(Desde,Hasta)"));
        list.add(new AyudaEntity("REPORTE EST. DE TOP DE CLIENTES", "Ninguno", "Comando para generar un grafico estadistico de top<br>de clientes que hicieron alguna solicitud.", "EST_CLIENTES_TOP()", "EST_CLIENTES_TOP()"));
    }

    public DefaultTableModel toTable() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"OPERACION", "PARAMETROS", "DESCRIPCION", "COMANDO", "ENVIAR"}, list.size());
        model.setRowCount(0);
        list.forEach((entity) -> {
            model.addRow(new Object[]{
                entity.getOperacion(),
                entity.getParametros(),
                entity.getDescripcion(),
                entity.getComandoMuestra(),
                "<a href=\"mailto:" + Constantes.MAIL_USERMAIL + "?Subject=" + entity.getComando() + "\">Usar comando</a>"
            });
        });
        return model;
    }
}
