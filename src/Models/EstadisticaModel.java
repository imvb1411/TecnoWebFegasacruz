/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.EstadisticaEntity;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mijael
 */
public class EstadisticaModel extends Model<EstadisticaEntity> {

    @Override
    public EstadisticaEntity loadData(ResultSet rs) throws SQLException {
        return new EstadisticaEntity(rs.getString("label"), rs.getString("value"));
    }

    @Override
    public Entity loadEntity() {
        return new Entity("", null);
    }

    @Override
    public List<EstadisticaEntity> findListByQuery(String query) {
        return super.findListByQuery(query); //To change body of generated methods, choose Tools | Templates.
    }

}
