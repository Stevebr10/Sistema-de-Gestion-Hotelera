/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasosUsoIngresos;

import CasoUsoHuesped.JIFActualizarHuespedes;
import Negocio.Conexion;
import Negocio.Metodos;
import Negocio.NotaVenta;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Brandon Oña
 */
public class JIFNotaVenta extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFNotaVenta() {
        initComponents();
        jBGenerar.setEnabled(false);
        ins = new Metodos();
        MostrarTablaH("");
        MostrarTablaNotaV("");
        MostrarTablaC("");
    }
    
    
    public void MostrarTablaH(String Nombre) {
        DefaultTableModel Huesped = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        //Huesped.addColumn("");
        Huesped.addColumn("Identificación");
        Huesped.addColumn("Nombre");
        Huesped.addColumn("Apellido");
        Huesped.addColumn("Teléfono");
        Huesped.addColumn("Correo");
        //Huesped.addColumn("Habitación");
        this.jTableHuesped.setModel(Huesped);
        String sql = "";

         if(Nombre.equals("")){
        sql = "SELECT * FROM HUESPEDES";
        }else{
        sql= "SELECT * FROM HUESPEDES WHERE Identificación like '%"+Nombre+"%'";
         }
        String datos[] = new String[5];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(3);
                datos[1] = rs.getString(4);
                datos[2] = rs.getString(5);
                datos[3] = rs.getString(6);
                datos[4] = rs.getString(7);
                //datos[5] = rs.getString(7);
                //datos[6] = rs.getString(7);
                Huesped.addRow(datos);
            }
            jTableHuesped.setModel(Huesped);
        } catch (SQLException ex) {
            Logger.getLogger(JIFActualizarHuespedes.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    public void MostrarTablaNotaV(String Nombre) {
        DefaultTableModel NotaVenta = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 9) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        //Huesped.addColumn("");
        NotaVenta.addColumn("Fecha");
        NotaVenta.addColumn("DNI");
        NotaVenta.addColumn("Nombre");
        NotaVenta.addColumn("Apellido");
        //NotaVenta.addColumn("Teléfono");
        //NotaVenta.addColumn("Correo");
        NotaVenta.addColumn("Detalle");
        NotaVenta.addColumn("Subtotal");
        NotaVenta.addColumn("Total");
        //Huesped.addColumn("Habitación");
        this.jTableNotaVenta.setModel(NotaVenta);
        String sql = "";

         if(Nombre.equals("")){
        sql = "SELECT * FROM NOTAVENTA";
        }else{
        sql= "SELECT * FROM NOTAVENTA WHERE Identificación like '%"+Nombre+"%'";
         }
        String datos[] = new String[7];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(5);
                datos[1] = rs.getString(6);
                datos[2] = rs.getString(7);
                datos[3] = rs.getString(8);
                datos[4] = rs.getString(9);
                datos[5] = rs.getString(10);
                datos[6] = rs.getString(11);
                //datos[7] = rs.getString(12);
                //datos[8] = rs.getString(13);
                //datos[6] = rs.getString(7);
                NotaVenta.addRow(datos);
            }
            jTableNotaVenta.setModel(NotaVenta);
        } catch (SQLException ex) {
            Logger.getLogger(JIFNotaVenta.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    public void MostrarTablaC(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        //Cobro.addColumn("ID Personal");
        Cobro.addColumn("ID Cobro");
        Cobro.addColumn("Habitacion");
        Cobro.addColumn("Días");
        Cobro.addColumn("Precio");
        Cobro.addColumn("Subtotal");
        Cobro.addColumn("Total");
        Cobro.addColumn("Fecha");
        //Huesped.addColumn("Habitación");
        this.jTableCobros.setModel(Cobro);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT idCobro, TipoHabitacion, Cantidad, PrecioHabitacion,(Cantidad*PrecioHabitacion) as Subtotal\n" +
                ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n" +
                "where c.idPersonal=p.idPersonal";
        } else {
            sql = "SELECT c.idPersonal, idCobro, TipoHabitacion, Cantidad, PrecioHabitacion, (Cantidad*PrecioHabitacion) as Subtotal\n" +
                ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n" +
                "where c.idPersonal=p.idPersonal and FechaCobro like '%" + Nombre + "%'";
        }
        String datos[] = new String[7];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                //datos[0] = rs.getString(1);
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTableCobros.setModel(Cobro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFRegistrarCobro.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    

    public void validarCaracter(java.awt.event.KeyEvent evento) {

        if (evento.getKeyChar() >= 33 && evento.getKeyChar() <= 64
                || evento.getKeyChar() >= 91 && evento.getKeyChar() <= 96
                || evento.getKeyChar() >= 123 && evento.getKeyChar() <= 208
                || evento.getKeyChar() >= 215 && evento.getKeyChar() <= 216
                || evento.getKeyChar() >= 222 && evento.getKeyChar() <= 223
                || evento.getKeyChar() >= 247 && evento.getKeyChar() <= 248
                || evento.getKeyChar() >= 338 && evento.getKeyChar() <= 8482) {

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permiten caracteres especiales");

        }

    }

   /* public void validarCamposVacios() {

        if (jTFecha.getText().isEmpty()) {
            jLFecha.setText("Campo Obligatorio");
        } else {
            jLFecha.setText("");
        }

        if (jTFRuc.getText().isEmpty()) {
            jLRuc.setText("Campo Obligatorio");
        } else {
            jLRuc.setText("");
        }
        
         if (jTFNombre.getText().isEmpty()) {
            jLNombre.setText("Campo Obligatorio");
        } else {
            jLNombre.setText("");
        }
         
          if (jTFCorreo.getText().isEmpty()) {
            jLCorreo.setText("Campo Obligatorio");
        } else if (!jTFCorreo.getText().contains("@")
                || !jTFCorreo.getText().contains(".")) {

            jLCorreo.setText("Correo no válido");
        } else {
            jLCorreo.setText("");
        }

        if (jTFSubTotal.getText().isEmpty()) {
            jLSubTotal.setText("Campo Obligatorio");
        } else {
            jLSubTotal.setText("");
        }

    }*/

    /*public void validarCorreo(java.awt.event.KeyEvent evento) {

        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 44
                || evento.getKeyChar() == 47
                || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 63
                || evento.getKeyChar() >= 91 && evento.getKeyChar() <= 94
                || evento.getKeyChar() == 96
                || evento.getKeyChar() >= 123 && evento.getKeyChar() <= 255) {

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite ese caracter en específico");

        }

    }*/
    
    public void validarCantidad(java.awt.event.KeyEvent evento) {
        
        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 43
            || evento.getKeyChar() == 45 
            || evento.getKeyChar() == 47   
            || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 255){
            
            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras u otros caracteres especiales");
            
        }
    }
    
    public void validarFecha(java.awt.event.KeyEvent evento){
        
        if(evento.getKeyChar() >= 33 && evento.getKeyChar() <= 46
            || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 255){
            
             evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras u otros caracteres especiales");
        }
    }

    public void validarNumero(java.awt.event.KeyEvent evento) {

        if (!(evento.getKeyChar() >= 48 && evento.getKeyChar() <= 57) && !(evento.getKeyChar() == 32) ){

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras o caracteres especiales");
        }
    }
    
    
    
    public void calcularTotal(){
        DecimalFormat df = new DecimalFormat("#.00");
        float subtotal = Float.parseFloat(jTFSubTotal.getText());
        double subTotal1 = subtotal * 0.12;
        
        double subTotal2 = subtotal * 0.10;
        
        double total = subtotal + subTotal1 + subTotal2;
        jTFTotal.setText(String.valueOf(df.format(total)));
    }

    public void habilitarBoton() {

        if (jTFecha.getText().isEmpty()
                || jTFCedula.getText().isEmpty()
                || jTFNombre.getText().isEmpty()
                || jTFSubTotal.getText().isEmpty()
                || jTADetalle.getText().isEmpty()
                ) {
            
            jBGenerar.setEnabled(false);
            
        } else {
            jBGenerar.setEnabled(true);
        }
    }
    
    public void limpiarH() {
        this.jTFCedula.setText("");
        this.jTFNombre.setText("");
        this.jTApellido.setText("");
        this.jTFecha.setText("");
        this.jTFTotal.setText("");
        this.jTFSubTotal.setText("");
        this.jTADetalle.setText("");
        //this.jTextTelefono.setText("");
        //this.jTFCorreo.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLSubTotal = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFCedula = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTADetalle = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jTApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFSubTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFTotal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHuesped = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableNotaVenta = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCobros = new javax.swing.JTable();
        jBGenerar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel12.setText("Fecha");

        jTFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFechaKeyTyped(evt);
            }
        });

        jLabel3.setText("Detalle:");

        jLabel1.setText("DNI:");

        jLabel2.setText("Nombre");

        jTFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCedulaKeyTyped(evt);
            }
        });

        jTFNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombreActionPerformed(evt);
            }
        });
        jTFNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFNombreKeyTyped(evt);
            }
        });

        jTADetalle.setColumns(20);
        jTADetalle.setRows(5);
        jTADetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTADetalleKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTADetalle);

        jLabel13.setText("Apellido:");

        jLabel4.setText("Sub total");

        jTFSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFSubTotalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFSubTotalKeyTyped(evt);
            }
        });

        jLabel14.setText("Total");

        jTFTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFTotalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(jTFCedula)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel14))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTFTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTFSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTFSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Huédpedes"));

        jTableHuesped.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableHuesped.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHuespedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableHuesped);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Notas de Venta"));

        jTableNotaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTableNotaVenta);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Cobros"));

        jTableCobros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCobros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCobrosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableCobros);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jBGenerar.setText("Generar");
        jBGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGenerarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLSubTotal)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jBGenerar)
                                        .addGap(211, 211, 211)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBGenerar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreActionPerformed
       
    }//GEN-LAST:event_jTFNombreActionPerformed

    private void jTFNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyReleased
        habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFNombreKeyReleased

    private void jTFNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFNombreKeyTyped

    private void jTFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaKeyReleased
         habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFechaKeyReleased

    private void jTFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaKeyTyped
        validarFecha(evt);
    }//GEN-LAST:event_jTFechaKeyTyped

    private void jTFSubTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSubTotalKeyReleased
        habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFSubTotalKeyReleased

    private void jTFSubTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFSubTotalKeyTyped
        validarCantidad(evt);
        habilitarBoton();
    }//GEN-LAST:event_jTFSubTotalKeyTyped

    private void jTFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyTyped
        validarNumero(evt);
    }//GEN-LAST:event_jTFCedulaKeyTyped

    private void jTFCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyReleased
        habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFCedulaKeyReleased

    private void jTableHuespedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHuespedMouseClicked
        int fila = this.jTableHuesped.getSelectedRow();
        Object tur = this.jTableHuesped.getValueAt(fila, 1);
        if (tur != null) {
            this.jTFCedula.setText(this.jTableHuesped.getValueAt(fila, 0).toString());
            this.jTFNombre.setText(this.jTableHuesped.getValueAt(fila, 1).toString());
            this.jTApellido.setText(this.jTableHuesped.getValueAt(fila, 2).toString());
            //this.jTextTelefono.setText(this.jTableHuesped.getValueAt(fila, 3).toString());
            //this.jTFCorreo.setText(this.jTableHuesped.getValueAt(fila, 4).toString());
        } else {
            String aux = this.jTFCedula.getText();
            if (aux.equals("")) {

            } else {
                limpiarH();
            }
        }
    }//GEN-LAST:event_jTableHuespedMouseClicked

    private void jBGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGenerarActionPerformed
        NotaVenta aux = new NotaVenta(this.jTFecha.getText(), this.jTFCedula.getText(), this.jTFNombre.getText(),
                this.jTApellido.getText(), this.jTADetalle.getText(),
                Double.parseDouble(this.jTFSubTotal.getText()), Double.parseDouble(this.jTFTotal.getText()));
        try {
            ins.agregarNotaVenta(aux);
            MostrarTablaNotaV("");
            //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
            limpiarH();
        } catch (SQLException ex) {
            //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        JOptionPane.showMessageDialog(null, "Nota de Venta registrada");
        jTFCedula.setEnabled(true);
        //jTFPasaporte.setEnabled(true);
        //jBGuardarHExtranjero.setEnabled(true);
    }//GEN-LAST:event_jBGenerarActionPerformed

    private void jTableCobrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCobrosMouseClicked
        int fila = this.jTableCobros.getSelectedRow();
        Object tur = this.jTableCobros.getValueAt(fila, 1);
        if (tur != null) {
            this.jTFSubTotal.setText(this.jTableCobros.getValueAt(fila, 4).toString());
            this.jTFTotal.setText(this.jTableCobros.getValueAt(fila, 5).toString());
            this.jTFecha.setText(this.jTableCobros.getValueAt(fila, 6).toString());
            //this.jTextTelefono.setText(this.jTableHuesped.getValueAt(fila, 3).toString());
            //this.jTFCorreo.setText(this.jTableHuesped.getValueAt(fila, 4).toString());
        } else {
            String aux = this.jTFCedula.getText();
            if (aux.equals("")) {

            } else {
                limpiarH();
            }
        }
    }//GEN-LAST:event_jTableCobrosMouseClicked

    private void jTFTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTotalKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_jTFTotalKeyReleased

    private void jTADetalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTADetalleKeyReleased
       habilitarBoton();
    }//GEN-LAST:event_jTADetalleKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGenerar;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTADetalle;
    private javax.swing.JTextField jTApellido;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFSubTotal;
    private javax.swing.JTextField jTFTotal;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTable jTableCobros;
    private javax.swing.JTable jTableHuesped;
    private javax.swing.JTable jTableNotaVenta;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
