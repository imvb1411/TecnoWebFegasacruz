/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.util.Map;

/**
 *
 * @author imvargas
 */
public class Entity {
    private String table;
    private String primaryKey;
    private Map<String,Object> fields;

    public Entity(String table, String primaryKey,Map<String,Object> fields) {
        this.table = table;
        this.primaryKey = primaryKey;
        this.fields=fields;
    }

    public Entity(String table,Map<String,Object> fields) {
        this.table = table;
        primaryKey="id";
        this.fields=fields;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Map<String,Object> getFields() {
        return fields;
    }

    public void setFields(Map<String,Object> fields) {
        this.fields = fields;
    }
}
