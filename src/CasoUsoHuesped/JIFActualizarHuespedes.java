/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoHuesped;

import static CasoUsoHuesped.JIFRegistrarHuesped.validadorDeTelefono;
import Negocio.Conexion;
import Negocio.Huesped;
import Negocio.Metodos;
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
public class JIFActualizarHuespedes extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;

    public JIFActualizarHuespedes() {
        initComponents();
        ins = new Metodos();
        MostrarTablaH("");
    }

    /*private void MostrarTablaH1() {
        DefaultTableModel Huesped = (DefaultTableModel) jTableHuesped.getModel();
        Huesped.addColumn("id");
        Huesped.addColumn("Cédula");
        Huesped.addColumn("Nombre");
        Huesped.addColumn("Apellido");
        Huesped.addColumn("Teléfono");
        Huesped.addColumn("Correo");
        Huesped.addColumn("Habitación");
        jTableHuesped.setModel(Huesped);

        Huesped.setRowCount(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        try {
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM HUESPEDES");
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                Huesped.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }*/
    
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
            //if (telefono.length() == 10) // Longitud de los digitos del telefono
            //{
                
                //Pattern pat = Pattern.compile("^[0][9]\\d{8}$");  //  ---  ^[09][0-9]{9}$ //  
                String allCountryRegex = "^(\\+)(\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{2}$";
                Pattern pat = Pattern.compile(allCountryRegex);
                Matcher mat = pat.matcher(telefono);
                if (mat.matches()) {
                    telefonoCorrecto = true;
                } else {
                    telefonoCorrecto = false;
                }
            //} else {
                //telefonoCorrecto = false;
            //}

        } catch (NumberFormatException nfe) {
            telefonoCorrecto = false;
        } catch (Exception err) {
            //System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            telefonoCorrecto = false;
        }
        return telefonoCorrecto;
    }
    
     public void MostrarTablaH(String Nombre) {
        DefaultTableModel Huesped = new DefaultTableModel(){
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

         if(Nombre.equals("")){
        sql = "SELECT * FROM HUESPEDES";
        }else{
        sql= "SELECT * FROM HUESPEDES WHERE Identificación like '%"+Nombre+"%'";
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

    public void limpiarH() {
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
        jTableHuesped = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jTextBuscarCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jBActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBGuardarH = new javax.swing.JButton();
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

        jLabel7.setText("jLabel7");

        setClosable(true);
        setMaximizable(true);

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
        jScrollPane1.setViewportView(jTableHuesped);

        jLabel8.setText("Ingresar la idetentificación del Huésped a buscar");

        jTextBuscarCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextBuscarCedulaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("Télefono:");

        jLabel5.setText("Correo electrónico");

        jBActualizar.setText("Actualizar Registro");
        jBActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarActionPerformed(evt);
            }
        });

        jLabel1.setText("Cédula/Pasaporte");

        jBGuardarH.setText("Guardar");
        jBGuardarH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGuardarHActionPerformed(evt);
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
                        .addComponent(jLabel5)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLAvisoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jTFCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLAvisoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jTFTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                .addComponent(jLAvisoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLAvisoCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(6, 6, 6)))
                    .addComponent(jLAvisoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jBActualizar)
                        .addGap(41, 41, 41)
                        .addComponent(jBGuardarH)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
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
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jBActualizar)
                    .addComponent(jBGuardarH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLAvisoCorreo)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarActionPerformed
        int fila = jTableHuesped.getSelectedRow();
        if (fila >= 0) {
            //this.JTFIdPaciente.setEditable(false);
            //this.JTFIdPaciente.setText(JTablePaciente.getValueAt(fila, 0).toString());

            this.jTFCedula.setText(jTableHuesped.getValueAt(fila, 1).toString());
            this.jTFNombre.setText(jTableHuesped.getValueAt(fila, 2).toString());
            this.jTFApellidos.setText(jTableHuesped.getValueAt(fila, 3).toString());
            this.jTFTelefono.setText(jTableHuesped.getValueAt(fila, 4).toString());
            this.jTFCorreo.setText(jTableHuesped.getValueAt(fila, 5).toString());
            //this.jTextHabitacion.setText(jTableHuesped.getValueAt(fila, 5).toString());
            this.jTFNombre.setEnabled(false);
            this.jTFApellidos.setEnabled(false);
            this.jTFCedula.setEnabled(false);
            this.jBGuardarH.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado un registro");
        }

    }//GEN-LAST:event_jBActualizarActionPerformed

    private void jBGuardarHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGuardarHActionPerformed
        if (jTFCedula.getText().isEmpty() && jTFNombre.getText().isEmpty() && jTFApellidos.getText().isEmpty()) {
            this.jBGuardarH.setEnabled(false);
        } else {
            Huesped aux = new Huesped(null, null, this.jTFNombre.getText(), this.jTFApellidos.getText(), this.jTFTelefono.getText(), this.jTFCorreo.getText()/*,
                Integer.parseInt(this.jTextHabitacion.getText())*/);
            int posicion = jTableHuesped.getSelectedRow();
            String valor = jTableHuesped.getValueAt(posicion, 0).toString();
            try {
                ins.ActualizarRegistroH(aux, Integer.parseInt(valor));
                MostrarTablaH("");
                limpiarH();
                this.jBGuardarH.setEnabled(false);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Registro Actualizado Exitosamente");
        }
        
    }//GEN-LAST:event_jBGuardarHActionPerformed

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
        int ind5 = this.jTFTelefono.getText().lastIndexOf("<");
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
            this.jTFCorreo.requestFocus();
        }
    }//GEN-LAST:event_jTFTelefonoKeyTyped

    private void jTFCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyReleased
        habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFCorreoKeyReleased

    private void jTFCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyTyped
        validarCorreo(evt);
    }//GEN-LAST:event_jTFCorreoKeyTyped

    private void jTextBuscarCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextBuscarCedulaKeyReleased
        MostrarTablaH(this.jTextBuscarCedula.getText());
    }//GEN-LAST:event_jTextBuscarCedulaKeyReleased

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
                //this.jTextHabitacion.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Correo no válido");
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
    private javax.swing.JButton jBGuardarH;
    private javax.swing.JLabel jLAvisoApellido;
    private javax.swing.JLabel jLAvisoCedula;
    private javax.swing.JLabel jLAvisoCorreo;
    private javax.swing.JLabel jLAvisoNombre;
    private javax.swing.JLabel jLAvisoTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTableHuesped;
    private javax.swing.JTextField jTextBuscarCedula;
    // End of variables declaration//GEN-END:variables
}
