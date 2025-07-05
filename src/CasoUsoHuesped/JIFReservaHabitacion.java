/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoHuesped;

import Negocio.ColorFilasReserva;
import Negocio.CompararId;
import Negocio.Conexion;
import Negocio.Huesped;
import Negocio.Metodos;
import Negocio.Reserva;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class JIFReservaHabitacion extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;

    public JIFReservaHabitacion() {
        initComponents();
        ins = new Metodos();
        MostrarTablaR();
        MostrarTablaHabi("");
        MostrarTablaH("");
        this.jtIDHabitacion.setEditable(false);
        this.jtIDHuesped.setEditable(false);
        this.jtIDReserva.setEditable(false);
        jTableReserva.setDefaultRenderer(jTableReserva.getColumnClass(0), new ColorFilasReserva());
    }

    public void validarDatosRepetidoH() {
        //boolean reserva = false;
        for (int i = 0; i < this.jTableReserva.getRowCount(); i++) {
            if (this.jTableReserva.getValueAt(i, 1).equals(this.jtIDHuesped)) {
                JOptionPane.showMessageDialog(null, "El huesped ya está en una habitacón");
                //reserva = true;
            }
        }
        //return reserva;
    }

    public boolean validarDatosRepetidoHabi() {
        boolean reserva = false;
        for (int i = 0; i < this.jTableReserva.getRowCount(); i++) {
            if (this.jTableReserva.getValueAt(i, 2).equals(this.jtIDHabitacion.getText())||this.jTableReserva.getValueAt(i, 2).equals(null)) {
                JOptionPane.showMessageDialog(null, "El huesped ya está en una habitacón");
                reserva = true;
            }
        }
        return reserva;
    }

    public void limpiarR() {
        this.jtIDHabitacion.setText("");
        this.jtIDHuesped.setText("");
        this.jtIDReserva.setText("");
    }

    public void MostrarTablaR() {
        DefaultTableModel Reserva = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }

            }

        };

        Reserva.addColumn("N° Alojamiento");
        Reserva.addColumn("ID Huesped");
        Reserva.addColumn("N° Habitacion");
        this.jTableReserva.setModel(Reserva);

        String sql = "SELECT * FROM RESERVAS ORDER BY Orden";
        String datos[] = new String[3];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(2);
                datos[1] = rs.getString(3);
                datos[2] = rs.getString(4);
                Reserva.addRow(datos);
            }
            jTableReserva.setModel(Reserva);

        } catch (SQLException ex) {
            Logger.getLogger(JIFReservaHabitacion.class
                    .getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public int obtenerMayorID() {
        ArrayList<String> idreserva = new ArrayList<String>();
        DefaultTableModel reserva = new DefaultTableModel();
        reserva.addColumn("Orden");
        reserva.addColumn("N° Alojamiento");
        reserva.addColumn("ID Huesped");
        reserva.addColumn("N° Habitación");
        //reserva.addColumn("ID PACIENTE");

        String sql = "SELECT * FROM RESERVAS ORDER BY Orden";
        String datos[] = new String[4];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                //datos[3] = rs.getString(4);
                reserva.addRow(datos);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JIFReservaHabitacion.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < reserva.getRowCount(); i++) {
            idreserva.add(reserva.getValueAt(i, 1).toString());
        }
        String aux = Collections.max(idreserva, new CompararId());
        //int aux;
        return Integer.parseInt(aux);

    }

    /*public void MostrarTablaH(String Nombre) {
        DefaultTableModel Huesped = new DefaultTableModel();/* {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;
                } else {
                    return false;
                }

            }

        };

        Huesped.addColumn("id");
        Huesped.addColumn("Identificación");
        Huesped.addColumn("Nombre");
        Huesped.addColumn("Apellido");
        Huesped.addColumn("Teléfono");
        Huesped.addColumn("Correo");
        //Huesped.addColumn("Habitación");
        this.jTableHuesped.setModel(Huesped);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT * FROM HUESPEDES";
        } else {
            sql = "SELECT * FROM HUESPEDES WHERE Identificación like '%" + Nombre + "%'";
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
                Huesped.addRow(datos);
            }
            jTableHuesped.setModel(Huesped);

        } catch (SQLException ex) {
            Logger.getLogger(JIFHuespedesV1.class
                    .getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }*/
    public void MostrarTablaH(String Nombre) {
        DefaultTableModel Huesped = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;
                } else {
                    return false;
                }
            }

        };
        Huesped.addColumn("ID");
        Huesped.addColumn("Identificación");
        Huesped.addColumn("Nombre");
        Huesped.addColumn("Apellido");
        Huesped.addColumn("Teléfono");
        Huesped.addColumn("Correo");
        //Huesped.addColumn("Habitación");
        this.jTableHuesped.setModel(Huesped);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT * FROM HUESPEDES";
        } else {
            sql = "SELECT * FROM HUESPEDES WHERE Identificación like '%" + Nombre + "%'";
        }
        String datos[] = new String[6];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(2);
                datos[1] = rs.getString(3);
                datos[2] = rs.getString(4);
                datos[3] = rs.getString(5);
                datos[4] = rs.getString(6);
                datos[5] = rs.getString(7);
                //datos[6] = rs.getString(7);
                Huesped.addRow(datos);
            }
            jTableHuesped.setModel(Huesped);
        } catch (SQLException ex) {
            Logger.getLogger(JIFActualizarHuespedes.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void MostrarTablaHabi(String Nombre) {
        DefaultTableModel Habitacion = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 3) {
                    return true;
                } else {
                    return false;
                }

            }

        };

        Habitacion.addColumn("N° de Habitación");
        Habitacion.addColumn("Tipo");
        Habitacion.addColumn("Descripción");

        //Huesped.addColumn("Habitación");
        this.jTableHabitacion.setModel(Habitacion);
        String sql = "";

        if (Nombre.equals("")) {
            sql = "SELECT * FROM HABITACION";
        } else {
            sql = "SELECT * FROM HABITACION WHERE idHabitacion like '%" + Nombre + "%'";
        }
        String datos[] = new String[3];
        Statement st;
        try {
            st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                //datos[6] = rs.getString(7);
                Habitacion.addRow(datos);
            }
            jTableHuesped.setModel(Habitacion);

        } catch (SQLException ex) {
            Logger.getLogger(JIFReservaHabitacion.class
                    .getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public boolean ExisteEnTabla(JTable tabla, String Dato, int Col) {
        boolean Existe = false;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getValueAt(i, Col).equals(Dato)) {
                Existe = true;
            }
        }
        return Existe;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTablaH = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHabitacion = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextBuscarCedula = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHuesped = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        JLIdHuesped = new javax.swing.JLabel();
        jLidHabitacion = new javax.swing.JLabel();
        jtIDHuesped = new javax.swing.JTextField();
        jtIDHabitacion = new javax.swing.JTextField();
        jBGuardar = new javax.swing.JToggleButton();
        jBEliminarR = new javax.swing.JToggleButton();
        jBActualizarR = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableReserva = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jtIDReserva = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);

        jTableHabitacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableHabitacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHabitacionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableHabitacion);

        jLabel7.setText("Ingrese la identificación del huésped a buscar");

        jTextBuscarCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBuscarCedulaKeyReleased(evt);
            }
        });

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

        jLabel1.setText("Info de Habitaciones");

        javax.swing.GroupLayout jPanelTablaHLayout = new javax.swing.GroupLayout(jPanelTablaH);
        jPanelTablaH.setLayout(jPanelTablaHLayout);
        jPanelTablaHLayout.setHorizontalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTablaHLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaHLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(349, 349, 349))
                    .addGroup(jPanelTablaHLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(31, 31, 31)
                        .addComponent(jTextBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaHLayout.createSequentialGroup()
                    .addContainerGap(15, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanelTablaHLayout.setVerticalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTablaHLayout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );

        JLIdHuesped.setText("idHuesped");

        jLidHabitacion.setText("idHabitacion");

        jBGuardar.setText("Guardar");
        jBGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarActionPerformed(evt);
            }
        });

        jBEliminarR.setText("Eliminar");
        jBEliminarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEliminarRActionPerformed(evt);
            }
        });

        jBActualizarR.setText("Actualizar");
        jBActualizarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarRActionPerformed(evt);
            }
        });

        jTableReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableReservaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableReserva);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setText("idReserva");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(JLIdHuesped)
                                    .addGap(11, 11, 11))
                                .addComponent(jLidHabitacion, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtIDHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jBEliminarR, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jBActualizarR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBGuardar)
                            .addComponent(jLabel2)
                            .addComponent(jtIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBEliminarR)
                            .addComponent(jtIDHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLIdHuesped))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBActualizarR)
                            .addComponent(jtIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLidHabitacion))
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextBuscarCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBuscarCedulaKeyReleased
        MostrarTablaH(this.jTextBuscarCedula.getText());
    }//GEN-LAST:event_jTextBuscarCedulaKeyReleased

    private void jBGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarActionPerformed
