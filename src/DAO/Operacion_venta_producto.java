/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.Operacion_ingrediente.cn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Empresa
 */
public class Operacion_venta_producto {
   
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo= new DefaultTableModel();
  
    public DefaultTableModel lista(int dato){
        
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from venta_producto where folio_venta='"+dato+"' ";
//            where folio_venta='"+datos+"'";
    rs = s.executeQuery(query);
   ResultSetMetaData rsmd=rs.getMetaData(); 
   int CanColumns = rsmd.getColumnCount();
   for(int i=1;i<=CanColumns;i++){
   modelo.addColumn(rsmd.getColumnLabel(i));
   }
   while (rs.next()){
       Object[] fila=new Object[CanColumns];
   for(int i=0;i<CanColumns;i++){
   fila[i] = rs.getObject(i+1);
   }
   modelo.addRow(fila);
   }
    }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    //retornamos modelo para jtable
    modelo.fireTableDataChanged();
    return modelo;
    }

    
    
    

         public void gu(int folio_venta,String nombre ,String descripcion,String tipo,int cantidad ,double total){
             try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO Venta_producto (folio_venta,nombre,descripcion,tipo,cantidad,precio)values ('"+folio_venta+"','"+nombre+"','"+descripcion+"','"+tipo+"','"+cantidad+"','"+total+"')";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
             
         }
        public void el(int id){
             try{
        Statement s=cn.createStatement();
        String query =  "DELETE FROM Venta_producto WHERE id='"+id+"' ";
        s.executeUpdate(query);;
        JOptionPane.showMessageDialog(null, "Descartado");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
             
         }  
         
         
}
