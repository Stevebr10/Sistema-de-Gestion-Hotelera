/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasosUsoIngresos;

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
 * @author JHON
 */
public class JIFConsultarCobro extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;
    
    public JIFConsultarCobro() {
        initComponents();
        ins = new Metodos();
        MostrarTablaC("");
    }
    
    
    public void MostrarTablaC(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        Cobro.addColumn("ID Personal");
        Cobro.addColumn("ID Cobro");
        Cobro.addColumn("Tipo de Habitacion");
        Cobro.addColumn("Días");
        Cobro.addColumn("Precio");
        Cobro.addColumn("Subtotal");
        Cobro.addColumn("Total");
        Cobro.addColumn("Fecha");
        //Huesped.addColumn("Habitación");
        this.jTableCobro.setModel(Cobro);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT c.idPersonal, idCobro, TipoHabitacion, Cantidad, PrecioHabitacion, (Cantidad*PrecioHabitacion) as Subtotal\n" +
                ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n" +
                "where c.idPersonal=p.idPersonal";
        } else {
            sql = "SELECT c.idPersonal, idCobro, TipoHabitacion, Cantidad, PrecioHabitacion, (Cantidad*PrecioHabitacion) as Subtotal\n" +
                ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n" +
                "where c.idPersonal=p.idPersonal and FechaCobro like '%" + Nombre + "%'";
        }
        String datos[] = new String[8];
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
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTableCobro.setModel(Cobro);
        } catch (SQLException ex) {
            Logger.getLogger(JIFRegistrarCobro.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    
    public void MostrarTablaCobroTotal(String Nombre) {
        DefaultTableModel Cobro = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        Cobro.addColumn("Total de Ingresos");
        //Huesped.addColumn("Habitación");
        this.jTableCobro.setModel(Cobro);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT ROUND(SUM(((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor))),2,1) as Total FROM COBRO c, PARAMETROS p\n" +
                    "where c.idPersonal=p.idPersonal";
        } else {
            sql = "SELECT c.idPersonal, idCobro, TipoHabitacion, Cantidad, PrecioHabitacion, (Cantidad*PrecioHabitacion) as Subtotal\n" +
                ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n" +
                "where c.idPersonal=p.idPersonal and FechaCobro like '%" + Nombre + "%'";
        }
        String datos[] = new String[1];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                //datos[6] = rs.getString(7);
                Cobro.addRow(datos);
            }
            jTableCobro.setModel(Cobro);
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
        jTableCobro = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jTextConsultarC = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jTableCobro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCobro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCobroMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCobro);

        jLabel16.setText("Ingrese la fecha para buscar un cobro:");

        jTextConsultarC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextConsultarCKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextConsultarC, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jTextConsultarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton1.setText("Total de Ingresos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addGap(95, 95, 95)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jButton1)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableCobroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCobroMouseClicked
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
    }//GEN-LAST:event_jTableCobroMouseClicked

    private void jTextConsultarCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextConsultarCKeyReleased
        MostrarTablaC(this.jTextConsultarC.getText());
    }//GEN-LAST:event_jTextConsultarCKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MostrarTablaCobroTotal("");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCobro;
    private javax.swing.JTextField jTextConsultarC;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
