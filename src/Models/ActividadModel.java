/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.ActividadEntity;
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
public class ActividadModel extends Model<ActividadEntity>{

    ActividadEntity entity;

    public ActividadModel(ActividadEntity entity) {
        this.entity = entity;
    }

    public ActividadEntity getEntity() {
        return entity;
    }

    public void setEntity(ActividadEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("nombre", entity.getNombre());
        args.put("codigo", entity.getCodigo());
        args.put("descripcion", entity.getDescripcion());
        args.put("estado", entity.getEstado());
        return new Entity("actividad", args);
    }

    @Override
    public ActividadEntity loadData(ResultSet rs) throws SQLException {
        entity=new ActividadEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setNombre(rs.getString("nombre"));
            entity.setCodigo(rs.getInt("codigo"));
            entity.setDescripcion(rs.getString("descripcion"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
}
