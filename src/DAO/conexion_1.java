/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Frazier-98
 */
public class conexion_1 {
    
    static Connection cn = null;   
    public Connection conexion() {
            try{
        Class.forName("org.sqlite.JDBC");
        cn = DriverManager.getConnection("jdbc:sqlite: C:\\Users\\Frazier-98\\Documents\\proyecto\\pizza.db");

        }catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null, e);
        } catch (SQLException ex) {
        Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
    }
        return cn;
    }
    
    
    
    
    
    
    
}
