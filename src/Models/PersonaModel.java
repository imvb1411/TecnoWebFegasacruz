package Models;

import Entities.PersonaEntity;
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
public class PersonaModel extends Model<PersonaEntity>{

    PersonaEntity entity;

    public PersonaModel(PersonaEntity entity) {
        this.entity = entity;
    }

    public PersonaEntity getEntity() {
        return entity;
    }

    public void setEntity(PersonaEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("ci", entity.getCi());
        args.put("nomre", entity.getNombre());
        args.put("apellido", entity.getApellido());
        args.put("telefono", entity.getTelefono());
        args.put("email", entity.getEmail());
        args.put("tipo_persona", entity.getTipoPersona());
        args.put("estado", entity.getEstado());
        return new Entity("users_people", args);
    }

    @Override
    public PersonaEntity loadData(ResultSet rs) throws SQLException {
        entity=new PersonaEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setCi(rs.getString("ci"));
            entity.setNombre(rs.getString("nombre"));
            entity.setApellido(rs.getString("apellido"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
}
