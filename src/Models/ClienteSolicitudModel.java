/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ClienteSolicitudEntity;
import Entities.DetalleRubroEntity;
import Entities.Solicitud701Entity;
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

    public ClienteSolicitudModel() {
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
        args.put("detallerubro_id", entity.getDetalleRubroId());
        args.put("solicitud701_id", entity.getSolicitud701Id());
        args.put("valor", entity.getValor());
        args.put("estado", entity.getEstado());
        return new Entity("cliente_solicitud", args);
    }

    @Override
    public ClienteSolicitudEntity loadData(ResultSet rs) throws SQLException {
        entity=null;
        if(rs.next()){
            entity=new ClienteSolicitudEntity(
                    rs.getInt("id"),
                    rs.getInt("detallerubro_id"), 
                    rs.getInt("solicitud701_id "), 
                    rs.getDouble("valor"), 
                    rs.getTimestamp("fecha_reg"), 
                    rs.getTimestamp("fecha_mod"), 
                    rs.getByte("estado"));
        }
        return entity;
    }
    
    public DetalleRubroEntity getDetalleRubro(){
        entity.setDetalleRubro(new DetalleRubroModel().findById(entity.getDetalleRubroId()));
        return entity.getDetalleRubro();
    }
    
    public Solicitud701Entity getSolicitud701(){
        entity.setSolicitud701(new Solicitud701Model().findById(entity.getSolicitud701Id()));
        return entity.getSolicitud701();
    }
}
