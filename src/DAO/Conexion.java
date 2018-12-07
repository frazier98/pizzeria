/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Empresa
 */
public class Conexion {
//    Connection conectar=null;
//    public Connection conexion(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conectar=DriverManager.getConnection("jdbc:mysql://localhost/pizza","root","12345");
//        } catch (Exception e) {
//            System.out.print(e.getMessage());
//        }
//        return conectar;
//    } "C:\\Users\\Frazier-98\\Documents\\proyecto\\pizza.db";
         static Connection cn = null;
//creamos la clase conexion
    public static Connection Enlace(Connection cn)throws SQLException{
    //ruta de la base de datos la cual crearemos 
        String ruta = "C:\\Users\\Frazier-98\\Documents\\proyecto\\punto2\\proyecto ejmeplo\\proyecto\\proyectobueno\\amalia\\punto.db";
        try{
        Class.forName("org.sqlite.JDBC");
        cn = DriverManager.getConnection("jdbc:sqlite:"+ruta);        
        }catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
    
    
    
}
