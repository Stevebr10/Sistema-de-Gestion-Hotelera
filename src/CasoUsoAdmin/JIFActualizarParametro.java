/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoAdmin;

import CasoUsoInventario.*;
import CasosUsoIngresos.*;
import CasoUsoHuesped.JIFActualizarHuespedes;
import Negocio.Cobro;
import Negocio.Conexion;
import Negocio.Inventario;
import Negocio.Metodos;
import Negocio.NotaVenta;
import Negocio.Parametro;
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
public class JIFActualizarParametro extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFActualizarParametro() {
        initComponents();
        //jBGenerarIn.setEnabled(false);
        ins = new Metodos();
        MostrarTablaPara("");
    }
    
    
    public void MostrarTablaPara(String Nombre) {
        DefaultTableModel Parametro = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column ==5) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Parametro.addColumn("ID Personal");
        Parametro.addColumn("ID Parámetro");
        Parametro.addColumn("Nombre");
        Parametro.addColumn("Descripción");
        Parametro.addColumn("Valor");
        //Huesped.addColumn("Habitación");
        this.jTableParametros.setModel(Parametro);
        String sql = "";

         if(Nombre.equals("")){
        sql = "SELECT * FROM PARAMETROS";
        }else{
        sql= "SELECT * FROM PARAMETROS WHERE Nombre like '%"+Nombre+"%'";
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
                datos[4] = rs.getString(5);
                //datos[6] = rs.getString(7);
                Parametro.addRow(datos);
            }
            jTableParametros.setModel(Parametro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFActualizarParametro.class.getName()).log(Level.SEVERE, null, ex);
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

    public void habilitarBoton() {

        if (jTFidPersonal.getText().isEmpty()
                || jTFNombre.getText().isEmpty()
                || jTFValor.getText().isEmpty()
                || jTFDescripcion.getText().isEmpty()) {
            
            jBGuardar.setEnabled(false);
            
        } else {
            jBGuardar.setEnabled(true);
        }
    }
    
    public void limpiarPa() {
        this.jTFidPersonal.setText("");
        this.jTFNombre.setText("");
        this.jTFValor.setText("");
        this.jTFDescripcion.setText("");
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
        jLabel1 = new javax.swing.JLabel();
        jTFidPersonal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFValor = new javax.swing.JTextField();
        jTFDescripcion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableParametros = new javax.swing.JTable();
        jBGuardar = new javax.swing.JButton();
        jTActualizarP = new javax.swing.JToggleButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar Parámetro"));

        jLabel1.setText("ID Personal:");

        jTFidPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFidPersonalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFidPersonalKeyTyped(evt);
            }
        });

        jLabel13.setText("Nombre:");

        jLabel2.setText("Valor:");

        jLabel3.setText("Descripción:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)
                        .addComponent(jTFidPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(13, 47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFidPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTFValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Parámetros"));

        jTableParametros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableParametros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableParametrosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableParametros);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jTActualizarP.setText("Actualizar");
        jTActualizarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTActualizarPActionPerformed(evt);
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
                        .addGap(16, 16, 16)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jTActualizarP)
                        .addGap(58, 58, 58)
                        .addComponent(jBGuardar)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBGuardar)
                    .addComponent(jTActualizarP))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTFidPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidPersonalKeyTyped
        validarNumero(evt);
    }//GEN-LAST:event_jTFidPersonalKeyTyped

    private void jTFidPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidPersonalKeyReleased
        //habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFidPersonalKeyReleased

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
        Parametro aux = new Parametro(Integer.parseInt(this.jTFidPersonal.getText()) , 
                this.jTFNombre.getText(), this.jTFDescripcion.getText(), Double.parseDouble(this.jTFValor.getText()));
        int posicion = jTableParametros.getSelectedRow();
            String valor = jTableParametros.getValueAt(posicion, 1).toString();
        try {
            ins.ActualizarParametro(aux,Integer.parseInt(valor));
            MostrarTablaPara("");
            //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
            limpiarPa();
        } catch (SQLException ex) {
            //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        JOptionPane.showMessageDialog(null, "Parámetro actualizado");
        jTFidPersonal.setEnabled(true);
        //jTFPasaporte.setEnabled(true);
        //jBGuardarHExtranjero.setEnabled(true);
    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jTableParametrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParametrosMouseClicked
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
    }//GEN-LAST:event_jTableParametrosMouseClicked

    private void jTActualizarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTActualizarPActionPerformed
        int fila = jTableParametros.getSelectedRow();
        if (fila >= 0) {
            //this.JTFIdPaciente.setEditable(false);
            //this.JTFIdPaciente.setText(JTablePaciente.getValueAt(fila, 0).toString());

            this.jTFidPersonal.setText(jTableParametros.getValueAt(fila, 0).toString());
            this.jTFNombre.setText(jTableParametros.getValueAt(fila, 2).toString());
            this.jTFDescripcion.setText(jTableParametros.getValueAt(fila, 3).toString());
            this.jTFValor.setText(jTableParametros.getValueAt(fila, 4).toString());
            //this.jTextHabitacion.setText(jTableHuesped.getValueAt(fila, 5).toString());
            //this.jTFNombre.setEnabled(false);
            this.jTFidPersonal.setEnabled(false);
            //this.jTNumHabitacion.setEnabled(false);
            //this.jBGuardarH.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro");
        }
    }//GEN-LAST:event_jTActualizarPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGuardar;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jTActualizarP;
    private javax.swing.JTextField jTFDescripcion;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFValor;
    private javax.swing.JTextField jTFidPersonal;
    private javax.swing.JTable jTableParametros;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
