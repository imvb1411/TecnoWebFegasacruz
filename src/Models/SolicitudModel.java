/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.SolicitudEntity;
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
        args.put("nro_documento", entity.getNroDocumento());
        args.put("nro_orden_pago", entity.getNroOrdenPago());
        args.put("gestion", entity.getGestion());
        args.put("tipo_formulario_idc", entity.getTipoFormularioId());
        args.put("ubicacion_id", entity.getUbicacionId());
        args.put("actividad_id", entity.getActividadId());
        args.put("personal_id", entity.getPersonalId());
        args.put("estado", entity.getEstado());
        return new Entity("solicitud", args);
    }

    @Override
    public SolicitudEntity loadData(ResultSet rs) throws SQLException {
        entity=new SolicitudEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setNroDocumento(rs.getString("nro_documento"));
            entity.setNroOrdenPago(rs.getInt("nro_orden_pago"));
            entity.setGestion(rs.getInt("gestion"));
            entity.setTipoFormularioId(rs.getInt("tipo_formulario_idc"));
            entity.setUbicacionId(rs.getInt("ubicacion_id"));
            entity.setActividadId(rs.getInt("actividad_id"));
            entity.setPersonalId(rs.getInt("personal_id"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
}
