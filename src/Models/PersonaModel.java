package Models;

import Entities.PersonaEntity;
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
        entity=new PersonaEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setCi(rs.getString("ci"));
            entity.setNombre(rs.getString("nombre"));
            entity.setApellidoPaterno(rs.getString("apellido_pat"));
            entity.setApellidoMaterno(rs.getString("apellido_mat"));
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
   /* public List<PersonaModel> findByTipoPersona(int tipo) {
        List<PersonaModel> list;
        Map<String,Object> args = new HashMap<>();
        args.put("tipo_persona", tipo);       
        args.put("table", "persona");
        list=findById(args);
        return list;
    }*/
}
