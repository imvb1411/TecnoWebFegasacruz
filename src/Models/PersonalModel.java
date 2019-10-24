/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Entities.PersonaEntity;
import Entities.PersonalEntity;
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
 * @author ASUS
 */
public class PersonalModel extends Model<PersonalEntity>{

    PersonalEntity entity;

    public PersonalModel(PersonalEntity entity) {
        this.entity = entity;
    }

    public PersonalEntity getEntity() {
        return entity;
    }

    public void setEntity(PersonalEntity entity) {
        this.entity = entity;
    }
    
    @Override
    public Entity loadEntity() {
        Map<String,Object> args=new HashMap<>();
        args.put("id", entity.getId());
        args.put("personaId ", entity.getPersonaId());
        args.put("nick ", entity.getNick());
        args.put("password ", entity.getPassword());
        args.put("tipo_personal ", entity.getTipoPersonal());
        args.put("estado", entity.getEstado());
        return new Entity("ubicacion", args);
    }

    @Override
    public PersonalEntity loadData(ResultSet rs) throws SQLException {
        entity=new PersonalEntity();
        if(rs.next()){
            entity.setId(rs.getInt("id"));
            entity.setPersonaId(rs.getInt("personaId"));
            entity.setNick(rs.getString("nick"));
            entity.setPassword(rs.getString("password"));  
            entity.setTipoPersonal(rs.getString("tipo_personal"));            
            entity.setEstado(rs.getByte("estado"));
        }
        return entity;
    }
    
    public PersonaEntity getPersona(){
        return new PersonaModel().findById(entity.getPersonaId());
    }
    
    public List<SolicitudEntity> getSolicitudes() {
        Map<String, Object> args = new HashMap<>();
        args.put("registrador_id", entity.getId());
        return new SolicitudModel().findListByParams(args);
    }
}
