/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import AppPackage.AnimationClass;
import DAO.Operaciones_cliente;
import DAO.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static principal.Generar_reporte.cn;
import rojerusan.RSPanelsSlider;

/**
 *
 * @author RojeruSan
 */
public class Principal extends javax.swing.JFrame {

    JFileChooser selector;
File destino;  
File origen;
public String Opcion;
public Image otraimg;
public BufferedImage imagenActual;
public String file;
public String file2;
public PreparedStatement pstmt;
    public static DefaultTableModel modelo2; 
 // conexion cc= new conexion();
    static Connection cn;
    static Statement s;
    static ResultSet rs;
   
    auto_increment a = new auto_increment();
  
    
    /**
     * Creates new form Principal
     */
    public Principal() throws SQLException {
        initComponents();
        DatosTabla();
        DatosTabla1();
        DatosTabla2();
        DatosTabla4();
        DatosTabla3();
        pedidos("","");
        suma(0,0);
        // venta();
//                 tbl_descargar.setModel(new javax.swing.table.DefaultTableModel(
//            new Object [][] {
//                
//            },
//            new String[]{
//                "Nombre", "Descripcion", "Tipo", "Precio", "Total"
//            } 
//        
//        ));
        Calendar cal= Calendar.getInstance();
        String fecha;
        fecha = cal.get(Calendar.DATE)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR);
        lbl_fecha.setText(fecha);
        lbl_folio.setText(Integer.toString(a.id()));
        lbl_vendedor.setText("2");
        txt_id_venta.setVisible(false);
        txt_nombre_venta.setEnabled(false);
        txt_apellido_venta.setEnabled(false);
        txt_direccion_venta.setEnabled(false);
        txt_referencias_venta.setEnabled(false);
        txt_eliminar.setEnabled(false);
        desmostrar();
    }
    

   public void venta(){
       String []datos = new String [1];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT max(id) from empleados");
            while(rs.next()){
                datos[0]=rs.getString(1);    
            }
            lbl_folio.setText(rs.getString(1));
        
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
    public void suma(double num1,double num2){
        double total = num1 * num2;
        lbl_total.setText(String.valueOf(total));
        
    }
    public void DatosTabla(){
    Operaciones_empleado oper = new Operaciones_empleado();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_info.setModel(model);
    }
    public void DatosTabla1(){
    Operaciones_cliente oper = new Operaciones_cliente();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_info1.setModel(model);
    }
    public void DatosTabla2(){
    Operaciones_cliente oper = new Operaciones_cliente();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_info1.setModel(model);
    }
    
    public void DatosTabla3(){
    Operaciones_pizza_i oper = new Operaciones_pizza_i();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_info2.setModel(model);
    }
    public void DatosTabla4(){
    Operacion_ingrediente oper = new Operacion_ingrediente();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_info3.setModel(model);
    }
    public void pedidos (String productos, String data) {     
  if (productos == ""){
     Operaciones_pizza_i oper = new Operaciones_pizza_i();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista();
    tbl_cargar.setModel(model);
    }
   if (productos == "Chico"){
     Operaciones_pizza_i oper = new Operaciones_pizza_i();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista_chico(data);
    tbl_cargar.setModel(model);
    }
     
     if (productos == "Mediano"){
    Operaciones_pizza_i oper = new Operaciones_pizza_i();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista_Mediano(data);
    tbl_cargar.setModel(model);    
    
    }
    if (productos == "Grande"){
     Operaciones_pizza_i oper = new Operaciones_pizza_i();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista_Grande(data);
    tbl_cargar.setModel(model);
    }
  }
       public void guardar()
    {
         try {
        file2=selector.getSelectedFile().getName();//nombre del archivo
         origen = new File(file);
        destino = new File("C:\\Users\\Empresa\\Pictures\\fotos_proyecto\\"+file2);

                        InputStream in = new FileInputStream(origen);
                        OutputStream out = new FileOutputStream(destino);

                        byte[] buf = new byte[1024];
                        int len;

                        while ((len = in.read(buf)) > 0) {
                                out.write(buf, 0, len);
                        }
                        in.close();
                        out.close();        
                String img = String.valueOf(destino);
       
        } catch (Exception e) {
        }
     Operaciones_empleado app = new Operaciones_empleado();       
       String img = String.valueOf(destino);
        app.Guardar(txt_nombres.getText(), txt_apellidos.getText(), txt_edad.getText(), txt_direccion.getText(), img);   
          }   
    public void Guardar_c(){
       Operaciones_cliente app = new Operaciones_cliente();       
        app.Guardar_c(txt_nombres1.getText(), txt_apellidos1.getText(), txt_direccion1.getText(), txt_telefono1.getText(), txt_referencia1.getText());   
       DatosTabla1(); 
       }   
    public void Eliminar(){
        Operaciones_empleado app = new Operaciones_empleado();
        app.Eliminar(Integer.parseInt(txt_id.getText()));
    }
    public void Eliminar_C(){
        Operaciones_cliente app = new Operaciones_cliente();
        app.Eliminar_c(Integer.parseInt(txt_id1.getText()));
    }
    
   public void contador(){
     
    String []datos = new String [1];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select max(id) as id from clientes");
            while(rs.next()){
                datos[0]=rs.getString(1);
      
                lbl_folio.setText(String.valueOf(datos[0]));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
  //  String ultimo = model.toString();
    //int folio1 = 1;
    //int folio2 = Integer.parseInt(ultimo);
    //int folio_real = folio1 + folio2;
    
  //  lbl_folio.setText(String.valueOf(folio_real));
               
}
  
    public void Actualizar_c(){
       Operaciones_cliente app = new Operaciones_cliente();       
        app.Actualizar_c(Integer.parseInt(txt_id1.getText()),txt_nombres1.getText(), txt_apellidos1.getText(), txt_direccion1.getText(), txt_telefono1.getText(), txt_referencia1.getText());   
      }
    public void limpiar_c(){
        txt_id1.setText(null);
        txt_nombres1.setText(null);
        txt_apellidos1.setText(null); 
        txt_telefono1.setText(null);
        txt_direccion1.setText(null);     
        txt_referencia1.setText(null);     
    }
    public void limpiar(){
        txt_id.setText(null);
        txt_nombres.setText(null);
        txt_apellidos.setText(null); 
        txt_edad.setText(null);
        txt_direccion.setText(null);        
    }
    
    public void mostrar(){
        cmb_pedido.setEnabled(true);
        tbl_cargar.setEnabled(true);
        tbl_descargar.setEnabled(true);
        btn_generar.setVisible(true);
        btn_agregar.setVisible(true);
        btn_terminar.setVisible(true);
        btn_Imprimir.setVisible(true);
    }
    public void desmostrar(){
        cmb_pedido.setEnabled(false);
        tbl_cargar.setEnabled(false);
        tbl_descargar.setEnabled(false);
        btn_generar.setVisible(false);
        btn_agregar.setVisible(false);
        btn_terminar.setVisible(false);
         btn_Imprimir.setVisible(false);
    } 
    
    public void productos(int datos){
    Operacion_venta_producto oper = new Operacion_venta_producto();
    DefaultTableModel model =new DefaultTableModel();
    model = oper.lista(datos);
    tbl_descargar.setModel(model);
    }
    
        public BufferedImage abrirImagen(){
          
        //Creamos la variable que será devuelta (la creamos como null)
        BufferedImage bmp=null;
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        selector=new JFileChooser();   
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                  file=selector.getSelectedFile().getPath();//ruta de la imagen
                  } catch (Exception e) {
             }
                  
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imagenActual=bmp;
        //Retornamos el valor
        return bmp;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
        

        
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jblimage2 = new javax.swing.JLabel();
        jblimage1 = new javax.swing.JLabel();
        jFrame1 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btn2 = new rojerusan.RSButtonIconI();
        btn1 = new rojerusan.RSButtonIconI();
        btn4 = new rojerusan.RSButtonIconI();
        btn3 = new rojerusan.RSButtonIconI();
        btn9 = new rojerusan.RSButtonIconI();
        lbl_venta = new javax.swing.JLabel();
        lbl_empleado = new javax.swing.JLabel();
        lbl_cliente = new javax.swing.JLabel();
        lbl_info = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        rSPanelsSlider1 = new rojerusan.RSPanelsSlider();
        pnl5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jblimage4 = new javax.swing.JLabel();
        pnl1 = new javax.swing.JPanel();
        pnl7 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbl_cargar = new javax.swing.JTable();
        txt_buscar = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        lbl_folio = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lbl_vendedor = new javax.swing.JLabel();
        btn_generar = new javax.swing.JButton();
        cmb_pedido = new javax.swing.JComboBox<>();
        lbl_tamaño = new javax.swing.JLabel();
        lbl_tamaño1 = new javax.swing.JLabel();
        lbl_tamaño3 = new javax.swing.JLabel();
        lbl_precio = new javax.swing.JLabel();
        spn_cantidad = new javax.swing.JSpinner();
        lbl_tamaño2 = new javax.swing.JLabel();
        lbl_tamaño5 = new javax.swing.JLabel();
        lbl_tamaño6 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();
        lbl_tamaño4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbl_descargar = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        btn_terminar = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        lbl_fecha = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txt_direccion_venta = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        txt_referencias_venta = new javax.swing.JTextArea();
        jLabel48 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txt_apellido_venta = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        txt_nombre_venta = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        txt_telefono_venta = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        txt_id_venta = new javax.swing.JTextArea();
        lbl_total1 = new javax.swing.JLabel();
        lbl_precio1 = new javax.swing.JLabel();
        lbl_tamaño7 = new javax.swing.JLabel();
        txt_eliminar = new javax.swing.JTextField();
        btn_eliminar = new javax.swing.JToggleButton();
        pnl2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txt_nombres = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_edad = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_direccion = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        lbl_foto = new javax.swing.JLabel();
        btn_examinar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnguardar1 = new javax.swing.JButton();
        btnguardar2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_info = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnl3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txt_nombres1 = new javax.swing.JTextField();
        txt_id1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txt_telefono1 = new javax.swing.JTextField();
        txt_apellidos1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt_referencia1 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_direccion1 = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        lbl_eliminar = new javax.swing.JButton();
        lbl_guardar = new javax.swing.JButton();
        lbl_actualizar = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_info1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnl4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        rSPanelsSlider2 = new rojerusan.RSPanelsSlider();
        pnl_1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txt_id_pizza = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_nombre_pizza = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txt_descripcion_pizza = new javax.swing.JTextArea();
        cmb_tipo = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        txt_precio1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_info2 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        btn_eliminar_pizza_i = new javax.swing.JButton();
        btn_guardar_pizza_i = new javax.swing.JButton();
        btn_actualizar_pizza_i = new javax.swing.JButton();
        pnl_2 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txt_id_ingrediente = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txt_nombre_ingrediente = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        txt_descripcion_ingrediente = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        txt_precio2 = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_info3 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        btn_eliminar_refresco = new javax.swing.JButton();
        btn_guardar_refresco = new javax.swing.JButton();
        btn_actualizar_refrescos = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        btn5 = new rojerusan.RSButtonIconI();
        btn6 = new rojerusan.RSButtonIconI();
        btn7 = new rojerusan.RSButtonIconI();
        btn8 = new rojerusan.RSButtonIconI();
        lbl_pizza = new javax.swing.JLabel();
        lbl_ingrediente = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        btn_ingredientes = new javax.swing.JButton();
        btn_ingredientes1 = new javax.swing.JButton();

        jblimage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pizza800x250.jpg"))); // NOI18N
        jblimage2.setToolTipText("");

        jblimage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4357a154237b6e7.jpg"))); // NOI18N

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 204), new java.awt.Color(0, 102, 204)));
        jPanel2.setForeground(new java.awt.Color(0, 102, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn2.setBackground(new java.awt.Color(255, 255, 255));
        btn2.setText("            PANEL #2");
        btn2.setColorHover(new java.awt.Color(0, 102, 255));
        btn2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        jPanel2.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 0, 10));

        btn1.setBackground(new java.awt.Color(255, 255, 255));
        btn1.setText("            PANEL #1");
        btn1.setColorHover(new java.awt.Color(0, 102, 255));
        btn1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn1.setSelected(true);
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        jPanel2.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 0, 0));

        btn4.setBackground(new java.awt.Color(255, 255, 255));
        btn4.setText("            PANEL #4");
        btn4.setColorHover(new java.awt.Color(0, 102, 255));
        btn4.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
        jPanel2.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 20, 0));

        btn3.setBackground(new java.awt.Color(255, 255, 255));
        btn3.setText("            PANEL #3");
        btn3.setColorHover(new java.awt.Color(0, 102, 255));
        btn3.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        jPanel2.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 20, 0));

        btn9.setBackground(new java.awt.Color(255, 255, 255));
        btn9.setText("            PANEL #1");
        btn9.setColorHover(new java.awt.Color(0, 102, 255));
        btn9.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn9.setSelected(true);
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9ActionPerformed(evt);
            }
        });
        jPanel2.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 0, 10));

        lbl_venta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_venta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cashier-banknote 64x64.png"))); // NOI18N
        lbl_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ventaMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 160, 90));

        lbl_empleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/worker-gear 64x64.png"))); // NOI18N
        lbl_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_empleadoMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 160, 90));

        lbl_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/group_2-edit 64x64.png"))); // NOI18N
        lbl_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_clienteMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 160, 90));

        lbl_info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/application-info 64x64.png"))); // NOI18N
        lbl_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_infoMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 160, 90));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255)));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pizza_30843.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 120));

        rSPanelsSlider1.setBackground(new java.awt.Color(255, 255, 255));

        pnl5.setBackground(new java.awt.Color(255, 255, 255));
        pnl5.setForeground(new java.awt.Color(255, 255, 255));
        pnl5.setName("pnl5"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Verdana", 2, 24)); // NOI18N
        jLabel3.setText("Bienvenido:");

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/action_arrow_left 48x48.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/action_arrow_right 48x48.png"))); // NOI18N
        jButton5.setToolTipText("");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton5MouseMoved(evt);
            }
        });
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });

        jblimage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pizza800x250.jpg"))); // NOI18N
        jblimage4.setToolTipText("");

        javax.swing.GroupLayout pnl5Layout = new javax.swing.GroupLayout(pnl5);
        pnl5.setLayout(pnl5Layout);
        pnl5Layout.setHorizontalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl5Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl5Layout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(541, 541, 541)
                .addComponent(jButton5)
                .addGap(291, 291, 291))
            .addGroup(pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl5Layout.createSequentialGroup()
                    .addContainerGap(68, Short.MAX_VALUE)
                    .addComponent(jblimage4, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(120, Short.MAX_VALUE)))
        );
        pnl5Layout.setVerticalGroup(
            pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl5Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(441, 441, 441)
                .addGroup(pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl5Layout.createSequentialGroup()
                    .addContainerGap(159, Short.MAX_VALUE)
                    .addComponent(jblimage4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(159, Short.MAX_VALUE)))
        );

        rSPanelsSlider1.add(pnl5, "card6");

        pnl1.setBackground(new java.awt.Color(255, 255, 255));
        pnl1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl1.setName("pnl1"); // NOI18N

        pnl7.setBackground(new java.awt.Color(255, 255, 255));
        pnl7.setName("pnl6"); // NOI18N

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pedidos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbl_cargar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_cargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_cargarMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tbl_cargar);

        txt_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("N° Pedido:");

        lbl_folio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_folio.setText("N°Pedido");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Vendedor:");

        lbl_vendedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbl_vendedor.setText("Nombre");

        btn_generar.setText("Generar pizza");
        btn_generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarActionPerformed(evt);
            }
        });

        cmb_pedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grande", "Mediano", "Chico" }));
        cmb_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_pedidoMouseClicked(evt);
            }
        });
        cmb_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pedidoActionPerformed(evt);
            }
        });

        lbl_tamaño.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_tamaño.setText("Tamaño:");

        lbl_tamaño1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_tamaño1.setText("Buscar");

        lbl_tamaño3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tamaño3.setText("$");

        lbl_precio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_precio.setText("0.00");

        spn_cantidad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        spn_cantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spn_cantidadStateChanged(evt);
            }
        });
        spn_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                spn_cantidadKeyReleased(evt);
            }
        });

        lbl_tamaño2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_tamaño2.setText("Cantidad:");

        lbl_tamaño5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tamaño5.setText("x");

        lbl_tamaño6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tamaño6.setText("=");

        lbl_total.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_total.setText("Total");

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_tamaño4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_tamaño4.setText("Precio");

        jButton2.setText("Ingresar Cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Registrar venta");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(lbl_tamaño3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(lbl_tamaño5))
                            .addComponent(lbl_tamaño4))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tamaño2)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(spn_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_tamaño6)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_total)
                                .addGap(43, 43, 43)
                                .addComponent(btn_agregar)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_folio))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_vendedor))
                            .addComponent(lbl_tamaño)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tamaño1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_generar, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(lbl_folio)))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(lbl_vendedor)
                    .addComponent(jButton4))
                .addGap(4, 4, 4)
                .addComponent(lbl_tamaño)
                .addGap(8, 8, 8)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_generar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_tamaño1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tamaño2)
                    .addComponent(lbl_tamaño4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_tamaño3)
                    .addComponent(lbl_precio)
                    .addComponent(spn_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tamaño5)
                    .addComponent(lbl_tamaño6)
                    .addComponent(lbl_total)
                    .addComponent(btn_agregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_descargar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_descargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_descargarMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbl_descargar);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Datos del Pedido:");

        btn_terminar.setText("Finalizar");
        btn_terminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_terminarMouseClicked(evt);
            }
        });
        btn_terminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_terminarActionPerformed(evt);
            }
        });

        btn_Imprimir.setText("Imprimir");
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel40.setText("Fecha:");

        lbl_fecha.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbl_fecha.setText("00:00:00");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel43.setText("Nombre:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Direccion:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel46.setText("Referencias:");

        txt_direccion_venta.setColumns(20);
        txt_direccion_venta.setRows(5);
        jScrollPane10.setViewportView(txt_direccion_venta);

        txt_referencias_venta.setColumns(20);
        txt_referencias_venta.setRows(5);
        jScrollPane11.setViewportView(txt_referencias_venta);

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel48.setText("Telefono:");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Apellidos:");

        txt_apellido_venta.setColumns(20);
        txt_apellido_venta.setRows(5);
        jScrollPane14.setViewportView(txt_apellido_venta);

        txt_nombre_venta.setColumns(20);
        txt_nombre_venta.setRows(5);
        jScrollPane15.setViewportView(txt_nombre_venta);

        txt_telefono_venta.setColumns(20);
        txt_telefono_venta.setRows(5);
        jScrollPane16.setViewportView(txt_telefono_venta);

        txt_id_venta.setColumns(20);
        txt_id_venta.setRows(5);
        jScrollPane17.setViewportView(txt_id_venta);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel43)
                                        .addComponent(jLabel47))
                                    .addComponent(jLabel48))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(204, 204, 204)
                                .addComponent(jLabel46)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_fecha)
                        .addGap(378, 378, 378)
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(lbl_fecha)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48))
                .addGap(7, 7, 7)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lbl_total1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_total1.setText("Total: ");

        lbl_precio1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_precio1.setText("0.00");

        lbl_tamaño7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_tamaño7.setText("$");

        btn_eliminar.setText("Descartar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl7Layout = new javax.swing.GroupLayout(pnl7);
        pnl7.setLayout(pnl7Layout);
        pnl7Layout.setHorizontalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane13)
                    .addGroup(pnl7Layout.createSequentialGroup()
                        .addComponent(btn_terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Imprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_eliminar)
                        .addContainerGap())
                    .addGroup(pnl7Layout.createSequentialGroup()
                        .addGroup(pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl7Layout.createSequentialGroup()
                                .addComponent(lbl_total1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_tamaño7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_precio1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel44))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnl7Layout.setVerticalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl7Layout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(129, 129, 129))
                    .addGroup(pnl7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_terminar)
                            .addComponent(btn_Imprimir)
                            .addComponent(txt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_total1)
                            .addComponent(lbl_tamaño7)
                            .addComponent(lbl_precio1))
                        .addGap(144, 144, 144))))
        );

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addComponent(pnl7, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(pnl1, "card2");

        pnl2.setBackground(new java.awt.Color(255, 255, 255));
        pnl2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255)));
        pnl2.setName("pnl2"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de Empleado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        txt_nombres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombresKeyTyped(evt);
            }
        });

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nombre (s):");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Direccion:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Edad:");

        txt_edad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_edad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_edadKeyTyped(evt);
            }
        });

        txt_apellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_apellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidosKeyTyped(evt);
            }
        });

        txt_direccion.setColumns(20);
        txt_direccion.setRows(5);
        jScrollPane2.setViewportView(txt_direccion);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("N° Empleado:");

        lbl_foto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_foto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/worker-gear 64x64.png"))); // NOI18N

        btn_examinar.setText("Examinar");
        btn_examinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_examinarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel2)
                        .addComponent(jLabel8))
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                        .addComponent(txt_edad)
                        .addComponent(txt_apellidos)
                        .addComponent(txt_nombres)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_examinar, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(lbl_foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_edad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(lbl_foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_examinar)
                .addGap(36, 36, 36))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 32x32.png"))); // NOI18N
        btnguardar.setToolTipText("");
        btnguardar.setBorder(null);
        btnguardar.setBorderPainted(false);
        btnguardar.setContentAreaFilled(false);
        btnguardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 48x48.png"))); // NOI18N
        btnguardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 48x48.png"))); // NOI18N
        btnguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardarMouseClicked(evt);
            }
        });
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 32x32.png"))); // NOI18N
        btnguardar1.setToolTipText("");
        btnguardar1.setBorder(null);
        btnguardar1.setBorderPainted(false);
        btnguardar1.setContentAreaFilled(false);
        btnguardar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 48x48.png"))); // NOI18N
        btnguardar1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 48x48.png"))); // NOI18N
        btnguardar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardar1MouseClicked(evt);
            }
        });
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });

        btnguardar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 32x32.png"))); // NOI18N
        btnguardar2.setToolTipText("");
        btnguardar2.setBorder(null);
        btnguardar2.setBorderPainted(false);
        btnguardar2.setContentAreaFilled(false);
        btnguardar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 48x48.png"))); // NOI18N
        btnguardar2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 48x48.png"))); // NOI18N
        btnguardar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardar2MouseClicked(evt);
            }
        });
        btnguardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        tbl_info.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_infoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_info);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 102, 204));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/minimizar.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(895, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(28, 28, 28)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(pnl2, "card3");

        pnl3.setBackground(new java.awt.Color(255, 255, 255));
        pnl3.setName("pnl3"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(0, 102, 204));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/minimizar.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(28, 28, 28)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles del Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        txt_nombres1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombres1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombres1KeyTyped(evt);
            }
        });

        txt_id1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id1ActionPerformed(evt);
            }
        });
        txt_id1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_id1KeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Nombre (s):");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Apellidos:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Direccion:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Telefono/Celular:");

        txt_telefono1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telefono1KeyTyped(evt);
            }
        });

        txt_apellidos1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_apellidos1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_apellidos1KeyTyped(evt);
            }
        });

        txt_referencia1.setColumns(20);
        txt_referencia1.setRows(5);
        jScrollPane3.setViewportView(txt_referencia1);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("N° Cliente:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Referencias:");

        txt_direccion1.setColumns(20);
        txt_direccion1.setRows(5);
        jScrollPane4.setViewportView(txt_direccion1);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_apellidos1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                        .addComponent(txt_id1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_nombres1, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txt_telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nombres1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_apellidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txt_telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        lbl_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 32x32.png"))); // NOI18N
        lbl_eliminar.setToolTipText("");
        lbl_eliminar.setBorder(null);
        lbl_eliminar.setBorderPainted(false);
        lbl_eliminar.setContentAreaFilled(false);
        lbl_eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 48x48.png"))); // NOI18N
        lbl_eliminar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-delete 48x48.png"))); // NOI18N
        lbl_eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_eliminarMouseClicked(evt);
            }
        });
        lbl_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_eliminarActionPerformed(evt);
            }
        });

        lbl_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 32x32.png"))); // NOI18N
        lbl_guardar.setToolTipText("");
        lbl_guardar.setBorder(null);
        lbl_guardar.setBorderPainted(false);
        lbl_guardar.setContentAreaFilled(false);
        lbl_guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 48x48.png"))); // NOI18N
        lbl_guardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-save 48x48.png"))); // NOI18N
        lbl_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_guardarMouseClicked(evt);
            }
        });
        lbl_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_guardarActionPerformed(evt);
            }
        });

        lbl_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 32x32.png"))); // NOI18N
        lbl_actualizar.setToolTipText("");
        lbl_actualizar.setBorder(null);
        lbl_actualizar.setBorderPainted(false);
        lbl_actualizar.setContentAreaFilled(false);
        lbl_actualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 48x48.png"))); // NOI18N
        lbl_actualizar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_symbol_blue-update 48x48.png"))); // NOI18N
        lbl_actualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_actualizarMouseClicked(evt);
            }
        });
        lbl_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_actualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(lbl_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbl_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        tbl_info1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbl_info1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_info1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_info1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_info1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 937, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Buscar:");

        txtbuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/view_zoom-add 32x32.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl3Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        rSPanelsSlider1.add(pnl3, "card4");

        pnl4.setBackground(new java.awt.Color(255, 255, 255));
        pnl4.setName("pnl4"); // NOI18N

        jPanel12.setBackground(new java.awt.Color(0, 102, 204));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/minimizar.png"))); // NOI18N
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar.png"))); // NOI18N
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(28, 28, 28)
                .addComponent(jLabel23)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        rSPanelsSlider2.setBackground(new java.awt.Color(255, 255, 255));

        pnl_1.setBackground(new java.awt.Color(255, 255, 255));
        pnl_1.setName("pnl_1"); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pizzas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txt_id_pizza.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID PIZZA:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Nombre de Pizza:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Descripcion:");

        txt_nombre_pizza.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_descripcion_pizza.setColumns(20);
        txt_descripcion_pizza.setRows(5);
        jScrollPane7.setViewportView(txt_descripcion_pizza);

        cmb_tipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmb_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chico", "Mediano", "Grande", " ", " " }));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Precio:");

        txt_precio1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_id_pizza, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(txt_nombre_pizza))
                                .addGroup(jPanel14Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel27)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txt_precio1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_id_pizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_pizza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_precio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbl_info2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_info2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_info2MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_info2);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btn_eliminar_pizza_i.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 32x32.png"))); // NOI18N
        btn_eliminar_pizza_i.setBorder(null);
        btn_eliminar_pizza_i.setBorderPainted(false);
        btn_eliminar_pizza_i.setContentAreaFilled(false);
        btn_eliminar_pizza_i.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 48x48.png"))); // NOI18N
        btn_eliminar_pizza_i.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 48x48.png"))); // NOI18N
        btn_eliminar_pizza_i.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_pizza_iMouseClicked(evt);
            }
        });

        btn_guardar_pizza_i.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 32x32.png"))); // NOI18N
        btn_guardar_pizza_i.setBorder(null);
        btn_guardar_pizza_i.setBorderPainted(false);
        btn_guardar_pizza_i.setContentAreaFilled(false);
        btn_guardar_pizza_i.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 48x48.png"))); // NOI18N
        btn_guardar_pizza_i.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 48x48.png"))); // NOI18N
        btn_guardar_pizza_i.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardar_pizza_iMouseClicked(evt);
            }
        });
        btn_guardar_pizza_i.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_pizza_iActionPerformed(evt);
            }
        });

        btn_actualizar_pizza_i.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 32x32.png"))); // NOI18N
        btn_actualizar_pizza_i.setBorder(null);
        btn_actualizar_pizza_i.setBorderPainted(false);
        btn_actualizar_pizza_i.setContentAreaFilled(false);
        btn_actualizar_pizza_i.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 48x48.png"))); // NOI18N
        btn_actualizar_pizza_i.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 48x48.png"))); // NOI18N
        btn_actualizar_pizza_i.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_actualizar_pizza_iMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_eliminar_pizza_i, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_pizza_i, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar_pizza_i, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_guardar_pizza_i, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_pizza_i, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_eliminar_pizza_i, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_1Layout = new javax.swing.GroupLayout(pnl_1);
        pnl_1.setLayout(pnl_1Layout);
        pnl_1Layout.setHorizontalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnl_1Layout.setVerticalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_1Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_1Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );

        rSPanelsSlider2.add(pnl_1, "card2");

        pnl_2.setBackground(new java.awt.Color(255, 255, 255));
        pnl_2.setName("pnl_2"); // NOI18N

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingredientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txt_id_ingrediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel30.setText("ID Ingredienetes");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Nombre del Ingrediente");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Descripcion:");

        txt_nombre_ingrediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_descripcion_ingrediente.setColumns(20);
        txt_descripcion_ingrediente.setRows(5);
        jScrollPane8.setViewportView(txt_descripcion_ingrediente);

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel33.setText("Precio:");

        txt_precio2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id_ingrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txt_nombre_ingrediente))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel31)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_precio2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txt_id_ingrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nombre_ingrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_precio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        tbl_info3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_info3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_info3MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_info3);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        btn_eliminar_refresco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 32x32.png"))); // NOI18N
        btn_eliminar_refresco.setBorder(null);
        btn_eliminar_refresco.setBorderPainted(false);
        btn_eliminar_refresco.setContentAreaFilled(false);
        btn_eliminar_refresco.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 48x48.png"))); // NOI18N
        btn_eliminar_refresco.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/delete_button_error 48x48.png"))); // NOI18N
        btn_eliminar_refresco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_eliminar_refrescoMouseClicked(evt);
            }
        });
        btn_eliminar_refresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_refrescoActionPerformed(evt);
            }
        });

        btn_guardar_refresco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 32x32.png"))); // NOI18N
        btn_guardar_refresco.setBorder(null);
        btn_guardar_refresco.setBorderPainted(false);
        btn_guardar_refresco.setContentAreaFilled(false);
        btn_guardar_refresco.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 48x48.png"))); // NOI18N
        btn_guardar_refresco.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save-edit 48x48.png"))); // NOI18N
        btn_guardar_refresco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardar_refrescoMouseClicked(evt);
            }
        });
        btn_guardar_refresco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_refrescoActionPerformed(evt);
            }
        });

        btn_actualizar_refrescos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 32x32.png"))); // NOI18N
        btn_actualizar_refrescos.setBorder(null);
        btn_actualizar_refrescos.setBorderPainted(false);
        btn_actualizar_refrescos.setContentAreaFilled(false);
        btn_actualizar_refrescos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 48x48.png"))); // NOI18N
        btn_actualizar_refrescos.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/refresh_update 48x48.png"))); // NOI18N
        btn_actualizar_refrescos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_actualizar_refrescosMouseClicked(evt);
            }
        });
        btn_actualizar_refrescos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_refrescosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_eliminar_refresco, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(btn_actualizar_refrescos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar_refresco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_guardar_refresco, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_actualizar_refrescos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_eliminar_refresco, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnl_2Layout = new javax.swing.GroupLayout(pnl_2);
        pnl_2.setLayout(pnl_2Layout);
        pnl_2Layout.setHorizontalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pnl_2Layout.setVerticalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_2Layout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_2Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
        );

        rSPanelsSlider2.add(pnl_2, "card3");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 204), new java.awt.Color(0, 102, 204)));
        jPanel13.setForeground(new java.awt.Color(0, 102, 204));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn5.setBackground(new java.awt.Color(255, 255, 255));
        btn5.setText("            PANEL #2");
        btn5.setColorHover(new java.awt.Color(0, 102, 255));
        btn5.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel13.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 0, 10));

        btn6.setBackground(new java.awt.Color(255, 255, 255));
        btn6.setText("            PANEL #1");
        btn6.setColorHover(new java.awt.Color(0, 102, 255));
        btn6.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn6.setSelected(true);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });
        jPanel13.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 10, 0));

        btn7.setBackground(new java.awt.Color(255, 255, 255));
        btn7.setText("            PANEL #4");
        btn7.setColorHover(new java.awt.Color(0, 102, 255));
        btn7.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn7ActionPerformed(evt);
            }
        });
        jPanel13.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 20, 0));

        btn8.setBackground(new java.awt.Color(255, 255, 255));
        btn8.setText("            PANEL #3");
        btn8.setColorHover(new java.awt.Color(0, 102, 255));
        btn8.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn8ActionPerformed(evt);
            }
        });
        jPanel13.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 570, 20, 0));

        lbl_pizza.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_pizza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pizza-icon-256_icon-icons.com_75224.png"))); // NOI18N
        lbl_pizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_pizzaMouseClicked(evt);
            }
        });
        jPanel13.add(lbl_pizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 160, 90));

        lbl_ingrediente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_ingrediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pizza_icon-icons.com_68706 (1).png"))); // NOI18N
        lbl_ingrediente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_ingredienteMouseClicked(evt);
            }
        });
        jPanel13.add(lbl_ingrediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 160, 90));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Pizzas");
        jPanel13.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Ingredientes");
        jPanel13.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, -1, -1));

        btn_ingredientes.setBackground(new java.awt.Color(255, 255, 255));
        btn_ingredientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_ingredientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pizza-icon-256_icon-icons.com_75224.png"))); // NOI18N
        btn_ingredientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_ingredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingredientesActionPerformed(evt);
            }
        });
        jPanel13.add(btn_ingredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 120, 100));

        btn_ingredientes1.setBackground(new java.awt.Color(255, 255, 255));
        btn_ingredientes1.setForeground(new java.awt.Color(255, 255, 255));
        btn_ingredientes1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pizza_icon-icons.com_68706 (1).png"))); // NOI18N
        btn_ingredientes1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_ingredientes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ingredientes1ActionPerformed(evt);
            }
        });
        jPanel13.add(btn_ingredientes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 120, 100));

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 951, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelsSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rSPanelsSlider1.add(pnl4, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(rSPanelsSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombresKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombresKeyTyped

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKeyPressed

    private void txt_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_idKeyTyped

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void btnguardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardar2ActionPerformed

    private void txt_edadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_edadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edadKeyTyped

    private void txt_apellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidosKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidosKeyTyped

    private void btnguardar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardar1MouseClicked
    guardar();
    DatosTabla();
    limpiar(); 
    }//GEN-LAST:event_btnguardar1MouseClicked

    private void btn_examinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_examinarActionPerformed
        lbl_foto.setText("");//limpia el label, elimina la palabra foto
       lbl_foto.setIcon(new ImageIcon(abrirImagen().getScaledInstance(230, 240, Image.SCALE_SMOOTH)));//llama al metodo de la imagen y redimenciona el tamaño    
       
    }//GEN-LAST:event_btn_examinarActionPerformed

    private void tbl_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_infoMouseClicked
       int rec = this.tbl_info.getSelectedRow();
        this.txt_id.setText(tbl_info.getValueAt(rec, 0).toString());
        this.txt_nombres.setText(tbl_info.getValueAt(rec, 1).toString());
        this.txt_apellidos.setText(tbl_info.getValueAt(rec, 2).toString());
        this.txt_edad.setText(tbl_info.getValueAt(rec, 3).toString());
        this.txt_direccion.setText(tbl_info.getValueAt(rec, 4).toString());
        String ImagenRecibida=tbl_info.getValueAt(tbl_info.getSelectedRow(),5).toString();
        ImageIcon icono = new ImageIcon(ImagenRecibida);//lo convertimos a icono
        Image imagencontacto = icono.getImage().getScaledInstance(230, 240, Image.SCALE_SMOOTH);//lo convertimos a imagen para redimencionar
        ImageIcon otroicono = new ImageIcon(imagencontacto);//lo pasamos ahora con el tamaño y lo convertimos a icono
        lbl_foto.setText("");//limpiamos el texto
        lbl_foto.setIcon(otroicono);//manda la ruta para mostrar la imagen           // TODO add your handling code here:
    }//GEN-LAST:event_tbl_infoMouseClicked

    private void btnguardar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardar2MouseClicked
             // Actualizar();
 DatosTabla();
    limpiar(); 
    }//GEN-LAST:event_btnguardar2MouseClicked

    private void btnguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseClicked
             Eliminar();
            DatosTabla();
             limpiar(); 
    }//GEN-LAST:event_btnguardarMouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        this.setState(Principal.ICONIFIED);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked

        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Desea cerrar el Programa?", "Exit", dialog);
        if(result == 0)
        {
            System.exit(0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked

        AnimationClass ac =new AnimationClass();
        ac.jLabelXLeft(0, -1000, 25, 5, jblimage4);

        AnimationClass acc =new AnimationClass();
  //      acc.jLabelXLeft(1000, 0, 25, 5, jblimage3);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5MouseMoved

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        AnimationClass ac =new AnimationClass();
        ac.jLabelXRight(-1000, 0, 25, 5, jblimage4);

        AnimationClass acc =new AnimationClass();
//        acc.jLabelXRight(0, 1000, 25, 5, jblimage3);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3MouseClicked

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void tbl_info1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_info1MouseClicked
       int rec = this.tbl_info1.getSelectedRow();
        this.txt_id1.setText(tbl_info1.getValueAt(rec, 0).toString());
        this.txt_nombres1.setText(tbl_info1.getValueAt(rec, 1).toString());
        this.txt_apellidos1.setText(tbl_info1.getValueAt(rec, 2).toString());
        this.txt_direccion1.setText(tbl_info1.getValueAt(rec, 3).toString());
        this.txt_telefono1.setText(tbl_info1.getValueAt(rec, 4).toString());
        this.txt_referencia1.setText(tbl_info1.getValueAt(rec, 5).toString());
    }//GEN-LAST:event_tbl_info1MouseClicked

    private void lbl_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_actualizarActionPerformed
        
        
        
        
        
    }//GEN-LAST:event_lbl_actualizarActionPerformed

    private void lbl_actualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_actualizarMouseClicked

        Actualizar_c();
        limpiar_c();
      DatosTabla1(); 
    }//GEN-LAST:event_lbl_actualizarMouseClicked

    private void lbl_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_guardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_guardarActionPerformed

    private void lbl_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_guardarMouseClicked
      Guardar_c();     
      limpiar_c();
       DatosTabla1();

    }//GEN-LAST:event_lbl_guardarMouseClicked

    private void lbl_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_eliminarActionPerformed

    private void lbl_eliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_eliminarMouseClicked
       Eliminar_C();
       limpiar_c();
       DatosTabla1();
    }//GEN-LAST:event_lbl_eliminarMouseClicked

    private void txt_apellidos1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_apellidos1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidos1KeyTyped

    private void txt_telefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telefono1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefono1KeyTyped

    private void txt_id1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id1KeyTyped

    private void txt_id1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id1KeyPressed

    private void txt_id1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id1ActionPerformed

    private void txt_nombres1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombres1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombres1KeyTyped

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        //mostrardatos_c(txtbuscar.getText());
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //mostrardatos_c("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

    private void lbl_infoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_infoMouseClicked
        if(!this.btn4.isSelected()){
//            this.btn1.setSelected(false);
//            this.btn2.setSelected(false);
//            this.btn3.setSelected(false);
//            this.btn4.setSelected(true);

            rSPanelsSlider1.slidPanel(20, pnl4, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_infoMouseClicked

    private void lbl_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clienteMouseClicked
        if(!this.btn3.isSelected()){
//            this.btn1.setSelected(false);
//            this.btn2.setSelected(false);
//            this.btn3.setSelected(true);
//            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl3, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_clienteMouseClicked

    private void lbl_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_empleadoMouseClicked
        if(!this.btn2.isSelected()){
//            this.btn1.setSelected(false);
//            this.btn2.setSelected(true);
//            this.btn3.setSelected(false);
//            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl1, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_empleadoMouseClicked

    private void lbl_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ventaMouseClicked
        if(!this.btn1.isSelected()){
//            this.btn1.setSelected(true);
//            this.btn2.setSelected(false);
//            this.btn3.setSelected(false);
//            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl1, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_ventaMouseClicked

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if(!this.btn3.isSelected()){
            this.btn1.setSelected(false);
            this.btn2.setSelected(false);
            this.btn3.setSelected(true);
            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl3, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if(!this.btn4.isSelected()){
            this.btn1.setSelected(false);
            this.btn2.setSelected(false);
            this.btn3.setSelected(false);
            this.btn4.setSelected(true);

            rSPanelsSlider1.slidPanel(20, pnl4, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if(!this.btn1.isSelected()){
            this.btn1.setSelected(true);
            this.btn2.setSelected(false);
            this.btn3.setSelected(false);
            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl1, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if(!this.btn2.isSelected()){
            this.btn1.setSelected(false);
            this.btn2.setSelected(true);
            this.btn3.setSelected(false);
            this.btn4.setSelected(false);

            rSPanelsSlider1.slidPanel(20, pnl2, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn_guardar_pizza_iMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardar_pizza_iMouseClicked
        Operaciones_pizza_i app = new Operaciones_pizza_i();       
        app.Guardar(txt_nombre_pizza.getText(), txt_descripcion_pizza.getText(), cmb_tipo.getSelectedItem().toString(), Double.parseDouble(txt_precio1.getText()));   
        //mostrardatos_pizza("");
        DatosTabla3();
    }//GEN-LAST:event_btn_guardar_pizza_iMouseClicked

    private void btn_guardar_pizza_iActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_pizza_iActionPerformed
      
    }//GEN-LAST:event_btn_guardar_pizza_iActionPerformed

    private void btn_eliminar_pizza_iMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_pizza_iMouseClicked
       Operaciones_pizza_i app = new Operaciones_pizza_i();
       app.Eliminar(Integer.parseInt(txt_id_pizza.getText()));
       //mostrardatos_pizza("");
            DatosTabla3();
    }//GEN-LAST:event_btn_eliminar_pizza_iMouseClicked

    private void btn_actualizar_pizza_iMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actualizar_pizza_iMouseClicked
       Operaciones_pizza_i app = new Operaciones_pizza_i();       
        app.Actualizar(Integer.parseInt(txt_id_pizza.getText()),txt_nombre_pizza.getText(), txt_descripcion_pizza.getText(), cmb_tipo.getSelectedItem().toString(), Double.parseDouble(txt_precio1.getText()));   
         // mostrardatos_pizza("");
             DatosTabla3();
    }//GEN-LAST:event_btn_actualizar_pizza_iMouseClicked

    private void tbl_info2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_info2MouseClicked
       int rec = this.tbl_info2.getSelectedRow();
        this.txt_id_pizza.setText(tbl_info2.getValueAt(rec, 0).toString());
        this.txt_nombre_pizza.setText(tbl_info2.getValueAt(rec, 1).toString());
        this.txt_descripcion_pizza.setText(tbl_info2.getValueAt(rec, 2).toString());
        this.cmb_tipo.setSelectedItem(tbl_info2.getValueAt(rec, 3).toString());
        this.txt_precio1.setText(tbl_info2.getValueAt(rec, 4).toString());
       
    }//GEN-LAST:event_tbl_info2MouseClicked

    private void tbl_info3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_info3MouseClicked
        int rec = this.tbl_info3.getSelectedRow();
        this.txt_id_ingrediente.setText(tbl_info3.getValueAt(rec, 0).toString());
        this.txt_nombre_ingrediente.setText(tbl_info3.getValueAt(rec, 1).toString());
        this.txt_descripcion_ingrediente.setText(tbl_info3.getValueAt(rec, 2).toString());
        this.txt_precio2.setText(tbl_info3.getValueAt(rec, 3).toString());
    }//GEN-LAST:event_tbl_info3MouseClicked

    private void btn_eliminar_refrescoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_eliminar_refrescoMouseClicked
      Operacion_ingrediente app = new Operacion_ingrediente();       
        app.Eliminar(Integer.parseInt(txt_id_ingrediente.getText()));   
      DatosTabla4();
    }//GEN-LAST:event_btn_eliminar_refrescoMouseClicked

    private void btn_guardar_refrescoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardar_refrescoMouseClicked
                Operacion_ingrediente app = new Operacion_ingrediente();       
        app.Guardar(txt_nombre_ingrediente.getText(), txt_descripcion_ingrediente.getText(), Double.parseDouble(txt_precio2.getText()));   
        //mostrardatos_refresco("");  
    }//GEN-LAST:event_btn_guardar_refrescoMouseClicked

    private void btn_guardar_refrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_refrescoActionPerformed
        
        Operacion_ingrediente app = new Operacion_ingrediente();       
        app.Guardar(txt_nombre_ingrediente.getText(), txt_descripcion_ingrediente.getText(), Double.parseDouble(txt_precio2.getText()));   
        //mostrardatos_refresco("");  
          DatosTabla4();
        
        
    }//GEN-LAST:event_btn_guardar_refrescoActionPerformed

    private void btn_actualizar_refrescosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_actualizar_refrescosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_actualizar_refrescosMouseClicked

    private void btn_actualizar_refrescosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_refrescosActionPerformed
         Operacion_ingrediente app = new Operacion_ingrediente();       
        app.Actualizar(Integer.parseInt(txt_id_ingrediente.getText()),txt_nombre_pizza.getText(), txt_descripcion_pizza.getText(), Double.parseDouble(txt_precio1.getText()));   
          //mostrardatos_refresco
            DatosTabla4();
    }//GEN-LAST:event_btn_actualizar_refrescosActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn9ActionPerformed

    private void btn_generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_generarActionPerformed

    private void cmb_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pedidoActionPerformed
          pedidos(cmb_pedido.getSelectedItem().toString(),txt_buscar.getText());
    }//GEN-LAST:event_cmb_pedidoActionPerformed

    private void cmb_pedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_pedidoMouseClicked
            pedidos(cmb_pedido.getSelectedItem().toString(),txt_buscar.getText());
    }//GEN-LAST:event_cmb_pedidoMouseClicked

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
         pedidos(cmb_pedido.getSelectedItem().toString(),txt_buscar.getText());
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void tbl_cargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_cargarMouseClicked
        int rec = this.tbl_cargar.getSelectedRow();
        this.lbl_precio.setText(tbl_cargar.getValueAt(rec, 4).toString());
        suma(Double.parseDouble(lbl_precio.getText()),Double.parseDouble(spn_cantidad.getValue().toString()));
        
    }//GEN-LAST:event_tbl_cargarMouseClicked

    public void sumar(){
        double fila = 0;
        double total = 0 ;
        for (int i = 0; i <tbl_descargar.getRowCount(); i++){
        fila = Double.parseDouble(tbl_descargar.getValueAt(i,6).toString());
        total += fila;
    }
        lbl_precio1.setText(""+total);
    }
    
    
    
    private void spn_cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spn_cantidadKeyReleased
     suma(Double.parseDouble(lbl_precio.getText()),Double.parseDouble(spn_cantidad.getToolTipText()));
    }//GEN-LAST:event_spn_cantidadKeyReleased

    private void spn_cantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spn_cantidadStateChanged
       //   lbl_total.setText(spn_cantidad.getValue().toString());
       suma(Double.parseDouble(lbl_precio.getText()),Double.parseDouble(spn_cantidad.getValue().toString()));
    }//GEN-LAST:event_spn_cantidadStateChanged

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
     
       int rec = this.tbl_cargar.getSelectedRow();
