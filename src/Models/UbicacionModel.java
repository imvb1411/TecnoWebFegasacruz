/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class UbicacionModel extends Model<UbicacionEntity>{

    UbicacionEntity entity;

    public UbicacionModel(UbicacionEntity entity) {
        this.entity = entity;
    }

    public UbicacionEntity getEntity() {
        return entity;
    }

    public void setEntity(UbicacionEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("nombre ", entity.getNombre());
        args.put("tipo_ubicacion_idc ", entity.getTipoUbicacionId());
        args.put("ubicacion_id ", entity.getUbicacionid());
        args.put("estado", entity.getEstado());
        return new Entity("ubicacion", args);
    }

    @Override
    public UbicacionEntity loadData(ResultSet rs) throws SQLException {
        entity=new UbicacionEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setNombre(rs.getString("nombre"));
            entity.setTipoUbicacionId(rs.getInt("tipo_ubicacion_idc"));
            entity.setUbicacionid(rs.getInt("ubicacion_id"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
}
