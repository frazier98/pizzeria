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
public class Operaciones_pizza_i {
             //creamos las variables para la conexion
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo= new DefaultTableModel();
  
    public DefaultTableModel lista(){   
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from pizzas";
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
    
    public DefaultTableModel lista_chico(String data){   
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from pizzas where tipo = 'Chico' and nombre like '%"+data+"%'";
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
    
        public DefaultTableModel lista_Mediano(String data){   
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from pizzas where tipo = 'Mediano' and nombre like '%"+data+"%'";
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
        
        public DefaultTableModel lista_Grande(String data){   
    try{
    cn = Conexion.Enlace(cn);
    Statement s= cn.createStatement();
    String query = "select * from pizzas where tipo = 'Grande' and nombre like '%"+data+"%'";
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
    
    
    public void Guardar (String nombre, String descripcion, String tipo, double precio){
         try{
        Statement s=cn.createStatement();
        String query = "INSERT INTO pizzas (Nombre,Descripcion,Tipo,Precio)values ('"+nombre+"','"+descripcion+"','"+tipo+"','"+precio+"')";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "AGREGADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}       
    }
    
    
 

    public void Eliminar(int id) {
        try{
        Statement s=cn.createStatement();
        String query =  "DELETE FROM pizzas WHERE id='"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ELIMINADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}     
    }

    public void Actualizar(int id, String nombre,String descripcion,String tipo,double precio) {
        try{
        Statement s=cn.createStatement();
        String query =  "UPDATE pizzas SET Nombre='"+nombre+"', Descripcion= '"+descripcion+"', Tipo='"+tipo+"', Precio='"+precio+"' WHERE ID= '"+id+"' ";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "ACTUALIZADO");
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
    }
    
}