//        validarDatosRepetidoH();
        String idHu, idHabi, valor;
        int fila = this.jTableReserva.getSelectedRow();
        idHu = this.jtIDHuesped.getText();
        idHabi = this.jtIDHabitacion.getText();

        if (fila != -1) {
            Object m = this.jTableReserva.getValueAt(fila, 1);
            if (m == null) {
                valor = this.jTableReserva.getValueAt(fila, 0).toString();
                if (idHu.equals("")) {
                    JOptionPane.showMessageDialog(null, "POR FAVOR, SELECCIONE UN HUÉSPED EN LA TABLA");
                } else if (idHabi.equals("")) {
                    JOptionPane.showMessageDialog(null, "POR FAVOR, SELECCIONE UNA HABITACIÓN EN LA TABLA");
                } else {
                    try {
                        ins.AgregarRegistroRSRe(idHu, idHabi, valor);
                        MostrarTablaR();
                        limpiarR();
                        JOptionPane.showMessageDialog(null, "FORMATO CORRECTO");
                        //if (ExisteEnTabla(jTableReserva, this.JLIdHuesped.getText(), 0)==false) {

                        //} else {
                        //  JOptionPane.showMessageDialog(null, "HUÉSPED YA ALOJADO EN UNA HABITACIÓN");
                        //}
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFReservaHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "ESTA HABITACIÓN YA ESTA OCUPADA");
            }
        } else {
            JOptionPane.showMessageDialog(null, "POR FAVOR, SELECCIONE UN ALOJAMIENTO EN LA TABLA");
        }
        //JOptionPane.showMessageDialog(null, "REGISTRO AÑADIDO EXITOSAMENTE");

    }//GEN-LAST:event_jBGuardarActionPerformed

    private void jTableHuespedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHuespedMouseClicked
        int aux = this.jTableHuesped.getSelectedRow();
        this.jtIDHuesped.setText(this.jTableHuesped.getValueAt(aux, 0).toString());
    }//GEN-LAST:event_jTableHuespedMouseClicked

    private void jTableHabitacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHabitacionMouseClicked
        int aux = this.jTableHabitacion.getSelectedRow();
        this.jtIDHabitacion.setText(this.jTableHabitacion.getValueAt(aux, 0).toString());
    }//GEN-LAST:event_jTableHabitacionMouseClicked

    private void jTableReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReservaMouseClicked
        int fila = this.jTableReserva.getSelectedRow();
        Object tur = this.jTableReserva.getValueAt(fila, 1);
        if (tur != null) {
            this.jtIDReserva.setText(this.jTableReserva.getValueAt(fila, 0).toString());
            this.jtIDHuesped.setText(this.jTableReserva.getValueAt(fila, 1).toString());
            this.jtIDHabitacion.setText(this.jTableReserva.getValueAt(fila, 2).toString());
        } else {
            String aux = this.jtIDReserva.getText();
            if (aux.equals("")) {

            } else {
                limpiarR();
            }
        }
    }//GEN-LAST:event_jTableReservaMouseClicked

    private void jBEliminarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEliminarRActionPerformed
        int fila = this.jTableReserva.getSelectedRow();
        String valor;
        if (fila != -1) {
            Object m = jTableReserva.getValueAt(fila, 2);
            if (m != null) {
                int opc = JOptionPane.showConfirmDialog(null, "¿DESEA ELIMINAR ESTE REGISTRO?", "ELIMINAR REGISTRO", JOptionPane.YES_NO_OPTION);
                if (opc == JOptionPane.YES_OPTION) {
                    valor = this.jTableReserva.getValueAt(fila, 0).toString();
                    try {
                        int maxid = obtenerMayorID();
                        ins.borrarRegistroRe(valor, maxid);
                        MostrarTablaR();
                        limpiarR();
                        JOptionPane.showMessageDialog(null, "REGISTRO ELIMINADO EXITOSAMENTE");
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFReservaHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "ALOJAMIENTO LIBRE NO SE PUEDE ELIMINAR");
                MostrarTablaR();
            }
        } else {
            JOptionPane.showMessageDialog(null, "POR FAVOR SELECCIONE UN HABITACIÓN EN LA TABLA");
        }

        /*int aux = jTableReserva.getSelectedRow();
        String valor = jTableReserva.getValueAt(aux,0).toString();
        int  x = Integer.parseInt(jTableReserva.getValueAt(jTableReserva.getSelectedRow(),0).toString());
        if (aux!=-1) {
            int opc=JOptionPane.showConfirmDialog(null,"¿Desea eliminar el Registro?","ELIMINAR REGISTRO",JOptionPane.YES_NO_OPTION);
            if (opc==JOptionPane.YES_OPTION) {
                try {
                    ins.borrarRegistroRe1(valor, x);
                    JOptionPane.showMessageDialog(null,"Registro eliminado Exitosamente");
                    MostrarTablaR();
                } catch (SQLException ex) {
                    Logger.getLogger(JIFReservaHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"No a seleccionado un Registro");
        }*/
    }//GEN-LAST:event_jBEliminarRActionPerformed

    private void jBActualizarRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarRActionPerformed
        String idHues, idHabi, valor;
        int fila = this.jTableReserva.getSelectedRow();
        idHues = this.jtIDHuesped.getText();
        idHabi = this.jtIDHabitacion.getText();

        if (fila != -1) {
            Object m = this.jTableReserva.getValueAt(fila, 1);
            if (m != null) {
                valor = this.jTableReserva.getValueAt(fila, 0).toString();
                if (idHues.equals("")) {
                    JOptionPane.showMessageDialog(null, "POR FAVOR SELECCIONE UN HÚESPED EN LA TABLA");
                } else if (idHabi.equals("")) {
                    JOptionPane.showMessageDialog(null, "POR FAVOR SELECCIONE UNA HABITACIÓN EN LA TABLA");
                } else {
                    try {
                        // if (ExisteEnTabla(jTableReserva, this.JLIdHuesped.getText(), 0)) {
                        ins.ActualizarReserva(valor, idHues, idHabi);
                        MostrarTablaR();
                        limpiarR();
                        JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO EXITOSAMENTE");
                        //} else {
                        //  JOptionPane.showMessageDialog(null, "HUÉSPED YA ALOJADO EN UNA HABITACIÓN");
                        //}
                    } catch (SQLException ex) {
                        Logger.getLogger(JIFReservaHabitacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "ALOJAMIENTO DISPONIBLE NO SE PUEDE ACTUALIZAR");
            }
        } else {
            JOptionPane.showMessageDialog(null, "POR FAVOR SELECCIONE UN CITA EN LA TABLA");
        }
        //JOptionPane.showMessageDialog(null, "REGISTRO ACTUALIZADO EXITOSAMENTE");
    }//GEN-LAST:event_jBActualizarRActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLIdHuesped;
    private javax.swing.JToggleButton jBActualizarR;
    private javax.swing.JToggleButton jBEliminarR;
    private javax.swing.JToggleButton jBGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLidHabitacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelTablaH;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableHabitacion;
    private javax.swing.JTable jTableHuesped;
    private javax.swing.JTable jTableReserva;
    private javax.swing.JTextField jTextBuscarCedula;
    private javax.swing.JTextField jtIDHabitacion;
    private javax.swing.JTextField jtIDHuesped;
    private javax.swing.JTextField jtIDReserva;
    // End of variables declaration//GEN-END:variables
}
