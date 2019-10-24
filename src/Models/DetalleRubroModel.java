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
import java.util.Map;

/**
 *
 * @author Mijael
 */
public class DetalleRubroModel extends Model<DetalleRubroEntity> {

    DetalleRubroEntity entity;

    @Override
    public DetalleRubroEntity loadData(ResultSet rs) throws SQLException {
        entity = null;
        if (rs.next()) {
            entity = new DetalleRubroEntity(
                    rs.getInt("id"),
                    rs.getInt("rubro_id"),
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getTimestamp("fecha_reg"),
                    rs.getTimestamp("fecha_mod"),
                    rs.getByte("estado")
            );
        }
        return entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("rubro_id", entity.getRubroId());
        params.put("codigo", entity.getCodigo());
        params.put("nombre", entity.getNombre());
        params.put("estado", entity.getEstado());
        return new Entity("detalle_rubro", params);
    }

    public RubroEntity getRubro() {
        return new RubroModel().findById(entity.getRubroId());
    }
}
