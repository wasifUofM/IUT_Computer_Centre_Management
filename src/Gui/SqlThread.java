/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PaPa
 */
public class SqlThread extends Thread {

    Statement st = null;

    public SqlThread(Statement st) {
        this.st = st;
      
    }

    @Override
    public void run() {
        try {
            st.executeBatch();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlThread.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
