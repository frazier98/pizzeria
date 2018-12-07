/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import DAO.Conexion;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Empresa
 */
public class Generar_reporte {
     static Connection cn;
    public void reporte (int venta_2){
        
        try {
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject("C:\\Users\\Frazier-98\\Documents\\proyecto\\punto2\\proyecto ejmeplo\\proyecto\\proyectobueno\\amalia\\EjemploPanelSlider\\src\\venta_2.jasper");
            Map parametro = new HashMap();
            parametro.put("venta_2", venta_2);
            
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, Conexion.Enlace(cn));
            JasperViewer jv = new JasperViewer(j,false); 
            jv.setTitle("Reporte Empleados");
            jv.setVisible(true);
            
       
        }catch(Exception e){
            System.out.print("error"+e);     
        }
        
        
        
    }

    
    
    
    
    
    
}
