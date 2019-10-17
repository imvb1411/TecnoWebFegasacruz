/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Connection;

import Framework.Entity;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author imvargas
 */
public class SingletonConnection implements Serializable {

    private String dbHost = "mail.ficct.uagrm.edu.bo";
    private final String DB_NAME = "db_grupo06sc";
    private String dbUser = "grupo06sc";
    private String dbPassword = "grupo06grupo06";
    private final int DB_PORT = 5432;
    private final String url;
    private Connection objConnection;
    private Connection objConnectionBinnacle;
    private static SingletonConnection _instance;

    public SingletonConnection(String dbHost, String dbUser, String dbPassword) {
        this.dbHost = dbHost;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        url = "jdbc:postgresql://" + dbHost + ":" + DB_PORT + "/" + DB_NAME + "?autoReconnect=true&useSSL=false";
    }

    private SingletonConnection() {
        url = "jdbc:postgresql://" + dbHost + ":" + DB_PORT + "/" + DB_NAME + "?autoReconnect=true&useSSL=false";
    }

    /**
     * Get a instance for connect to DataBase
     *
     * @return
     */
    public static SingletonConnection getInstance() {
        if (SingletonConnection._instance == null) {
            _instance = new SingletonConnection();
        }
        return _instance;
    }

    public Connection getConnection() {
        return objConnection;
    }

