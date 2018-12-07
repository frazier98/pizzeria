/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import DAO.Conexion;
import java.sql.Connection;

/**
 *
 * @author Empresa
 */
public class Main {

         
    public static void main(String[] args){
        
        try{
            Generar_reporte g = new Generar_reporte();
            g.reporte(2);    
            
        }catch(Exception e){
            System.out.print("El error fue"+e);
            
        }
        
        
        
        
    }
    
    
    
    
    
    
}
