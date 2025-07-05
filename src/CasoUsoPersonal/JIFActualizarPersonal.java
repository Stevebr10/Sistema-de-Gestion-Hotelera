/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoPersonal;

import static CasoUsoHuesped.JIFRegistrarHuesped.validadorDeTelefono;
import Negocio.Conexion;
import Negocio.Huesped;
import Negocio.Metodos;
import Negocio.Personal;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class JIFActualizarPersonal extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;

    public JIFActualizarPersonal() {
        initComponents();
        ins = new Metodos();
        MostrarTablaP("");
    }

    
    public static boolean validadorDeCorreo(String correo) {
        boolean emailCorrecto = false;

        try {
                Pattern pat = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+"); //Pattern pat = Pattern.compile("^[0][9]\\d{8}$"); 
                Matcher mat = pat.matcher(correo);
                if (mat.matches()) {
                    emailCorrecto = true;
                } else {
                    emailCorrecto = false;
                }

        } catch (NumberFormatException nfe) {
            emailCorrecto = false;
        } catch (Exception err) {
            //System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            emailCorrecto = false;
        }
        return emailCorrecto;
    }
    
    public static boolean validadorDeTelefono(String telefono) {
        boolean telefonoCorrecto = false;

        try {
            if (telefono.length() == 10) // Longitud de los digitos del telefono
            {
                Pattern pat = Pattern.compile("^[0][9]\\d{8}$");  //  ---  ^[09][0-9]{9}$
                Matcher mat = pat.matcher(telefono);
                if (mat.matches()) {
                    telefonoCorrecto = true;
                } else {
                    telefonoCorrecto = false;
                }
            } else {
                telefonoCorrecto = false;
            }

        } catch (NumberFormatException nfe) {
            telefonoCorrecto = false;
        } catch (Exception err) {
            //System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            telefonoCorrecto = false;
        }
        return telefonoCorrecto;
    }
    
     public void MostrarTablaP(String Cedula) {
        DefaultTableModel Personal = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 7) {
                    return true;
                } else {
                    return false;
                }
            }
            
        };
        Personal.addColumn("ID");
        Personal.addColumn("Cédula");
        Personal.addColumn("Nombre");
        Personal.addColumn("Apellido");
        Personal.addColumn("Teléfono");
        Personal.addColumn("Correo");
        Personal.addColumn("Rol");
        //Huesped.addColumn("Habitación");
        this.jTablePersonal.setModel(Personal);
        String sql = "";

         if(Cedula.equals("")){
        sql = "SELECT * FROM PERSONAL";
        }else{
        sql= "SELECT * FROM PERSONAL WHERE Cédula like '%"+Cedula+"%'";
         }
        String datos[] = new String[7];
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
                Personal.addRow(datos);
            }
            jTablePersonal.setModel(Personal);
        } catch (SQLException ex) {
            Logger.getLogger(JIFActualizarPersonal.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void limpiarP() {
        this.jTFCedula.setText("");
        this.jTFNombre.setText("");
        this.jTFApellidos.setText("");
        this.jTFTelefono.setText("");
        this.jTFCorreo.setText("");
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

     public void validarCamposVacios() {

        if (jTFCedula.getText().isEmpty()) {
            jLAvisoCedula.setText("Campo Obligatorio");
        } else {
            jLAvisoCedula.setText("");
        }

        if (jTFNombre.getText().isEmpty()) {
            jLAvisoNombre.setText("Campo Obligatorio");
        } else {
            jLAvisoNombre.setText("");
        }

        if (jTFApellidos.getText().isEmpty()) {
            jLAvisoApellido.setText("Campo Obligatorio");
        } else {
            jLAvisoApellido.setText("");
        }

        if (jTFTelefono.getText().isEmpty()) {
            jLAvisoTelefono.setText("Campo Obligatorio");
        } else {
            jLAvisoTelefono.setText("");
        }

        if (jTFCorreo.getText().isEmpty()) {
            jLAvisoCorreo.setText("Campo Obligatorio");
        } else if (!jTFCorreo.getText().contains("@")
                || !jTFCorreo.getText().contains(".")) {

            jLAvisoCorreo.setText("Correo no válido");
        } else {
            jLAvisoCorreo.setText("");
        }

        /*if (jTextRol.getText().isEmpty()) {
            jLAvisoHabitacion.setText("Campo Obligatorio");
        } else {
            jLAvisoHabitacion.setText("");
        }*/

    }
    
   
   public void validarPasaporte(java.awt.event.KeyEvent evento){
       if (evento.getKeyChar() >= 33 && evento.getKeyChar() <= 47
                || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 64
                || evento.getKeyChar() >= 91 && evento.getKeyChar() <= 96
                || evento.getKeyChar() >= 123 && evento.getKeyChar() <= 208
                || evento.getKeyChar() >= 210 && evento.getKeyChar() <= 240
                || evento.getKeyChar() >= 242 && evento.getKeyChar() <= 255) {
           evento.consume();
            JOptionPane.showMessageDialog(null, "No se permiten caracteres especiales"); 
       }
       
   }

    public void habilitarBoton() {

        if (jTFCedula.getText().isEmpty()
                || jTFNombre.getText().isEmpty()
                || jTFApellidos.getText().isEmpty()
                || jTFTelefono.getText().isEmpty()
                || jTFCorreo.getText().isEmpty()
                || !jLAvisoCorreo.getText().isEmpty()) {

            jBActualizar.setEnabled(false);

        } else {
            jBActualizar.setEnabled(true);
        }
    }
    
    public void validarCorreo(java.awt.event.KeyEvent evento) {

        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 44
                || evento.getKeyChar() == 47
                || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 63
                || evento.getKeyChar() >= 91 && evento.getKeyChar() <= 94
                || evento.getKeyChar() == 96
                || evento.getKeyChar() >= 123 && evento.getKeyChar() <= 255) {

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite ese caracter en específico");

        }

    }
    
     public int Rol(String x){
        int pos = -1;
        if (x.equals(jComboBoxP.getItemAt(0))) {
            pos = 0;
        }else if (x.equals(jComboBoxP.getItemAt(1))) {
            pos = 1;
        }
         return pos; 
    }
    
    /*public void validarNumero(java.awt.event.KeyEvent evento) {

        if (!(evento.getKeyChar() >= 48 && evento.getKeyChar() <= 57) && !(evento.getKeyChar() == 32) ){

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras o caracteres especiales");
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePersonal = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBGuardarP = new javax.swing.JButton();
        jTFCedula = new javax.swing.JTextField();
        jTFNombre = new javax.swing.JTextField();
        jTFApellidos = new javax.swing.JTextField();
        jTFTelefono = new javax.swing.JTextField();
        jTFCorreo = new javax.swing.JTextField();
        jLAvisoCedula = new javax.swing.JLabel();
        jLAvisoNombre = new javax.swing.JLabel();
        jLAvisoApellido = new javax.swing.JLabel();
        jLAvisoTelefono = new javax.swing.JLabel();
        jLAvisoCorreo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLAvisoHabitacion = new javax.swing.JLabel();
        jComboBoxP = new javax.swing.JComboBox<>();

        jLabel7.setText("jLabel7");

        setClosable(true);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal"));

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
        jScrollPane1.setViewportView(jTablePersonal);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Télefono:");

        jLabel5.setText("Correo electrónico:");

        jBActualizar.setText("Actualizar Registro");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cédula:");

        jBGuardarP.setText("Guardar");
        jBGuardarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarPActionPerformed(evt);
            }
        });

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

        jTFApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFApellidosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFApellidosKeyTyped(evt);
            }
        });

        jTFTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFTelefonoMouseClicked(evt);
            }
        });
        jTFTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFTelefonoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFTelefonoKeyTyped(evt);
            }
        });

        jTFCorreo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFCorreoMouseClicked(evt);
            }
        });
        jTFCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCorreoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCorreoKeyTyped(evt);
            }
        });

        jLAvisoCedula.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoCedula.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoNombre.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoNombre.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoApellido.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoApellido.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoTelefono.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoTelefono.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoCorreo.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoCorreo.setForeground(new java.awt.Color(255, 0, 0));

        jLabel6.setText("Rol:");

        jLAvisoHabitacion.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        jLAvisoHabitacion.setForeground(new java.awt.Color(255, 0, 0));

        jComboBoxP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Socio", "Administrador" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel6)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLAvisoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jTFCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLAvisoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jTFTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLAvisoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLAvisoCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLAvisoHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLAvisoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jBActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jBGuardarP)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(2, 2, 2)
                                .addComponent(jLAvisoCedula)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(11, 11, 11)
                                .addComponent(jLAvisoNombre)
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(5, 5, 5)
                                .addComponent(jLAvisoApellido)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLAvisoTelefono))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBActualizar)
                            .addComponent(jBGuardarP))
                        .addGap(16, 16, 16)))
                .addComponent(jLAvisoCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jLAvisoHabitacion)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int fila = jTablePersonal.getSelectedRow();
        if (fila >= 0) {
            //this.JTFIdPaciente.setEditable(false);
            //this.JTFIdPaciente.setText(JTablePaciente.getValueAt(fila, 0).toString());
            
            this.jTFCedula.setText(jTablePersonal.getValueAt(fila, 1).toString());
            this.jTFNombre.setText(jTablePersonal.getValueAt(fila, 2).toString());
            this.jTFApellidos.setText(jTablePersonal.getValueAt(fila, 3).toString());
            this.jTFTelefono.setText(jTablePersonal.getValueAt(fila, 4).toString());
            this.jTFCorreo.setText(jTablePersonal.getValueAt(fila, 5).toString());
            int x = Rol(jTablePersonal.getValueAt(fila,6).toString());
            this.jComboBoxP.setSelectedIndex(x);
            this.jTFNombre.setEnabled(false);
            this.jTFApellidos.setEnabled(false);
            this.jTFCedula.setEnabled(false);
            this.jBGuardarP.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro");
        }

    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBGuardarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarPActionPerformed
   if (jTFCedula.getText().isEmpty() && jTFNombre.getText().isEmpty() && jTFApellidos.getText().isEmpty()) {
            this.jBGuardarP.setEnabled(false);
   }else{
       Personal aux = new Personal(null, null, this.jTFNombre.getText(), this.jTFApellidos.getText(), this.jTFTelefono.getText(), this.jTFCorreo.getText(),
                this.jComboBoxP.getSelectedItem().toString());
        int posicion = jTablePersonal.getSelectedRow();
        String valor = jTablePersonal.getValueAt(posicion, 0).toString();
        try {
            ins.ActualizarRegistroP(aux, Integer.parseInt(valor));
            MostrarTablaP("");
            limpiarP();
            this.jBGuardarP.setEnabled(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        JOptionPane.showMessageDialog(null, "Registro Actualizado Exitoso");
   }

        
    }//GEN-LAST:event_jBGuardarPActionPerformed

    private void jTFCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFCedulaKeyReleased

    private void jTFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyTyped
        //validarNumero(evt);
    }//GEN-LAST:event_jTFCedulaKeyTyped

    private void jTFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreActionPerformed

    }//GEN-LAST:event_jTFNombreActionPerformed

    private void jTFNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFNombreKeyReleased

    private void jTFNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFNombreKeyTyped

    private void jTFApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFApellidosKeyReleased

    private void jTFApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFApellidosKeyTyped

    private void jTFTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFTelefonoKeyReleased

    private void jTFTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyTyped
        //validarNumero(evt);
        char variable = evt.getKeyChar();
        int ind = this.jTFTelefono.getText().lastIndexOf(".");
        int ind2 = this.jTFTelefono.getText().lastIndexOf(",");
        int ind3 = this.jTFTelefono.getText().lastIndexOf("´");
        int ind4 = this.jTFTelefono.getText().lastIndexOf("{");
        int ind5 = this.jTFTelefono.getText().lastIndexOf("-");
        int ind6 = this.jTFTelefono.getText().lastIndexOf("}");
        int ind7 = this.jTFTelefono.getText().lastIndexOf("|");
        int ind8 = this.jTFTelefono.getText().lastIndexOf("'");
        int ind9 = this.jTFTelefono.getText().lastIndexOf("¿");
        int ind10 = this.jTFTelefono.getText().lastIndexOf("@");
        if (Character.isLetter(variable) | Character.isSpaceChar(variable) | ind != -1 | ind2 != -1
                | ind3 != -1 | ind4 != -1 | ind5 != -1 | ind6 != -1 | ind7 != -1 | ind8 != -1 | ind9 != -1 | ind10 != -1) {
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS ENTEROS");
            getToolkit().beep();
            evt.consume();
            this.jTFTelefono.requestFocus();
        }
    }//GEN-LAST:event_jTFTelefonoKeyTyped

    private void jTFCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFCorreoKeyReleased

    private void jTFCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyTyped
        validarCorreo(evt);
    }//GEN-LAST:event_jTFCorreoKeyTyped

    private void jTFTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validadorDeTelefono(this.jTFTelefono.getText()) == true) {
                this.jTFCorreo.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Teléfono no válido");
                this.jTFTelefono.requestFocus();
            }
        }
    }//GEN-LAST:event_jTFTelefonoKeyPressed

    private void jTFCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validadorDeCorreo(this.jTFCorreo.getText()) == true) {
                this.jComboBoxP.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Teléfono no válido");
                this.jTFCorreo.requestFocus();
            }
        }
    }//GEN-LAST:event_jTFCorreoKeyPressed

    private void jTFCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFCorreoMouseClicked
         if (validadorDeTelefono(this.jTFTelefono.getText()) == true) {
            this.jTFCorreo.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Teléfono no válido");
            this.jTFTelefono.requestFocus();
        }
    }//GEN-LAST:event_jTFCorreoMouseClicked

    private void jTFTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFTelefonoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTelefonoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizar;
    private javax.swing.JButton jBGuardarP;
    private javax.swing.JComboBox<String> jComboBoxP;
    private javax.swing.JLabel jLAvisoApellido;
    private javax.swing.JLabel jLAvisoCedula;
    private javax.swing.JLabel jLAvisoCorreo;
    private javax.swing.JLabel jLAvisoHabitacion;
    private javax.swing.JLabel jLAvisoNombre;
    private javax.swing.JLabel jLAvisoTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTablePersonal;
    // End of variables declaration//GEN-END:variables
}
