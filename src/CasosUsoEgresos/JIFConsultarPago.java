/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasosUsoEgresos;

import CasosUsoIngresos.*;
import CasoUsoHuesped.JIFActualizarHuespedes;
import Negocio.Cobro;
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
public class JIFConsultarPago extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFConsultarPago() {
        initComponents();
        ins = new Metodos();
        MostrarTablaPago("");
    }
    
    
    public void MostrarTablaPago(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Cobro.addColumn("Servicios");
        Cobro.addColumn("Salarios");
        
        //Huesped.addColumn("Habitación");
        this.jTablePagos.setModel(Cobro);
        String sql = "";

         if(Nombre.equals("")){
        sql = "Select CONCAT('Tipo de Servicio: ',TipoServicio,' - Total: ', TotalPagado,' - Fecha: ', se.Fecha) as Servicios, CONCAT('Cédula Personal: ',Cédula, ' - Salario: ', Salario,' - Fecha: ', sa.Fecha) as Salarios\n" +
                "from SERVICIOS se, SALARIOS sa\n" +
                "where sa.idPersonal=se.idPersonal";
        }else{
        sql= "Select CONCAT('Tipo de Servicio: ',TipoServicio,' - Total: ', TotalPagado,' - Fecha: ', se.Fecha) as Servicios, CONCAT('Cédula Personal: ',Cédula, ' - Salario: ', Salario,' - Fecha: ', sa.Fecha) as Salarios\n" +
                "from SERVICIOS se, SALARIOS sa\n" +
                "where sa.idPersonal=se.idPersonal and se.Fecha like '%"+Nombre+"%' and sa.Fecha like '%"+Nombre+"%'";
         }
        String datos[] = new String[2];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                /*datos[2] = rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);*/
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTablePagos.setModel(Cobro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFConsultarPago.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    public void MostrarTablaPagoTotal(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Cobro.addColumn("Servicios");
        Cobro.addColumn("Salarios");
        
        //Huesped.addColumn("Habitación");
        this.jTablePagos.setModel(Cobro);
        String sql = "";

         if(Nombre.equals("")){
        sql = "Select CONCAT('Total Pagado: ', SUM(TotalPagado))  as Servicios, CONCAT('Total Pagado: ', SUM(Salario)) as Salarios\n" +
                "from SERVICIOS se, SALARIOS sa\n" +
                "where sa.idPersonal=se.idPersonal";
        }else{
        sql= "";
         }
        String datos[] = new String[2];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                /*datos[2] = rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);*/
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTablePagos.setModel(Cobro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFConsultarPago.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    public void MostrarTablaListaPagos(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Cobro.addColumn("Servicios");
        Cobro.addColumn("Salarios");
        
        //Huesped.addColumn("Habitación");
        this.jTablePagos.setModel(Cobro);
        String sql = "";

         if(Nombre.equals("")){
        sql = "Select CONCAT('Tipo de Servicio: ',TipoServicio,' - Total: ', TotalPagado,' - Fecha: ', se.Fecha) as Servicios, CONCAT('Cédula Personal: ',Cédula, ' - Salario: ', Salario,' - Fecha: ', sa.Fecha) as Salarios\n" +
                "from SERVICIOS se, SALARIOS sa\n" +
                "where sa.idPersonal=se.idPersonal";
        }else{
        sql= "";
         }
        String datos[] = new String[2];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                /*datos[2] = rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);*/
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTablePagos.setModel(Cobro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFConsultarPago.class.getName()).log(Level.SEVERE, null, ex);
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
        float subtotal = Float.parseFloat(jTFSubTotal.getText());
        double subTotal1 = subtotal * 0.12;
        
        double subTotal2 = subtotal * 0.10;
        
        double total = subtotal + subTotal1 + subTotal2;
        jTFTotal.setText(String.valueOf(df.format(total)));
    }*/

    

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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePagos = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jTextConsultarPago = new javax.swing.JTextField();
        jBPagoTotal = new javax.swing.JButton();
        jBListaPagos = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pagos"));

        jTablePagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePagosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePagos);

        jLabel16.setText("Ingrese la fecha para buscar un pago:");

        jTextConsultarPago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextConsultarPagoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextConsultarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextConsultarPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jBPagoTotal.setText("Pago Total");
        jBPagoTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPagoTotalActionPerformed(evt);
            }
        });

        jBListaPagos.setText("Lista de Pagos");
        jBListaPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBListaPagosActionPerformed(evt);
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
                        .addGap(76, 76, 76)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jBPagoTotal)
                        .addGap(142, 142, 142)
                        .addComponent(jBListaPagos)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBPagoTotal)
                    .addComponent(jBListaPagos))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTablePagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePagosMouseClicked
        /*int fila = this.jTableCobro.getSelectedRow();
        Object tur = this.jTableCobro.getValueAt(fila, 1);
        if (tur != null) {
            this.jTFCantidadDias.setText(this.jTableCobro.getValueAt(fila, 0).toString());
            this.jTFNombre.setText(this.jTableCobro.getValueAt(fila, 1).toString());
            this.jTFechaCobro.setText(this.jTableCobro.getValueAt(fila, 2).toString());
            this.jTextTelefono.setText(this.jTableCobro.getValueAt(fila, 3).toString());
            this.jTFCorreo.setText(this.jTableCobro.getValueAt(fila, 4).toString());
        } else {
            String aux = this.jTFCantidadDias.getText();
            if (aux.equals("")) {

            } else {
                limpiarH();
            }
        }*/
    }//GEN-LAST:event_jTablePagosMouseClicked

    private void jTextConsultarPagoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConsultarPagoKeyReleased
        MostrarTablaPago(this.jTextConsultarPago.getText());
    }//GEN-LAST:event_jTextConsultarPagoKeyReleased

    private void jBPagoTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPagoTotalActionPerformed
        MostrarTablaPagoTotal("");
    }//GEN-LAST:event_jBPagoTotalActionPerformed

    private void jBListaPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListaPagosActionPerformed
        MostrarTablaListaPagos("");
    }//GEN-LAST:event_jBListaPagosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBListaPagos;
    private javax.swing.JButton jBPagoTotal;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePagos;
    private javax.swing.JTextField jTextConsultarPago;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
