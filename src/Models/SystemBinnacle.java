/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Data.Connection.GenericStoredProcedures;
import Framework.Entity;
import Framework.Model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imvargas
 */
public class SystemBinnacle extends Model<SystemBinnacle> {

    //<editor-fold desc="Attributes" defaultstate="collapsed">
    private int id;
    private int staff_id;
    private String ip;
    private String _table;
    private int transaction_id;

    public static enum Operation {
        Insert, Update, Delete
    }
    private Operation operation;
    private String detail;
    private Timestamp created_at;

    private final String TABLE = "system_binnacle";
    //private final String[]fields=new String[]{"id","people_id","role_id","status","created_at","updated_at"};
    //</editor-fold>

    //<editor-fold desc="Constructors" defaultstate="collapsed">
    public SystemBinnacle(int id, int staff_id, String ip, String _table, int transaction_id, Operation operation, String detail, Timestamp created_at) {
        this.id = id;
        this.staff_id = staff_id;
        this.transaction_id = transaction_id;
        this.ip = ip;
        this._table = _table;
        this.operation = operation;
        this.detail = detail;
        this.created_at = created_at;
    }

    public SystemBinnacle(int id, int staff_id, String ip, String _table, int transaction_id, Operation operation, String detail) {
        this.id = id;
        this.staff_id = staff_id;
        this.transaction_id = transaction_id;
        this.ip = ip;
        this._table = _table;
        this.operation = operation;
        this.detail = detail;
        this.created_at = created_at;
    }

    public SystemBinnacle(int staff_id, String ip, String _table, int transaction_id, Operation operation, String detail) {
        this.staff_id = staff_id;
        this.transaction_id = transaction_id;
        this.ip = ip;
        this._table = _table;
        this.operation = operation;
        this.detail = detail;
    }

    public SystemBinnacle() {
    }
    //</editor-fold>

    //<editor-fold desc="Getters & Setters" defaultstate="collapsed">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTable() {
        return _table;
    }

    public void setTable(String table) {
        this._table = table;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    //</editor-fold>

//    public MSystemStaff getStaff() {
//        return new MSystemStaff().findById(staff_id);
//    }

    //<editor-fold desc="Override Methods" defaultstate="collapsed"> 
    @Override
    public SystemBinnacle loadData(ResultSet rs) throws SQLException {
        return new SystemBinnacle(
                rs.getInt("id"),
                rs.getInt("staff_id"),
                rs.getString("ip"),
                rs.getString("_table"),
                rs.getInt("transaction_id"),
                Operation.valueOf(rs.getString("operation")),
                rs.getString("detail"),
                rs.getTimestamp("created_at")
        );
    }

    @Override
    public Entity loadEntity() {
        Map<String, Object> args = new HashMap<>();
        args.put("id", id);
        args.put("staff_id", staff_id);
        args.put("transaction_id", transaction_id);
        args.put("ip", ip);
        args.put("_table", _table);
        args.put("operation", operation.toString());
        args.put("detail", detail);
        return new Entity(TABLE, args);
    }

    public void setEntity(Entity entity) {
        Map<String, Object> args = entity.getFields();
//        this.staff_id = Session.currentStaff.getId();
        this._table = entity.getTable();
        this.detail = "|";
        args.entrySet().forEach((entrySet) -> {
            this.detail += entrySet.getKey() + "=" + entrySet.getValue().toString() + "|";
            System.out.println(this.detail);
        });
    }

    //<editor-fold desc="Reading Methods" defaultstate="collapsed">
    @Override
    public List<SystemBinnacle> findAll(Status status) {
        return super.findAll(status);
    }

    @Override
    public SystemBinnacle findById(int id) {
        return super.findById(id);
    }
    //</editor-fold>

    //<editor-fold desc="CRUD Methods" defaultstate="collapsed">
    @Override
    public int insert() {
        int idInserted = -1;
        try {
            CX.connectBinnacle();
            Entity entity = loadEntity();
            ResultSet rs = Model.CX.callGenericProcedureBinnacle(GenericStoredProcedures.framework_generic_insert, entity);
            if (rs.next()) {
                idInserted = rs.getInt("id");
            }
            CX.disconnectBinnacle();
        } catch (SQLException ex) {
            Logger.getLogger(SystemBinnacle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return idInserted;
    }

    @Override
    public int update() {
        return super.update();
    }

    @Override
    public int delete() {
        return super.delete();
    }
    //</editor-fold>

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    //</editor-fold>
}