    /**
     * Connect to DB
     *
     * @return
     */
    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            objConnection = DriverManager.getConnection(url, dbUser, dbPassword);
            return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("SingletonConnection.connect: Error " + e.getMessage());
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    public boolean connectBinnacle() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            objConnectionBinnacle = DriverManager.getConnection(url, dbUser, dbPassword);
            return true;
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            System.out.println("SingletonConnection.connectBinnacle: Error " + e.getMessage());
            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    /**
     * Close the connection
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (objConnection != null) {
            objConnection.close();
        }
    }

    public void disconnectBinnacle() throws SQLException {
        if (objConnectionBinnacle != null) {
            objConnectionBinnacle.close();
        }
    }

    /**
     * @param proc Name of stored procedure
     * @param args Map with the procedure parameters
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet callProcedure(String proc, Map<String, Object> args) throws SQLException {
        CallableStatement cst;
        //objConnection.setAutoCommit(false);
        if (args != null && !args.isEmpty()) {
            String params = getParams(args.size());
            cst = objConnection.prepareCall("{ call " + proc + params + "}");
            System.out.println("{ call " + proc + params + "}");
            for (Map.Entry<String, Object> entrySet : args.entrySet()) {
                String key = entrySet.getKey();
                Object value = entrySet.getValue();
                if (value == null) {
                    cst.setString(key, null);
                } else {
                    Class classObject = value.getClass();
                    String classname = classObject.getCanonicalName();
                    switch (classname) {
                        case "java.lang.String":
                            cst.setString(key, (String) value);
                            break;
                        case "java.lang.Integer":
                            cst.setInt(key, (Integer) value);
                            break;
                        case "java.lang.Double":
                            cst.setDouble(key, (Double) value);
                            break;
                        case "java.lang.Time":
                            cst.setTime(key, (Time) value);
                            break;
                        case "java.lang.Float":
                            cst.setFloat(key, (Float) value);
                            break;
                        case "java.lang.Byte":
                            cst.setByte(key, (Byte) value);
                            break;
                        case "java.lang.Short":
                            cst.setShort(key, (Short) value);
                            break;
                        case "java.lang.Long":
                            cst.setLong(key, (Long) value);
                            break;
                        case "java.sql.Timestamp":
                            cst.setTimestamp(key, (Timestamp) value);
                            break;
                        case "java.sql.Date":
                            cst.setTimestamp(key, new Timestamp(((java.sql.Date) value).getTime()));
                            break;
                        case "java.util.Date":
                            cst.setTimestamp(key, new Timestamp(((java.util.Date) value).getTime()));
                            break;
                        case "java.lang.Character":
                            cst.setString(key, value.toString());
                            break;
                        case "java.lang.Boolean":
                            cst.setBoolean(key, (Boolean) value);
                            break;
                        case "java.io.FileInputStream":
                            cst.setBinaryStream(key, (InputStream) value);
                            break;
                        default:
                            Logger.getLogger(SingletonConnection.class.getName()).log(Level.SEVERE, "CLASSNAME NO ENCONTRADO -> ".concat(classname));
                    }
                }
            }
        } else {
            cst = objConnection.prepareCall("{ call " + proc + "() }");
        }
        ResultSet result = cst.executeQuery();
//        if (result.next()) {
//            objConnection.commit();
//        } else {
//            objConnection.rollback();
//        }
        //result.previous();
        return result;
    }

    public ResultSet callGenericFindProcedure(GenericStoredProcedures procedure, Entity entity) throws SQLException {
        CallableStatement cst;
        String query = "";
        switch (procedure) {
            case framework_generic_findById:
                query = "call " + procedure.toString() + "('" + entity.getTable() + "'," + getValuesForFind(entity.getFields())+ ")";
                System.out.println(query);
                break;
        }
        cst = objConnection.prepareCall(query);
        return (cst != null ? cst.executeQuery() : null);
    }

    public ResultSet callGenericProcedureBinnacle(GenericStoredProcedures procedure, Entity entity) throws SQLException {
        return callGenericProcedureMask(objConnectionBinnacle, procedure, entity);
    }

    public ResultSet callGenericProcedure(GenericStoredProcedures procedure, Entity entity) throws SQLException {
        return callGenericProcedureMask(objConnection, procedure, entity);
    }

    private ResultSet callGenericProcedureMask(Connection connection, GenericStoredProcedures procedure, Entity entity) throws SQLException {
        CallableStatement cst;
        String query = "";
        switch (procedure) {
            case framework_generic_insert:
                Object[] fieldsAndValues = getFieldsAndValues(entity);
                query = "call " + procedure.toString() + "('" + entity.getTable() + "'," + fieldsAndValues[0] + "," + fieldsAndValues[1] + ")";
                System.out.println(query);
                break;
            case framework_generic_update:
                query = "call " + procedure.toString() + "('" + entity.getTable() + "'," + getValuesForUpdate(entity.getFields()) + ",'" + (int) entity.getFields().get("id") + "');";
                System.out.println(query);
                break;
            case framework_generic_delete:
                query = "call " + procedure.toString() + "('" + entity.getTable() + "'," + "'" + (int) entity.getFields().get("id") + "');";
                System.out.println(query);
                break;
        }
        cst = connection.prepareCall(query);
        return (cst != null ? cst.executeQuery() : null);
    }

    private static Object[] getFieldsAndValues(Entity entity) {
        Map<String, Object> args = entity.getFields();
        Object[] result = new String[2];
        Object coma = '"';
        result[0] = coma.toString();
        result[1] = coma.toString();

        for (Map.Entry<String, Object> entrySet : args.entrySet()) {
            if (!entrySet.getKey().equals(entity.getPrimaryKey())) {
                result[0] += entrySet.getKey() + ",";
                Object value = entrySet.getValue();
                String className = value.getClass().getCanonicalName();
                switch (className) {
                    case "java.lang.String":
                        result[1] += "'" + value.toString() + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.lang.Integer":
                        result[1] += "'" + (int) value + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.math.BigDecimal":
                        result[1] += "'" + new BigDecimal(value.toString()) + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.sql.Date":
                        result[1] += "'" + (java.sql.Date) value + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.util.Date":
                        result[1] += "'" + new SimpleDateFormat("yyyy-MM-dd").format(value.toString()) + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.lang.Boolean":
                        result[1] += "'" + (Boolean) value + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    case "java.lang.Byte":
                        result[1] += "'" + (Byte) value + "',";
                        System.out.println("Result" + result[1]);
                        break;
                    default:
                        System.out.println("No se encontró la clase " + className);
                        break;
                }
            }
        }
        result[0] = result[0].toString().substring(0, result[0].toString().length() - 1);
        result[1] = result[1].toString().substring(0, result[1].toString().length() - 1);
        result[0] = result[0].toString() + coma;
        result[1] = result[1].toString() + coma;
        return result;
    }

    private String getValuesForUpdate(Map<String, Object> args) {
        Object coma = '"';
        String params = coma.toString();
        for (Map.Entry<String, Object> entrySet : args.entrySet()) {
            if (!entrySet.getKey().equals("id")) {
                params += entrySet.getKey() + "=";
                Object value = entrySet.getValue();
                String className = value.getClass().getCanonicalName();
                switch (className) {
                    case "java.lang.String":
                        params += "'" + value.toString() + "',";
                        System.out.println("Result" + params);
                        break;
                    case "java.lang.Integer":
                        params += "'" + (int) value + "',";
                        System.out.println("Result" + params);
                        break;
                    case "java.math.BigDecimal":
                        params += "'" + new BigDecimal(value.toString()) + "',";
                        System.out.println("Result" + params);
                        break;
                    case "java.lang.Byte":
                        params += "'" + Byte.parseByte(value.toString()) + "',";
                        System.out.println("Result" + params);
                        break;
                    default:
                        System.out.println("No se encontró la clase " + className);
                        break;
                }
            }
        }
        params = params + coma;
        return params;
    }

    private String getValuesForFind(Map<String, Object> args) {
        Object coma = '"';
        String params = coma.toString();
        int count=0;
        for (Map.Entry<String, Object> entrySet : args.entrySet()) {
            count++;
            if(count>1)
                params +=" and " + entrySet.getKey() + " like ";
            else
                params += entrySet.getKey() + " like ";
            Object value = entrySet.getValue();
            String className = value.getClass().getCanonicalName();
            switch (className) {
                case "java.lang.String":
                    params += "'" + value.toString() + "' ";
                    System.out.println("Result" + params);
                    break;
                case "java.lang.Integer":
                    params += "'" + (int) value + "' ";
                    System.out.println("Result" + params);
                    break;
                case "java.math.BigDecimal":
                    params += "'" + new BigDecimal(value.toString()) + "' ";
                    System.out.println("Result" + params);
                    break;
                case "java.lang.Byte":
                    params += "'" + Byte.parseByte(value.toString()) + "' ";
                    System.out.println("Result" + params);
                    break;
                default:
                    System.out.println("No se encontró la clase " + className);
                    break;
            }
        }
        params = params + coma;
        return params;
    }

    private static String getParams(int cantidad) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < cantidad; i++) {
            sb.append("?,");
        }
        if (cantidad > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(")");
        return sb.toString();
    }

    public void setAutoCommit(boolean flag) throws SQLException {
        objConnection.setAutoCommit(flag);
    }

    public void commit() throws SQLException {
        objConnection.commit();
    }

    public void rollback() throws SQLException {
        objConnection.rollback();
    }

}
