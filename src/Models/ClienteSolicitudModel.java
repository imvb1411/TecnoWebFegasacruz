/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ClienteSolicitudEntity;
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
public class ClienteSolicitudModel extends Model<ClienteSolicitudEntity>{

    ClienteSolicitudEntity entity;

    public ClienteSolicitudModel(ClienteSolicitudEntity entity) {
        this.entity = entity;
    }

    public ClienteSolicitudEntity getEntity() {
        return entity;
    }

    public void setEntity(ClienteSolicitudEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("persona_id", entity.getPersonaId());
        args.put("solicitud_id", entity.getSolicitudId());
        args.put("plano_id", entity.getPlanoId());
        args.put("marca_id", entity.getMarcaId());
        args.put("titulo_id", entity.getTituloId());
        args.put("fecha_solicitud ", entity.getFechaSolicitud());
        args.put("estado", entity.getEstado());
        return new Entity("cliente_solicitud", args);
    }

    @Override
    public ClienteSolicitudEntity loadData(ResultSet rs) throws SQLException {
        entity=new ClienteSolicitudEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setPersonaId(rs.getInt("persona_id"));
            entity.setSolicitudId(rs.getInt("solicitud_id"));
            entity.setPlanoId(rs.getInt("plano_id"));
            entity.setMarcaId(rs.getInt("marca_id"));
            entity.setTituloId(rs.getInt("titulo_id"));
            entity.setFechaSolicitud(rs.getTimestamp("fecha_solicitud"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
}
