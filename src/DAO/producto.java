/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.Conexion.cn;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Empresa
 */
public class producto {
    


    public void gu(int folio_venta,String nombre,String descripcion,String tipo,int cantidad,double precio ){
        try{
            Statement s = cn.createStatement();
            String query = "INSERT INTO Venta_producto (folio_venta,nombre,descripcion,tipo,cantidad,precio)values ('"+folio_venta+"','"+nombre+"','"+descripcion+"','"+tipo+"','"+cantidad+"','"+precio+"')";
            s.executeUpdate(query);
            s.close();
            cn.close();
            JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        
    }
    
    
    
    
    
}
