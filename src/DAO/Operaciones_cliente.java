/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class Operaciones_cliente {
            //creamos las variables para la conexion
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo= new DefaultTableModel();
  
    public DefaultTableModel lista(){
        
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from clientes";
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
    
    public void Guardar_c (String nombres, String apellidos, String domicilio, String telefono,String referencia){
         try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO clientes (Nombres,Apellidos,Domicilio,Telefono,Referencias)values ('"+nombres+"','"+apellidos+"','"+domicilio+"','"+telefono+"','"+referencia+"')";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}       
    }
    public void Eliminar_c(int id) {
        try{
        Statement s=cn.createStatement();
        String query =  "DELETE FROM clientes WHERE id='"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ELIMINADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}     
    }
    public void Actualizar_c(int id, String nombres,String apellidos,String domicilio,String telefono,String referencia) {
        try{
        Statement s=cn.createStatement();
        String query =  "UPDATE clientes SET Nombres='"+nombres+"', Apellidos= '"+apellidos+"', Domicilio='"+domicilio+"', Telefono='"+telefono+"',Referencias='"+referencia+"' WHERE id= '"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ACTUALIZADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
    }
    
   public DefaultTableModel lista_datos(String data){   
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from clientes where telefono like '%"+data+"%'";
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
    
    
    
    
    
    
}
