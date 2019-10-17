/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.util.List;
import java.util.Map;


/**
 *
 * @author imvargas
 * @param <T>
 */
public interface DataAdapter<T> {
    
    public abstract <T> List<T> findAll(Status status);
    public abstract List<T> findById(Map<String,Object> args);
    public abstract T findById(int id);
    public abstract List<T> findByParams(String procedure,Map<String,Object> args);
    public int insert();
    public int update();
    public int delete();
}
