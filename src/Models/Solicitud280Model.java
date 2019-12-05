/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.Solicitud280Entity;
import Entities.SolicitudEntity;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mijael
 */
public class Solicitud280Model extends Model<Solicitud280Entity>{
    
    Solicitud280Entity entity;

    public Solicitud280Model(Solicitud280Entity entity) {
        this.entity = entity;
    }

    public Solicitud280Model() {
    }
    
    @Override
    public Solicitud280Entity loadData(ResultSet rs) throws SQLException {
        entity=null;
        if(rs.next()){
            entity=new Solicitud280Entity(
                    rs.getInt("id"), 
                    rs.getInt("solicitud_id"), 
                    rs.getString("nit_dependencia"), 
                    rs.getString("nro_documento"), 
                    rs.getString("nro_boletapago"), 
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
        args.put("nit_dependencia", entity.getNitDependencia());
        args.put("nro_documento", entity.getNroDocumento());
        args.put("nro_boletapago", entity.getNroBoletaPago());
        args.put("estado", entity.getEstado());
        return new Entity("solicitud280",args);
    }
    
    public SolicitudEntity getSolicitud(){
        entity.setSolicitud(new SolicitudModel().findById(entity.getSolicitudId()));
        return entity.getSolicitud();
    }
}
