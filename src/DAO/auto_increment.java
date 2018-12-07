/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Frazier-98
 */
public class auto_increment {
    static Connection cn;
    public int id() {
        int folio = 1;
        PreparedStatement ps = null;
        ResultSet rs =null;
        Conexion db = new Conexion();
        try{
            ps = db.Enlace(cn).prepareStatement("select max(Folio) from Venta_principal");
            rs = ps.executeQuery();
            while(rs.next()){
                folio=rs.getInt(1) + 1;
            }
            
            
        }catch(Exception ex){
            System.out.println("error"+ex.getMessage());
        }
        return  folio;
    }
      
    
}
