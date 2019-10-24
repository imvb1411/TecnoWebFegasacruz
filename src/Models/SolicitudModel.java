/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ActividadEntity;
import Entities.PersonaEntity;
import Entities.SolicitudEntity;
import Entities.TituloEntity;
import Entities.UbicacionEntity;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class SolicitudModel extends Model<SolicitudEntity>{

    SolicitudEntity entity;

    public SolicitudModel(SolicitudEntity entity) {
        this.entity = entity;
    }
    
    public SolicitudModel() {
    }
    
    public SolicitudEntity getEntity() {
        return entity;
    }

    public void setEntity(SolicitudEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("actividad_id", entity.getActividadId());
        args.put("cliente_id", entity.getClienteId());
        args.put("registrador_id", entity.getRegistradorId());
        args.put("titulo_id", entity.getTituloId());
        args.put("ubicacion_id", entity.getUbicacionId());
        args.put("tipo_solicitud", entity.getTipoSolicitud());
        args.put("nro_orden", entity.getNroOrden());
        args.put("gestion", entity.getGestion());
        args.put("nro_hectareas", entity.getNroHectareas());
        args.put("fecha_solicitud", entity.getFechaSolicitud());
        args.put("fecha_finalizacion", entity.getFechaFinalizacion());
        args.put("estado", entity.getEstado());
        return new Entity("solicitud", args);
    }

    @Override
    public SolicitudEntity loadData(ResultSet rs) throws SQLException {
        entity=null;
        if(rs.next()){
            entity=new SolicitudEntity(
                    rs.getInt("id"),
                    rs.getInt("actividad_id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("registrador_id"),
                    rs.getInt("titulo_id"),
                    rs.getInt("ubicacion_id"),
                    SolicitudEntity.TIPO.valueOf(rs.getString("tipo_solicitud")),
                    rs.getString("nro_orden"),
                    rs.getInt("gestion"),
                    rs.getDouble("nro_hectareas"),
                    rs.getDate("fecha_solicitud"),
                    rs.getDate("fecha_finalizacion"),
                    rs.getTimestamp("fecha_reg"),
                    rs.getTimestamp("fecha_mod"),
                    rs.getByte("estado"));
        }
        return entity;
    }
    
    public ActividadEntity getActividad(){
        entity.setActividad(new ActividadModel().findById(entity.getActividadId()));
        return entity.getActividad();
    }
    
    public PersonaEntity getPersona(){
        entity.setCliente(new PersonaModel().findById(entity.getClienteId()));
        return entity.getCliente();
    }
    
    public PersonaEntity getRegistrador(){
        entity.setRegistrador(new PersonaModel().findById(entity.getRegistradorId()));
        return entity.getRegistrador();
    }
    
    public TituloEntity getTitulo(){
        entity.setTitulo(new TituloModel().findById(entity.getTituloId()));
        return entity.getTitulo();
    }
    
    public UbicacionEntity getUbicacion(){
        entity.setUbicacion(new UbicacionModel().findById(entity.getUbicacionId()));
        return entity.getUbicacion();
    }
}