//        DefaultTableModel modelo=(DefaultTableModel) tbl_descargar.getModel();
//          Object[] fila=new Object[6];
//           fila[0]=lbl_folio.getText();
//           fila[1]=tbl_cargar.getValueAt(rec, 1).toString();
//           fila[2]=tbl_cargar.getValueAt(rec, 3).toString();
//           fila[3]=tbl_cargar.getValueAt(rec, 4).toString();
//           fila[4]=spn_cantidad.getValue().toString();
//           fila[5]=lbl_total.getText();
//         
//          modelo.addRow(fila); 
         // lbl_total.setText("0.00");
          //spn_cantidad.setValue();
          Operacion_venta_producto o = new Operacion_venta_producto();
          o.gu(Integer.parseInt(lbl_folio.getText()), tbl_cargar.getValueAt(rec, 1).toString(), tbl_cargar.getValueAt(rec, 2).toString(), tbl_cargar.getValueAt(rec, 3).toString(), Integer.parseInt(spn_cantidad.getValue().toString()),Double.parseDouble(lbl_total.getText()));
          productos(Integer.parseInt(lbl_folio.getText()));
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void lbl_pizzaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_pizzaMouseClicked
        if(!this.btn6.isSelected()){
            this.btn8.setSelected(false);
            this.btn7.setSelected(false);
            this.btn5.setSelected(false);
            this.btn6.setSelected(true);

            rSPanelsSlider2.slidPanel(20, pnl_2, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_pizzaMouseClicked

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed

    private void lbl_ingredienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_ingredienteMouseClicked
        if(!this.btn7.isSelected()){
            this.btn8.setSelected(false);
            this.btn5.setSelected(false);
            this.btn5.setSelected(false);
            this.btn7.setSelected(true);

            rSPanelsSlider2.slidPanel(20, pnl_1, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_lbl_ingredienteMouseClicked

    private void btn_ingredientes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingredientes1ActionPerformed
               if(!this.btn_ingredientes1.isSelected()){
//            this.btn8.setSelected(false);
//            this.btn5.setSelected(false);
//            this.btn5.setSelected(false);
//            this.btn_ingredientes1.setSelected(true);

            rSPanelsSlider2.slidPanel(20, pnl_2, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn_ingredientes1ActionPerformed

    private void btn_ingredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ingredientesActionPerformed
                    if(!this.btn_ingredientes.isSelected()){
//            this.btn8.setSelected(false);
//            this.btn5.setSelected(false);
//            this.btn5.setSelected(false);
//            this.btn_ingredientes.setSelected(true);

            rSPanelsSlider2.slidPanel(20, pnl_1, RSPanelsSlider.direct.Right);
        }
    }//GEN-LAST:event_btn_ingredientesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clientes app = new clientes();

        app.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_eliminar_refrescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_refrescoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminar_refrescoActionPerformed

    private void btn_terminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_terminarActionPerformed
sumar();        // TODO add your handling code here:
    }//GEN-LAST:event_btn_terminarActionPerformed

    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed

       Generar_reporte t = new Generar_reporte();
        t.reporte(Integer.parseInt(lbl_folio.getText()));
       
        
        
    }//GEN-LAST:event_btn_ImprimirActionPerformed

    private void btn_terminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_terminarMouseClicked

    }//GEN-LAST:event_btn_terminarMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      Venta t = new Venta();
      t.Guardar_VG(Integer.parseInt(lbl_folio.getText()), Integer.parseInt(txt_id_venta.getText()), Integer.parseInt(lbl_vendedor.getText()), lbl_fecha.getText(),Double.parseDouble(lbl_precio1.getText()) );
      mostrar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tbl_descargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_descargarMouseClicked
          int rec = this.tbl_descargar.getSelectedRow();
        this.txt_eliminar.setText(tbl_descargar.getValueAt(rec, 0).toString());        // TODO add your handling code here:
        sumar();
        
    }//GEN-LAST:event_tbl_descargarMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
       Operacion_venta_producto n = new Operacion_venta_producto();
       n.el(Integer.parseInt(txt_eliminar.getText()));
       
    }//GEN-LAST:event_btn_eliminarActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSButtonIconI btn1;
    private rojerusan.RSButtonIconI btn2;
    private rojerusan.RSButtonIconI btn3;
    private rojerusan.RSButtonIconI btn4;
    private rojerusan.RSButtonIconI btn5;
    private rojerusan.RSButtonIconI btn6;
    private rojerusan.RSButtonIconI btn7;
    private rojerusan.RSButtonIconI btn8;
    private rojerusan.RSButtonIconI btn9;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.JButton btn_actualizar_pizza_i;
    private javax.swing.JButton btn_actualizar_refrescos;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JToggleButton btn_eliminar;
    private javax.swing.JButton btn_eliminar_pizza_i;
    private javax.swing.JButton btn_eliminar_refresco;
    private javax.swing.JButton btn_examinar;
    private javax.swing.JButton btn_generar;
    private javax.swing.JButton btn_guardar_pizza_i;
    private javax.swing.JButton btn_guardar_refresco;
    private javax.swing.JButton btn_ingredientes;
    private javax.swing.JButton btn_ingredientes1;
    private javax.swing.JButton btn_terminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnguardar2;
    private javax.swing.JComboBox<String> cmb_pedido;
    private javax.swing.JComboBox<String> cmb_tipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel jblimage1;
    private javax.swing.JLabel jblimage2;
    private javax.swing.JLabel jblimage4;
    private javax.swing.JButton lbl_actualizar;
    private javax.swing.JLabel lbl_cliente;
    private javax.swing.JButton lbl_eliminar;
    private javax.swing.JLabel lbl_empleado;
    private javax.swing.JLabel lbl_fecha;
    private javax.swing.JLabel lbl_folio;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JButton lbl_guardar;
    private javax.swing.JLabel lbl_info;
    private javax.swing.JLabel lbl_ingrediente;
    private javax.swing.JLabel lbl_pizza;
    private javax.swing.JLabel lbl_precio;
    private javax.swing.JLabel lbl_precio1;
    private javax.swing.JLabel lbl_tamaño;
    private javax.swing.JLabel lbl_tamaño1;
    private javax.swing.JLabel lbl_tamaño2;
    private javax.swing.JLabel lbl_tamaño3;
    private javax.swing.JLabel lbl_tamaño4;
    private javax.swing.JLabel lbl_tamaño5;
    private javax.swing.JLabel lbl_tamaño6;
    private javax.swing.JLabel lbl_tamaño7;
    private javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_total1;
    private javax.swing.JLabel lbl_vendedor;
    private javax.swing.JLabel lbl_venta;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl_1;
    private javax.swing.JPanel pnl_2;
    private rojerusan.RSPanelsSlider rSPanelsSlider1;
    private rojerusan.RSPanelsSlider rSPanelsSlider2;
    private javax.swing.JSpinner spn_cantidad;
    private javax.swing.JTable tbl_cargar;
    private javax.swing.JTable tbl_descargar;
    private javax.swing.JTable tbl_info;
    private javax.swing.JTable tbl_info1;
    private javax.swing.JTable tbl_info2;
    private javax.swing.JTable tbl_info3;
    public static javax.swing.JTextArea txt_apellido_venta;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_apellidos1;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextArea txt_descripcion_ingrediente;
    private javax.swing.JTextArea txt_descripcion_pizza;
    private javax.swing.JTextArea txt_direccion;
    private javax.swing.JTextArea txt_direccion1;
    public static javax.swing.JTextArea txt_direccion_venta;
    private javax.swing.JTextField txt_edad;
    private javax.swing.JTextField txt_eliminar;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_id1;
    private javax.swing.JTextField txt_id_ingrediente;
    private javax.swing.JTextField txt_id_pizza;
    public static javax.swing.JTextArea txt_id_venta;
    private javax.swing.JTextField txt_nombre_ingrediente;
    private javax.swing.JTextField txt_nombre_pizza;
    public static javax.swing.JTextArea txt_nombre_venta;
    private javax.swing.JTextField txt_nombres;
    private javax.swing.JTextField txt_nombres1;
    private javax.swing.JTextField txt_precio1;
    private javax.swing.JTextField txt_precio2;
    private javax.swing.JTextArea txt_referencia1;
    public static javax.swing.JTextArea txt_referencias_venta;
    private javax.swing.JTextField txt_telefono1;
    public static javax.swing.JTextArea txt_telefono_venta;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}
