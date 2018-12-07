/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.Operacion_ingrediente.cn;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Empresa
 */
public class Venta {

        
        public void Guardar_VG (int folio, int cliente,int vendedor ,String fecha, double total){
         try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO Venta_principal (folio,cliente,vendedor,fecha,total)values ('"+folio+"','"+cliente+"','"+vendedor+"','"+fecha+"','"+total+"')";
        s.executeUpdate(query);
//        s.close();
//        cn.close();
        JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
         }
        
//        public void Guardar_VP (int folio_venta,String nombre ,String descripcion,String tipo,int cantidad ,double total){
//         try{
//        Statement s=cn.createStatement();
//        String query = "INSERT INTO Venta_producto (folio_venta,nombre,descripcion,tipo,cantidad,precio)values ('"+folio_venta+"','"+nombre+"','"+descripcion+"','"+tipo+"','"+cantidad+"','"+total+"')";
//        s.executeUpdate(query);
//        s.close();
//        cn.close();
//        JOptionPane.showMessageDialog(null, "AGREGADO");
//        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}       
//         }

    
    
    
 
    
    }

