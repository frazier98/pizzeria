/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Empresa
 */
public class Operaciones_empleado {
         //creamos las variables para la conexion
 static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo= new DefaultTableModel();
  
    public DefaultTableModel lista(){
        
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from empleados";
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
    
    public void Guardar (String nombres, String apellidos, String edad, String direccion,String img){
         try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO empleados (nombres,apellidos,edad,direccion,foto)values ('"+nombres+"','"+apellidos+"','"+edad+"','"+direccion+"','"+img+"')";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}       
    }
    
    
 

    public void Eliminar(int id) {
        try{
        Statement s=cn.createStatement();
        String query =  "DELETE FROM empleados WHERE id='"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ELIMINADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}     
    }

    public void Actualizar(int id, String nombres,String apellidos,String edad,String direccion,String img) {
        try{
        Statement s=cn.createStatement();
        String query =  "UPDATE empleados SET nombres='"+nombres+"', apellidos= '"+apellidos+"', edad='"+edad+"', direccion='"+direccion+"',foto='"+img+"' WHERE id= '"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ACTUALIZADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
    }

    
    
    
}
