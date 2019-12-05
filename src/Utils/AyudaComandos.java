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
    List<AyudaEntity> list=new ArrayList<>();

    public AyudaComandos() {        
        list.add(new AyudaEntity("INSERTAR ACTIVIDAD","Nombre: String<br>Codigo: Integer<br>Descripcion: String","Comando para insertar una actividad.","INSERTAR_ACTIVIDAD(Nombre,Codigo,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR PLANO","SolicitudId: Integer<br>Descripcion: String","Comando para insertar un plano.","INSERTAR_PLANO(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR MARCA","SolicitudId: Integer<br>Descripcion: String","Comando para insertar una marca.","INSERTAR_MARCA(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR TITULO","SolicitudId: Integer<br>Descripcion: String","Comando para insertar un titulo.","INSERTAR_TITULO(SolicitudId,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR RUBRO","Nombre: String<br>Codigo: Integer<br>Descripcion: String","Comando para insertar un rubro.","INSERTAR_RUBRO(Nombre,Descripcion)"));
        list.add(new AyudaEntity("INSERTAR DETALLE RUBRO","RubroId: Integer<br>Codigo: Integer<br>Nombre: String","Comando para insertar un detalle rubro.","INSERTAR_DETALLERUBRO(RubroId,Codigo,Nombre)"));
        list.add(new AyudaEntity("INSERTAR CLIENTE","CI: Integer<br>Nombre: String<br>ApellidoPaterno: String<br>ApellidoMaterno: String<br>Telefono: String<br>Email: String","Comando para insertar un cliente.","INSERTAR_CLIENTE(CI,Nombre,ApellidoPat,ApellidoMat,Telefono,Email)"));
        list.add(new AyudaEntity("INSERTAR PERSONAL","CI: Integer<br>Nombre: String<br>ApellidoPaterno: String<br>ApellidoMaterno: String<br>Telefono: String<br>Email: String<br>Nick: String<br>Contrasena: String ","Comando para insertar un personal.","INSERTAR_PERSONAL(CI,Nombre,ApellidoPat,ApellidoMat,Telefono,Email,Nick,Contrasena)"));
        list.add(new AyudaEntity("INSERTAR UBICACION","UbicacionId: Integer<br>Nombre: String<br>Tipo: Enum (Departamento,Provincia,Municipio,Sitio,Zona,Subzona)","Comando para insertar una ubicacion.","INSERTAR_UBICACION(UbicacionId,Nombre,Tipo)"));
        list.add(new AyudaEntity("INSERTAR SOLICITUD280","ActividadId: Int<br>ClienteId: Integer<br>PersonalId: Integer<br>TituloId: Integer<br>UbicacionId: Integer<br>NroOrden: String<br>Gestion: Integer<br>NroHectareas: Double<br>FechaSolicitud: Date (yyyy-MM-dd)<br>NitDependencia: String<br>NroDocumento: String<br>NroBoletaPago: String","Comando para insertar una solicitud 280.","INSERTAR_SOLICITUD280(ActividadId,ClienteId,PersonalId,TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,FechaSolicitud)"));
        list.add(new AyudaEntity("INSERTAR SOLICITUD701","ActividadId: Int<br>ClienteId: Integer<br>PersonalId: Integer<br>TituloId: Integer<br>UbicacionId: Integer<br>NroOrden: String<br>Gestion: Integer<br>NroHectareas: Double<br>FechaSolicitud: Date (yyyy-MM-dd)<br>DDjjOriginal: Integer<br>Folio: Integer<br>NroTituloPropiedad: String","Comando para insertar una solicitud 701.","INSERTAR_SOLICITUD701(ActividadId,ClienteId,PersonalId,TituloId,UbicacionId,NroOrden,Gestion,NroHectareas,FechaSolicitud,ddjjOriginal,Folio,NroTituloPropiedad)"));
        list.add(new AyudaEntity("INSERTAR CLIENTE SOLICITUD701","DetalleRubroId: Integer<br>Solicitud701Id: Integer<br>Valor: Double","Comando para insertar un detalle entre cliente y solicitud701.","INSERTAR_CLIENTESOLICITUD701(DetalleRubroId,Solicitud701Id,Valor)"));
    }
    
    public DefaultTableModel toTable(){
        DefaultTableModel model=new DefaultTableModel(new Object[]{"OPERACION","PARAMETROS","DESCRIPCION","COMANDO"}, list.size());
        model.setRowCount(0);
        list.forEach((entity) -> {
            model.addRow(new Object[]{
                entity.getOperacion(),
                entity.getParametros(),
                entity.getDescripcion(),
                entity.getComando()
            });
        });
        return model;
    }
}
