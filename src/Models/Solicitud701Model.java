/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ClienteSolicitudEntity;
import Entities.Solicitud701Entity;
import Entities.SolicitudEntity;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mijael
 */
public class Solicitud701Model extends Model<Solicitud701Entity> {
    Solicitud701Entity entity;
    @Override
    public Solicitud701Entity loadData(ResultSet rs) throws SQLException {
        entity=null;
        if(rs.next()){
            entity=new Solicitud701Entity(
                    rs.getInt("id"), 
                    rs.getInt("solicitud_id"), 
                    rs.getInt("ddjj_original"), 
                    rs.getInt("folio"), 
                    rs.getString("nro_titulopropiedad"), 
                    rs.getTimestamp("fecha_reg"), 
                    rs.getTimestamp("fecha_mod"), 
                    rs.getByte("estado")
            );
        }
        return entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("solicitud_id", entity.getSolicitudId());
        args.put("ddjj_original", entity.getDdjjOriginal());
        args.put("folio", entity.getFolio());
        args.put("nro_titulopropiedad", entity.getNroTituloPropiedad());
        args.put("estado", entity.getEstado());
        return new Entity("solicitud701",args);
    }
    
    public SolicitudEntity getSolicitud(){
        entity.setSolicitud(new SolicitudModel().findById(entity.getSolicitudId()));
        return entity.getSolicitud();
    }
    
    public List<ClienteSolicitudEntity> getDetalle(){
        Map<String,Object> params=new HashMap<>();
        params.put("solicitud701_id", entity.getId());
        entity.setDetalle(new ClienteSolicitudModel().findListByParams(params));
        return entity.getDetalle();
    }
}
