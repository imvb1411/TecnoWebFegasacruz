/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import Data.Connection.GenericStoredProcedures;
import Data.Connection.SingletonConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author imvargas
 * @param <T>
 */
public abstract class Model<T> implements DataAdapter<T> {

    public static final SingletonConnection CX = SingletonConnection.getInstance();

    //<editor-fold desc="Model Methods" defaultstate="collapsed"> 
    public abstract T loadData(ResultSet rs) throws SQLException;

    @Override
    public abstract Entity loadEntity();
    //</editor-fold>

    //<editor-fold desc="Read Methods" defaultstate="collapsed">
    @Override
    public List<T> findAll(Status status) {
        List<T> list = new ArrayList<>();
        try {
            CX.connect();
            Map<String, Object> args = new HashMap<>();
            args.put("estado", status.toString());
            Entity objEntity = new Entity();
            objEntity.setTable(loadEntity().getTable());
            objEntity.setFields(args);
            ResultSet rs = CX.callGenericFindProcedure(objEntity);
            while (rs.next()) {
                list.add(loadData(rs));
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findAll");
        }
        return list;
    }
    
    @Override
    public T findById(int id) {
        T entity = null;
        ResultSet rs;
        try {
            Entity e = loadEntity();
            rs = CX.executeSelectById(e, id);
            if (rs.next()) {
                entity = loadData(rs);
            }
        } catch (SQLException ex) {
            getCatchError(ex, "findById(id)");
        }
        return entity;
    }

    @Override
    public List<T> findListById(int id) {
        List<T> list = new ArrayList<>();
        ResultSet rs;
        try {
            Entity e = loadEntity();
            rs = CX.executeSelectById(e, id);
            while (rs.next()) {
                list.add(loadData(rs));
            }
        } catch (SQLException ex) {
            getCatchError(ex, "findListById(id)");
        }
        return list;
    }
    
    @Override
    public T findByParams(Map<String, Object> args) {
        T entity = null;
        ResultSet rs;
        try {
            CX.connect();
            if (args != null) {
                Entity ent = new Entity();
                ent.setTable(loadEntity().getTable());
                ent.setFields(args);
                rs = CX.callGenericFindProcedure(ent);
                if (rs.next()) {
                    entity=loadData(rs);
                }
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findByParams");
        }
        return entity;
    }
    
    @Override
    public List<T> findListByParams(Map<String, Object> args) {
        List<T> entity = new ArrayList<>();
        ResultSet rs;
        try {
            CX.connect();
            if (args != null) {
                Entity ent = new Entity();
                ent.setTable(loadEntity().getTable());
                ent.setFields(args);
                rs = CX.callGenericFindProcedure(ent);
                while (rs.next()) {
                    entity.add(loadData(rs));
                }
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findByParams");
        }
        return entity;
    }
    //</editor-fold>

    //<editor-fold desc="CRUD Methods" defaultstate="collapsed">

    @Override
    public int insert() {
        int idInserted = -1;
        try {
            CX.connect();
            CX.setAutoCommit(false);
            Entity entity = loadEntity();
            ResultSet rs = CX.callGenericProcedure(GenericStoredProcedures.framework_generic_insert, entity);
            if (rs.next()) {
                idInserted = rs.getInt(1);
            }
        } catch (SQLException ex) {
            getCatchError(ex, "insert");
        } finally {
            if (idInserted < 0) {
                try {
                    CX.rollback();
                    System.out.println("Rollback");
                } catch (SQLException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    CX.commit();
                    System.out.println("Commit");
                } catch (SQLException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return idInserted;
    }

    @Override
    public int update() {
        int idUpdated = -1;
        try {
            CX.connect();
            Entity objEntity = loadEntity();
            ResultSet rs = CX.callGenericProcedure(GenericStoredProcedures.framework_generic_update, objEntity);
            if (rs.next()) {
                idUpdated = rs.getInt("id");
            }
        } catch (SQLException e) {
            getCatchError(e, "update");
        }
        return idUpdated;
    }

    @Override
    public int delete() {
        int idDeleted = -1;
        try {
            CX.connect();
            Entity objEntity = loadEntity();
            ResultSet rs = CX.callGenericProcedure(GenericStoredProcedures.framework_generic_delete, objEntity);
            if (rs.next()) {
                idDeleted = rs.getInt("id");
            }
        } catch (SQLException e) {
            getCatchError(e, "delete");
        }
        return idDeleted;
    }
    //</editor-fold>

    //<editor-fold desc="Auxiliar Methods" defaultstate="collapsed">
    private void getCatchError(Exception e, String method) {
        String error = "Model." + loadEntity().getTable() + "." + method + ": Error= " + e.getMessage();
        System.out.println(error);
        Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(null, error, "Error to " + method, JOptionPane.ERROR_MESSAGE);
    }
    //</editor-fold>

}
