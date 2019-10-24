package Models;

import Entities.PersonaEntity;
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
public class PersonaModel extends Model<PersonaEntity> {

    PersonaEntity entity;

    public PersonaModel(PersonaEntity entity) {
        this.entity = entity;
    }

    public PersonaModel() {
    }

    public PersonaEntity getEntity() {
        return entity;
    }

    public void setEntity(PersonaEntity entity) {
        this.entity = entity;
    }

    @Override
    public Entity loadEntity() {
        Map<String, Object> args = new HashMap<>();
        args.put("id", entity.getId());
        args.put("ci", entity.getCi());
        args.put("nomre", entity.getNombre());
        args.put("apellido_pat", entity.getApellidoPaterno());
        args.put("apellido_mat", entity.getApellidoMaterno());
        args.put("telefono", entity.getTelefono());
        args.put("email", entity.getEmail());
        args.put("tipo_persona", entity.getTipoPersona());
        args.put("estado", entity.getEstado());
        return new Entity("persona", args);
    }

    @Override
    public PersonaEntity loadData(ResultSet rs) throws SQLException {
        entity = null;
        if (rs.next()) {
            entity=new PersonaEntity(
                    rs.getInt("id"),
                    rs.getString("ci"),
                    rs.getString("nombre"), 
                    rs.getString("apellido_pat"), 
                    rs.getString("apellido_mat"), 
                    rs.getString("telefono"), 
                    rs.getString("telefono"), 
                    PersonaEntity.TIPO.valueOf(rs.getString("tipo_persona")), 
                    rs.getTimestamp("fecha_reg"), 
                    rs.getTimestamp("fecha_mod"), 
                    rs.getByte("estado")
            );
        }
        return entity;
    }

    public List<PersonaEntity> findByTipo(PersonaEntity.TIPO tipo) {
        Map<String, Object> args = new HashMap<>();
        args.put("tipo_persona", tipo.toString());
        return findListByParams(args);
    }

    public List<SolicitudEntity> getSolicitudes() {
        Map<String, Object> args = new HashMap<>();
        args.put("cliente_id", entity.getId());
        return new SolicitudModel().findListByParams(args);
    }
}
