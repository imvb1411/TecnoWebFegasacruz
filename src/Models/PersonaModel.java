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
import javax.swing.table.DefaultTableModel;

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
        entity = new PersonaEntity();
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
        entity = new PersonaEntity(
                rs.getInt("id"),
                rs.getString("ci"),
                rs.getString("nombre"),
                rs.getString("apellido_pat"),
                rs.getString("apellido_mat"),
                rs.getString("telefono"),
                rs.getString("email"),
                "CLI".equals(rs.getString("tipo_persona")) ? PersonaEntity.TIPO.Cliente : PersonaEntity.TIPO.Personal,
                rs.getTimestamp("fecha_reg"),
                rs.getTimestamp("fecha_mod"),
                rs.getByte("estado")
        );
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

    public DefaultTableModel toTable(List<PersonaEntity> list) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "CI", "NOMBRE", "APELLIDOS", "TELEFONO"}, list.size());
        for (PersonaEntity entity : list) {
            model.addRow(new Object[]{
                entity.getId(),
                entity.getCi(),
                entity.getNombre(),
                entity.getApellidoPaterno().concat(" ").concat(entity.getApellidoMaterno()),
                entity.getTelefono()
            });
        }
        return model;
    }
}
