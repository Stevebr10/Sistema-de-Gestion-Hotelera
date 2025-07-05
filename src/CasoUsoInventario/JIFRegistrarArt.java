/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoInventario;

import CasosUsoIngresos.*;
import CasoUsoHuesped.JIFActualizarHuespedes;
import Negocio.Cobro;
import Negocio.Conexion;
import Negocio.Inventario;
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
 * @author JHON
 */
public class JIFRegistrarArt extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFRegistrarArt() {
        initComponents();
        jBGenerarIn.setEnabled(false);
        ins = new Metodos();
        MostrarTablaIn("");
    }
    
    
    public void MostrarTablaIn(String Nombre) {
        DefaultTableModel Inventario = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Inventario.addColumn("Número de Habitación");
        Inventario.addColumn("ID Personal");
        Inventario.addColumn("Código del Artículo");
        Inventario.addColumn("Nombre");
        Inventario.addColumn("Cantidad");
        Inventario.addColumn("Estado:");
        //Huesped.addColumn("Habitación");
        this.jTableInventario.setModel(Inventario);
        String sql = "";

         if(Nombre.equals("")){
        sql = "SELECT * FROM INVENTARIO";
        }else{
        sql= "SELECT * FROM INVENTARIO WHERE CodigoArticulo like '%"+Nombre+"%'";
         }
        String datos[] = new String[6];
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
                datos[5] = rs.getString(6);
                //datos[6] = rs.getString(7);
                Inventario.addRow(datos);
            }
            jTableInventario.setModel(Inventario);
        } catch (SQLException ex) {
            Logger.getLogger(JIFRegistrarArt.class.getName()).log(Level.SEVERE, null, ex);
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

        if (jNumHabitacion.getText().isEmpty()
                || jTFidPersonal.getText().isEmpty()
                || jTFNombre.getText().isEmpty()
                || jTFCantidad.getText().isEmpty()
                || jTFEstado.getText().isEmpty()) {
            
            jBGenerarIn.setEnabled(false);
            
        } else {
            jBGenerarIn.setEnabled(true);
        }
    }
    
    public void limpiarIn() {
        this.jTFidPersonal.setText("");
        this.jTFNombre.setText("");
        this.jNumHabitacion.setText("");
        this.jTFCantidad.setText("");
        this.jTFEstado.setText("");
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
        jNumHabitacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTFidPersonal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFCantidad = new javax.swing.JTextField();
        jTFEstado = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInventario = new javax.swing.JTable();
        jBGenerarIn = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Inventario"));

        jLabel12.setText("N° de Habitación:");

        jNumHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNumHabitacionActionPerformed(evt);
            }
        });
        jNumHabitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jNumHabitacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jNumHabitacionKeyTyped(evt);
            }
        });

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

        jLabel2.setText("Cantidad:");

        jLabel3.setText("Estado:");

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
                        .addComponent(jLabel13)
                        .addGap(52, 52, 52)
                        .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(jTFEstado))))
                .addGap(0, 34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jNumHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFidPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Inventario"));

        jTableInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableInventarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableInventario);

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

        jBGenerarIn.setText("Registrar");
        jBGenerarIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGenerarInActionPerformed(evt);
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
                        .addGap(137, 137, 137)
                        .addComponent(jBGenerarIn)))
                .addContainerGap(16, Short.MAX_VALUE))
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
                .addComponent(jBGenerarIn)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jNumHabitacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNumHabitacionKeyReleased
         habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jNumHabitacionKeyReleased

    private void jNumHabitacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNumHabitacionKeyTyped
        //validarFecha(evt);
    }//GEN-LAST:event_jNumHabitacionKeyTyped

    private void jTFidPersonalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidPersonalKeyTyped
        validarNumero(evt);
    }//GEN-LAST:event_jTFidPersonalKeyTyped

    private void jTFidPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFidPersonalKeyReleased
        habilitarBoton();
        //validarCamposVacios();
    }//GEN-LAST:event_jTFidPersonalKeyReleased

    private void jBGenerarInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGenerarInActionPerformed
        Inventario aux = new Inventario(Integer.parseInt(this.jNumHabitacion.getText()), Integer.parseInt(this.jTFidPersonal.getText()) , 
                this.jTFNombre.getText(), Integer.parseInt(this.jTFCantidad.getText()),
                this.jTFEstado.getText());
        try {
            ins.agregarInventario(aux);
            MostrarTablaIn("");
            //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
            limpiarIn();
        } catch (SQLException ex) {
            //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        JOptionPane.showMessageDialog(null, "Artículo registrado");
        jTFidPersonal.setEnabled(true);
        //jTFPasaporte.setEnabled(true);
        //jBGuardarHExtranjero.setEnabled(true);
    }//GEN-LAST:event_jBGenerarInActionPerformed

    private void jTableInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableInventarioMouseClicked
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
    }//GEN-LAST:event_jTableInventarioMouseClicked

    private void jNumHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNumHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNumHabitacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGenerarIn;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jNumHabitacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFCantidad;
    private javax.swing.JTextField jTFEstado;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFidPersonal;
    private javax.swing.JTable jTableInventario;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
