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
public class UbicacionModel extends Model<UbicacionEntity> {

    UbicacionEntity entity;

    public UbicacionModel(UbicacionEntity entity) {
        this.entity = entity;
    }

    public UbicacionModel() {
        entity = new UbicacionEntity();
    }

    public UbicacionEntity getEntity() {
        return entity;
    }

    public void setEntity(UbicacionEntity entity) {
        this.entity = entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String, Object> args = new HashMap<>();
        args.put("id", entity.getId());
        args.put("nombre ", entity.getNombre());
        args.put("tipo ", entity.getTipoUbicacion());
        args.put("ubicacion_id ", entity.getUbicacionId());
        args.put("estado", entity.getEstado());
        return new Entity("ubicacion", args);
    }

    @Override
    public UbicacionEntity loadData(ResultSet rs) throws SQLException {
        entity = null;
        UbicacionEntity.TIPO tipo = null;
        switch (rs.getString("tipo")) {
            case "1":
                tipo = UbicacionEntity.TIPO.Departamento;
                break;
            case "2":
                tipo = UbicacionEntity.TIPO.Provincia;
                break;
            case "3":
                tipo = UbicacionEntity.TIPO.Municipio;
                break;
            case "4":
                tipo = UbicacionEntity.TIPO.Zona;
                break;
            case "5":
                tipo = UbicacionEntity.TIPO.Subzona;
                break;
            case "6":
                tipo = UbicacionEntity.TIPO.Sitio;
                break;
        }
        entity = new UbicacionEntity(
                rs.getInt("id"),
                rs.getInt("ubicacion_id"),
                rs.getString("nombre"),
                tipo,
                rs.getTimestamp("fecha_reg"),
                rs.getTimestamp("fecha_mod"),
                rs.getByte("estado"));
        return entity;
    }

    public UbicacionEntity getUbicacion() {
        return findById(entity.getUbicacionId());
    }
}
