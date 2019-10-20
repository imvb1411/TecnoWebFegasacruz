/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import Data.Connection.GenericStoredProcedures;
import Data.Connection.SingletonConnection;
import Framework.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mijael
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SingletonConnection cx=SingletonConnection.getInstance();
            cx.connect();
            ResultSet rs=cx.callGenericFindProcedure(GenericStoredProcedures.framework_findAll, new Entity("actividad", null));
            while(rs.next()){
                System.out.println(rs.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
