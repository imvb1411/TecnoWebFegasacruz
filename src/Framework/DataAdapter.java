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
    
    public abstract List<T> findAll(Status status);
    public abstract T findById(int id);
    public abstract List<T> findListById(int id);
    public abstract T findByParams(Map<String, Object> args);
    public abstract List<T> findListByParams(Map<String, Object> args);
    public abstract Entity loadEntity();
    public int insert();
    public int update();
    public int delete();
}
