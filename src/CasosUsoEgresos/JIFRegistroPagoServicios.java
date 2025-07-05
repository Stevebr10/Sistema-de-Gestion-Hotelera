/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasosUsoEgresos;

import CasosUsoIngresos.*;
import CasoUsoHuesped.JIFActualizarHuespedes;
import CasoUsoPersonal.JIFRegistrarPersonal;
import Negocio.Conexion;
import Negocio.Metodos;
import Negocio.NotaVenta;
import Negocio.Personal;
import Negocio.Salario;
import Negocio.Servicios;
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
 * @author JHON
 */
public class JIFRegistroPagoServicios extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFRegistroPagoServicios() {
        initComponents();
        jBRegistrarPago.setEnabled(false);
        ins = new Metodos();
        MostrarTablaS("");
    }
    
    public void MostrarTablaS(String Nombre) {
        DefaultTableModel Servicio = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 5) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Servicio.addColumn("ID Personal");
        Servicio.addColumn("ID Salario");
        Servicio.addColumn("Fecha");
        Servicio.addColumn("Servicio");
        Servicio.addColumn("Total Pagado");
        //Huesped.addColumn("Habitación");
        this.jTableServicios.setModel(Servicio);
        String sql = "";

         if(Nombre.equals("")){
        sql = "SELECT * FROM SERVICIOS";
        }else{
        sql= "SELECT * FROM SERVICIOS WHERE Fecha like '%"+Nombre+"%'";
         }
        String datos[] = new String[5];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(4);
                //datos[6] = rs.getString(7);
                Servicio.addRow(datos);
            }
            jTableServicios.setModel(Servicio);
        } catch (SQLException ex) {
            Logger.getLogger(JIFRegistroPagoServicios.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    
    /*public void calcularTotal(){
        DecimalFormat df = new DecimalFormat("#.00");
        float subtotal = Float.parseFloat(jTFRol.getText());
        double subTotal1 = subtotal * 0.12;
        
        double subTotal2 = subtotal * 0.10;
        
        double total = subtotal + subTotal1 + subTotal2;
        jTFSalarios.setText(String.valueOf(df.format(total)));
    }*/

    public void habilitarBoton() {

        if (jTFecha.getText().isEmpty()
                || jTFecha.getText().isEmpty()
                || jTFServicio.getText().isEmpty()
                || jTFTotalPagado.getText().isEmpty()
              ) {
            
            jBRegistrarPago.setEnabled(false);
            
        } else {
            jBRegistrarPago.setEnabled(true);
        }
    }
    
    public void limpiarS() {
        this.jTFecha.setText("");
        this.jTFServicio.setText("");
        this.jTFTotalPagado.setText("");
       
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
        jLabel2 = new javax.swing.JLabel();
        jTFServicio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFTotalPagado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTidPersonal = new javax.swing.JTextField();
        jPanelTablaH = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicios = new javax.swing.JTable();
        jBRegistrarPago = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(877, 397));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jLabel12.setText("Fecha:");

        jTFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFechaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFechaKeyTyped(evt);
            }
        });

        jLabel2.setText("Servicio:");

        jTFServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFServicioActionPerformed(evt);
            }
        });
        jTFServicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFServicioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFServicioKeyTyped(evt);
            }
        });

        jLabel14.setText("Total Pagado:");

        jLabel1.setText("ID Personal:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jTFTotalPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel12)
                            .addComponent(jLabel1))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTidPersonal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTidPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFTotalPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanelTablaH.setBorder(javax.swing.BorderFactory.createTitledBorder("Servicios"));

        jTableServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableServicios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableServiciosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableServicios);

        javax.swing.GroupLayout jPanelTablaHLayout = new javax.swing.GroupLayout(jPanelTablaH);
        jPanelTablaH.setLayout(jPanelTablaHLayout);
        jPanelTablaHLayout.setHorizontalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTablaHLayout.setVerticalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jBRegistrarPago.setText("Registrar Pago");
        jBRegistrarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarPagoActionPerformed(evt);
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
                        .addGap(17, 17, 17)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jBRegistrarPago)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBRegistrarPago)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFServicioActionPerformed
       
    }//GEN-LAST:event_jTFServicioActionPerformed

    private void jTFServicioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFServicioKeyReleased
        habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFServicioKeyReleased

    private void jTFServicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFServicioKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFServicioKeyTyped

    private void jTFechaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaKeyReleased
         habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFechaKeyReleased

    private void jTFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaKeyTyped
        validarFecha(evt);
    }//GEN-LAST:event_jTFechaKeyTyped

    private void jBRegistrarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarPagoActionPerformed
        Servicios aux = new Servicios(Integer.parseInt(this.jTidPersonal.getText()), this.jTFecha.getText(), this.jTFServicio.getText(),
                Double.parseDouble(this.jTFTotalPagado.getText()));
        try {
            ins.agregarServicio(aux);
            MostrarTablaS("");
            //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
            limpiarS();
        } catch (SQLException ex) {
            //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        JOptionPane.showMessageDialog(null, "Pago Registrado");
        //jTFecha.setEnabled(true);
        //jTFPasaporte.setEnabled(true);
        //jBGuardarHExtranjero.setEnabled(true);
    }//GEN-LAST:event_jBRegistrarPagoActionPerformed

    private void jTableServiciosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableServiciosMouseClicked
        /*int fila = this.jTableServicios.getSelectedRow();
        Object tur = this.jTableServicios.getValueAt(fila, 1);
        if (tur != null) {
            this.jTFCedula.setText(this.jTableServicios.getValueAt(fila, 0).toString());
            this.jTFServicio.setText(this.jTableServicios.getValueAt(fila, 1).toString());
            this.jTApellido.setText(this.jTableServicios.getValueAt(fila, 2).toString());
            //this.jTextTelefono.setText(this.jTablePersonal.getValueAt(fila, 3).toString());
            //this.jTFCorreo.setText(this.jTablePersonal.getValueAt(fila, 4).toString());
            this.jTFRol.setText(this.jTableServicios.getValueAt(fila, 5).toString());
        } else {
            String aux = this.jTFCedula.getText();
            if (aux.equals("")) {

            } else {
                limpiarS();
            }
        }*/
    }//GEN-LAST:event_jTableServiciosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBRegistrarPago;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelTablaH;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFServicio;
    private javax.swing.JTextField jTFTotalPagado;
    private javax.swing.JTextField jTFecha;
    private javax.swing.JTable jTableServicios;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTidPersonal;
    // End of variables declaration//GEN-END:variables
}
