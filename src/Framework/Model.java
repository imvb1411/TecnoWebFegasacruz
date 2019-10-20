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
            args.put("_status", status.toString());
            Entity objEntity = loadEntity();
            ResultSet rs = CX.callProcedure(objEntity.getTable() + "_findAll", args);
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
    public List<T> findById(int id) {
        List<T> entity = new ArrayList<>();
        try {
            CX.connect();
            Map<String, Object> args = new HashMap<>();
            args.put(loadEntity().getPrimaryKey(), id);
            ResultSet rs = CX.callProcedure(loadEntity().getTable() + "_findById", args);
            while (rs.next()) {
                entity.add(loadData(rs));
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findById");
        }
        return entity;
    }

    @Override
    public List<T> findById(Map<String, Object> args) {
        List<T> entity = new ArrayList<>();
        ResultSet rs=null;
        try {
            CX.connect();
            if (args != null) {
                Entity ent = new Entity();
                ent.setTable(args.get("table").toString());
                args.remove("table");
                ent.setFields(args);
                rs = CX.callGenericFindProcedure(GenericStoredProcedures.framework_generic_findById, ent);
            }else{
                rs = CX.callGenericFindProcedure(GenericStoredProcedures.framework_findAll, loadEntity());
            }
            while (rs.next()) {
                entity.add(loadData(rs));
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findById");
        }
        return entity;
    }

    @Override
    public List<T> findByParams(String proc, Map<String, Object> args) {
        List<T> entity = new ArrayList<>();
        try {
            CX.connect();
            ResultSet rs = CX.callProcedure(proc, args);
            while (rs.next()) {
                entity.add(loadData(rs));
            }
            CX.disconnect();
        } catch (SQLException e) {
            getCatchError(e, "findByParams");
        }
        return entity;
    }
    //</editor-fold>

    //<editor-fold desc="CRUD Methods" defaultstate="collapsed">
//    public int insert1() {
//        int idInserted = -1;
//        try {
//            CX.connect();
//            CX.setAutoCommit(false);
//            Entity entity=loadEntity();
//            idInserted= CX.callGenericProcedure1(GenericStoredProcedures.framework_generic_insert,entity);     
//        } catch (SQLException ex) {
//            getCatchError(ex,"insert");
//        }finally{
//            if (idInserted < 0) {
//                try {
//                    CX.rollback();
//                    System.out.println("Rollback");
//                } catch (SQLException ex) {
//                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            } else {
//                try {
//                    CX.commit();
//                    System.out.println("Commit");
//                } catch (SQLException ex) {
//                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }       
//        return idInserted;
//    }
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
