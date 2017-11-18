/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author PaPa
 */
import java.sql.*;
import javax.swing.*;
public class JavaConnect {
    
     Connection conn = null;
    public static Connection ConnecrDb(){
        
        
        try{
        
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:G:\\Study\\Java\\New_Computer_final\\ComputerJava.sqlite");
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return null;
        
        
        }
    
    
    } 
}
