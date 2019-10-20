/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.TituloEntity;
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
public class TituloModel extends Model<TituloEntity>{
    TituloEntity entity;

    public TituloModel(TituloEntity entity) {
        this.entity = entity;
    }

    public TituloEntity getEntity() {
        return entity;
    }

    public void setEntity(TituloEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity(){
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("imagen", entity.getImagen());
        args.put("descripcion", entity.getDescripcion());
        return new Entity("titulo", args);
    }
    
    @Override
    public TituloEntity loadData(ResultSet rs) throws SQLException {
        entity=new TituloEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setImagen(rs.getBytes("imagen"));
            entity.setDescripcion(rs.getString("descripcion"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
}
