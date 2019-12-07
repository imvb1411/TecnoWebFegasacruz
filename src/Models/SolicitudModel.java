/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ActividadEntity;
import Entities.EstadisticaEntity;
import Entities.PersonaEntity;
import Entities.SolicitudEntity;
import Entities.TituloEntity;
import Entities.UbicacionEntity;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class SolicitudModel extends Model<SolicitudEntity> {

    SolicitudEntity entity;

    public SolicitudModel(SolicitudEntity entity) {
        this.entity = entity;
    }

    public SolicitudModel() {
        entity = new SolicitudEntity();
    }

    public SolicitudEntity getEntity() {
        return entity;
    }

    public void setEntity(SolicitudEntity entity) {
        this.entity = entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String, Object> args = new HashMap<>();
        args.put("actividad_id", entity.getActividadId());
        args.put("cliente_id", entity.getClienteId());
        args.put("registrador_id", entity.getRegistradorId());
        args.put("titulo_id", entity.getTituloId());
        args.put("ubicacion_id", entity.getUbicacionId());
        args.put("tipo_solicitud", entity.getTipoSolicitud());
        args.put("nro_orden", entity.getNroOrden());
        args.put("gestion", entity.getGestion());
        args.put("nro_hectareas", entity.getNroHectareas());
        args.put("fecha_solicitud", new SimpleDateFormat("yyyy-MM-dd").format(entity.getFechaSolicitud()));
        if (entity.getFechaFinalizacion() != null) {
            args.put("fecha_finalizacion", entity.getFechaFinalizacion());
        }
        args.put("estado", entity.getEstado());
        return new Entity("solicitud", args);
    }

    @Override
    public SolicitudEntity loadData(ResultSet rs) throws SQLException {
        entity = null;
        entity = new SolicitudEntity(
                rs.getInt("id"),
                rs.getInt("actividad_id"),
                rs.getInt("cliente_id"),
                rs.getInt("registrador_id"),
                rs.getInt("titulo_id"),
                rs.getInt("ubicacion_id"),
                SolicitudEntity.TIPO.valueOf("Formulario".concat(rs.getString("tipo_solicitud"))),
                rs.getString("nro_orden"),
                rs.getInt("gestion"),
                rs.getDouble("nro_hectareas"),
                rs.getDate("fecha_solicitud"),
                rs.getDate("fecha_finalizacion"),
                rs.getTimestamp("fecha_reg"),
                rs.getTimestamp("fecha_mod"),
                rs.getByte("estado"));
        return entity;
    }

    public ActividadEntity getActividad() {
        entity.setActividad(new ActividadModel().findById(entity.getActividadId()));
        return entity.getActividad();
    }

    public PersonaEntity getPersona() {
        entity.setCliente(new PersonaModel().findById(entity.getClienteId()));
        return entity.getCliente();
    }

    public PersonaEntity getRegistrador() {
        entity.setRegistrador(new PersonaModel().findById(entity.getRegistradorId()));
        return entity.getRegistrador();
    }

    public TituloEntity getTitulo() {
        entity.setTitulo(new TituloModel().findById(entity.getTituloId()));
        return entity.getTitulo();
    }

    public UbicacionEntity getUbicacion() {
        entity.setUbicacion(new UbicacionModel().findById(entity.getUbicacionId()));
        return entity.getUbicacion();
    }

    public List<SolicitudEntity> findByDates(String desde, String hasta) {
        String query = "select * from solicitud where fecha_solicitud>='" + desde + "' and fecha_solicitud<='" + hasta + "' and estado<>0";
        List<SolicitudEntity> solicitudes = findListByQuery(query);
        return solicitudes;
    }

//    public DefaultTableModel findStadisticsByDates(String desde, String hasta) {
//        String query = "select tipo_solicitud as label,count(*)as value from solicitud where fecha_solicitud>='" + desde + "' and fecha_solicitud<='" + hasta + "' and estado<>0 group by tipo_solicitud";
//        System.out.println(query);
//        List<EstadisticaEntity> solicitudes = new EstadisticaModel().findListByQuery(query);
//        DefaultTableModel model = new DefaultTableModel(new Object[]{"TIPO_SOL", "TOTAL"}, solicitudes.size());
//        model.setRowCount(0);
//        for (EstadisticaEntity entity : solicitudes) {
//            model.addRow(new Object[]{
//                entity.getLabel(),
//                entity.getValue()
//            });
//        }
//        return model;
//    }
    public List<EstadisticaEntity> findStadisticsByDates(String desde, String hasta) {
        String query = "select tipo_solicitud as label,count(*)as value from solicitud where fecha_solicitud>='" + desde + "' and fecha_solicitud<='" + hasta + "' and estado<>0 group by tipo_solicitud";
        System.out.println(query);
        List<EstadisticaEntity> solicitudes = new EstadisticaModel().findListByQuery(query);
        return solicitudes;
    }

    public DefaultTableModel toTable(List<SolicitudEntity> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DefaultTableModel model = new DefaultTableModel(new Object[]{
            "ID",
            "ACTIVIDAD",
            "CLIENTE",
            "REGISTRADOR",
            "TITULO",
            "UBICACION",
            "TIPO",
            "NRO_ORDEN",
            "GESTION",
            "NRO_HECTAREAS",
            "FECHA_SOLICITUD",
            "FECHA_FINALIZACION"}, list.size());
        model.setRowCount(0);
        for (SolicitudEntity entity : list) {
            this.entity = entity;
            model.addRow(new Object[]{
                entity.getId(),
                getActividad().getNombre(),
                getPersona().toString(),
                getRegistrador().toString(),
                getTitulo().toString(),
                getUbicacion().toString(),
                entity.getTipoSolicitud(),
                entity.getNroOrden(),
                entity.getGestion(),
                entity.getNroHectareas(),
                sdf.format(entity.getFechaSolicitud()),
                entity.getFechaFinalizacion() != null ? sdf.format(entity.getFechaFinalizacion()) : null
            });
        }
        return model;
    }

    public List<EstadisticaEntity> findStadisticsClientsTop() {
        String query = "select count(*)as value,concat(persona.apellido_pat,' ',persona.apellido_mat,' ',persona.nombre)as label\n"
                + "from solicitud, persona\n"
                + "where cliente_id=persona.id\n"
                + "group by persona.id";
        System.out.println(query);
        List<EstadisticaEntity> estadisticas = new EstadisticaModel().findListByQuery(query);
        return estadisticas;
    }
}
