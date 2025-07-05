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
public class JIFRegistrarCobro extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;

    public JIFRegistrarCobro() {
        initComponents();
        //jBGenerar.setEnabled(false);
        ins = new Metodos();
        MostrarTablaC("");
        MostrarTablaP("");
        jBGenerar.setEnabled(false);
        this.jTFIdPersonal.setEnabled(false);
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
            sql = "SELECT c.idPersonal, idCobro, TipoHabitacion, Cantidad, PrecioHabitacion, (Cantidad*PrecioHabitacion) as Subtotal\n"
                    + ", ((Cantidad*PrecioHabitacion)+((Cantidad*PrecioHabitacion)*Valor)) as Total, FechaCobro FROM COBRO c, PARAMETROS p\n"
                    + "where c.idPersonal=p.idPersonal";
        } else {
            sql = "SELECT * FROM COBRO WHERE idCobro like '%" + Nombre + "%'";
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

    public void MostrarTablaP(String Cedula) {
        DefaultTableModel Personal = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 4) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        Personal.addColumn("ID");
        //Personal.addColumn("Cédula");
        Personal.addColumn("Nombre");
        Personal.addColumn("Apellido");
        //Personal.addColumn("Teléfono");
        //Personal.addColumn("Correo");
        Personal.addColumn("Rol");
        //Huesped.addColumn("Habitación");
        this.jTablePersonal.setModel(Personal);
        String sql = "";

        if (Cedula.equals("")) {
            sql = "SELECT * FROM PERSONAL where Rol='Empleado'";
        } else {
            sql = "SELECT * FROM PERSONAL WHERE Cédula like '%" + Cedula + "%'";
        }
        String datos[] = new String[4];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(3);
                datos[2] = rs.getString(4);
                datos[3] = rs.getString(7);
                //datos[4] = rs.getString(5);
                //datos[5] = rs.getString(6);
                //datos[6] = rs.getString(7);
                Personal.addRow(datos);
            }
            jTablePersonal.setModel(Personal);
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
                || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 255) {

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras u otros caracteres especiales");

        }
    }

    public void validarFecha(java.awt.event.KeyEvent evento) {

        if (evento.getKeyChar() >= 33 && evento.getKeyChar() <= 46
                || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 255) {

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras u otros caracteres especiales");
        }
    }

    public void validarNumero(java.awt.event.KeyEvent evento) {

        if (!(evento.getKeyChar() >= 46 && evento.getKeyChar() <= 57) && !(evento.getKeyChar() == 127)) {

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
    public boolean habilitarBoton() {
        boolean boton = false;

        if (this.jTFIdPersonal.getText().isEmpty()
                || jTipoHabitacion.getText().isEmpty()
                || jTFCantidadDias.getText().isEmpty()
                || jTFPrecioHabi.getText().isEmpty()
                || jTFechaCobro.getText().isEmpty()) {

            //jBGenerar.setEnabled(false);
            boton = true;

        } else {
            boton = false;
            //jBGenerar.setEnabled(true);
        }
        return boton;
    }
    
    public void habilitarBoton1() {

        if (this.jTFIdPersonal.getText().isEmpty()
                || jTipoHabitacion.getText().isEmpty()
                || jTFCantidadDias.getText().isEmpty()
                || jTFPrecioHabi.getText().isEmpty()
                || jTFechaCobro.getText().isEmpty()) {

            jBGenerar.setEnabled(false);

        } else {
            jBGenerar.setEnabled(true);
        }
       
    }

    public void limpiarC() {
        this.jTFCantidadDias.setText("");
        this.jTFechaCobro.setText("");
        this.jTipoHabitacion.setText("");
        this.jTFPrecioHabi.setText("");
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
        jTipoHabitacion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTFCantidadDias = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFechaCobro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTFPrecioHabi = new javax.swing.JTextField();
        jBGenerar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTFIdPersonal = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCobro = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePersonal = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        jLabel11.setText("jLabel11");

        setClosable(true);
        setMaximizable(true);
        setNextFocusableComponent(this);
        setPreferredSize(new java.awt.Dimension(875, 447));
        setVisible(true);

        jLSubTotal.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLSubTotal.setForeground(new java.awt.Color(255, 0, 0));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Cobro"));

        jLabel12.setText("Tipo de Habitación:");

        jTipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTipoHabitacionActionPerformed(evt);
            }
        });
        jTipoHabitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTipoHabitacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTipoHabitacionKeyTyped(evt);
            }
        });

        jLabel1.setText("Cantidad de días:");

        jTFCantidadDias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCantidadDiasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCantidadDiasKeyTyped(evt);
            }
        });

        jLabel13.setText("Fecha del cobro:");

        jTFechaCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFechaCobroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFechaCobroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFechaCobroKeyTyped(evt);
            }
        });

        jLabel14.setText("Precio Habitación:");

        jTFPrecioHabi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFPrecioHabiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFPrecioHabiKeyReleased(evt);
            }
        });

        jBGenerar.setText("Registrar");
        jBGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGenerarActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Personal:");

        jTFIdPersonal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFIdPersonalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTFPrecioHabi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTFCantidadDias, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTipoHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(jTFIdPersonal)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(26, 26, 26)
                        .addComponent(jTFechaCobro)))
                .addGap(21, 21, 21))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jBGenerar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFIdPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFCantidadDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTFPrecioHabi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jBGenerar)
                .addGap(21, 21, 21))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cobros"));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal"));

        jTablePersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePersonalMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePersonal);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLSubTotal)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTipoHabitacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTipoHabitacionKeyReleased
        habilitarBoton1();
        //validarCamposVacios();
        validarCaracter(evt);
    }//GEN-LAST:event_jTipoHabitacionKeyReleased

    private void jTipoHabitacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTipoHabitacionKeyTyped
        //validarFecha(evt);
    }//GEN-LAST:event_jTipoHabitacionKeyTyped

    private void jTFCantidadDiasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCantidadDiasKeyTyped
        validarNumero(evt);
    }//GEN-LAST:event_jTFCantidadDiasKeyTyped

    private void jTFCantidadDiasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCantidadDiasKeyReleased
        habilitarBoton1();
        //validarCamposVacios();
        validarNumero(evt);
    }//GEN-LAST:event_jTFCantidadDiasKeyReleased

    private void jBGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGenerarActionPerformed
        Cobro aux = new Cobro(Integer.parseInt(this.jTFIdPersonal.getText()), this.jTipoHabitacion.getText(), Integer.parseInt(this.jTFCantidadDias.getText()), Double.parseDouble(this.jTFPrecioHabi.getText()),
                this.jTFechaCobro.getText());
        if (!this.jTFIdPersonal.getText().isEmpty() && !this.jTipoHabitacion.getText().isEmpty()
                && !this.jTFCantidadDias.getText().isEmpty() && !this.jTFPrecioHabi.getText().isEmpty()
                && !this.jTFechaCobro.getText().isEmpty()) {
            if (habilitarBoton() == false) {

                try {
                    ins.agregarRegistroCobro(aux);
                    MostrarTablaC("");
                    //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
                    limpiarC();
                } catch (SQLException ex) {
                    //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.toString());
                }
                JOptionPane.showMessageDialog(null, "Cobro Registrado");
                jTFCantidadDias.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "formaro");
            }

        } else {
             JOptionPane.showMessageDialog(null, "Registros Vacíos");
        }

        //jTFPasaporte.setEnabled(true);
        //jBGuardarHExtranjero.setEnabled(true);
    }//GEN-LAST:event_jBGenerarActionPerformed

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

    private void jTipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTipoHabitacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTipoHabitacionActionPerformed

    private void jTablePersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePersonalMouseClicked
        int fila = this.jTablePersonal.getSelectedRow();
        Object tur = this.jTablePersonal.getValueAt(fila, 1);
        if (tur != null) {
            this.jTFIdPersonal.setText(this.jTablePersonal.getValueAt(fila, 0).toString());

        } else {
            String aux = this.jTFIdPersonal.getText();
            if (aux.equals("")) {

            } else {
                limpiarC();
            }
        }
    }//GEN-LAST:event_jTablePersonalMouseClicked

    private void jTFechaCobroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaCobroKeyTyped
        //validarFecha(evt);
    }//GEN-LAST:event_jTFechaCobroKeyTyped

    private void jTFPrecioHabiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFPrecioHabiKeyPressed

    }//GEN-LAST:event_jTFPrecioHabiKeyPressed

    private void jTFPrecioHabiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFPrecioHabiKeyReleased
        validarNumero(evt);
        habilitarBoton1();
    }//GEN-LAST:event_jTFPrecioHabiKeyReleased

    private void jTFechaCobroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaCobroKeyPressed
        validarFecha(evt);
    }//GEN-LAST:event_jTFechaCobroKeyPressed

    private void jTFIdPersonalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFIdPersonalKeyReleased
        habilitarBoton1();
    }//GEN-LAST:event_jTFIdPersonalKeyReleased

    private void jTFechaCobroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFechaCobroKeyReleased
        habilitarBoton1();
    }//GEN-LAST:event_jTFechaCobroKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBGenerar;
    private javax.swing.JLabel jLSubTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFCantidadDias;
    private javax.swing.JTextField jTFIdPersonal;
    private javax.swing.JTextField jTFPrecioHabi;
    private javax.swing.JTextField jTFechaCobro;
    private javax.swing.JTable jTableCobro;
    private javax.swing.JTable jTablePersonal;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTipoHabitacion;
    // End of variables declaration//GEN-END:variables
}
