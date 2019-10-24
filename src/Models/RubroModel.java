/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.DetalleRubroEntity;
import Entities.RubroEntity;
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
public class RubroModel extends Model<RubroEntity>{
    RubroEntity entity;
    DetalleRubroEntity detalle;

    public RubroModel(RubroEntity entity) {
        this.entity = entity;
    }

    public RubroModel() {
    }

    public RubroEntity getEntity() {
        return entity;
    }

    public void setEntity(RubroEntity entity) {
        this.entity = entity;
    }

    @Override
    public RubroEntity loadData(ResultSet rs) throws SQLException {
        entity=null;
        if(rs.next()){
            entity=new RubroEntity(
                    rs.getInt("id"), 
                    rs.getString("nombre"), 
                    rs.getString("descripcion"), 
                    rs.getTimestamp("fecha_reg"),
                    rs.getTimestamp("fecha_mod"), 
                    rs.getByte("estado")
            );
        }
        return entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String,Object> params=new HashMap<>();
        params.put("id", entity.getId());
        params.put("nombre", entity.getNombre());
        params.put("descripcion", entity.getDescripcion());
        params.put("estado", entity.getEstado());
        return new Entity("rubro",params);
    }
    
    public List<DetalleRubroEntity> getDetalle(){
        Map<String,Object> args=new HashMap<>();
        args.put("rubro_id", entity.getId());
        entity.setDetalle(new DetalleRubroModel().findListByParams(args));
        return entity.getDetalle();
    }
}
