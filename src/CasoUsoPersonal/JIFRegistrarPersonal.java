/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasoUsoPersonal;

import Negocio.Conexion;
import Negocio.Huesped;
import Negocio.Metodos;
import Negocio.Personal;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.lang.Integer.parseInt;
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
public class JIFRegistrarPersonal extends javax.swing.JInternalFrame {

    Conexion con = new Conexion();
    Metodos ins;

    public JIFRegistrarPersonal() {
        initComponents();
        ins = new Metodos();
        MostrarTablaP("");
        //this.jBGuardarHExtranjero.setEnabled(false);
        //this.jBGuardarHNacional.setEnabled(false);
        //this.jTableHuesped.setEnabled(false);
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

    public static boolean validadorCedulaV2(String cedula) {
        boolean cedulaCorrecta = false;
        //String cedula;
        //Preguntamos si la cedula consta de 10 digitos
        if (cedula.length() == 10) {
            //Obtenemos el digito de la region que sonlos dos primeros digitos
            String digito_region = cedula.substring(0, 2);
            //Pregunto si la region existe ecuador se divide en 24 regiones
            if (Integer.parseInt(digito_region) >= 1 && Integer.parseInt(digito_region) <= 24) {
                // Extraigo el ultimo digito
                String ultimo_digito = cedula.substring(9, 10);

                //Agrupo todos los pares y los sumo
                int pares = Integer.parseInt(cedula.substring(1, 2)) + Integer.parseInt(cedula.substring(3, 4)) + Integer.parseInt(cedula.substring(5, 6)) + Integer.parseInt(cedula.substring(7, 8));

                //Agrupo los impares, los multiplico por un factor de 2, si la resultante es > que 9 le restamos el 9 a la resultante
                int numero1 = Integer.parseInt(cedula.substring(0, 1));
                numero1 = (numero1 * 2);
                if (numero1 > 9) {
                    numero1 = (numero1 - 9);
                }

                int numero3 = Integer.parseInt(cedula.substring(2, 3));
                numero3 = (numero3 * 2);
                if (numero3 > 9) {
                    numero3 = (numero3 - 9);
                }

                int numero5 = Integer.parseInt(cedula.substring(4, 5));
                numero5 = (numero5 * 2);
                if (numero5 > 9) {
                    numero5 = (numero5 - 9);
                }

                int numero7 = Integer.parseInt(cedula.substring(6, 7));
                numero7 = (numero7 * 2);
                if (numero7 > 9) {
                    numero7 = (numero7 - 9);
                }

                int numero9 = Integer.parseInt(cedula.substring(8, 9));
                numero9 = (numero9 * 2);
                if (numero9 > 9) {
                    numero9 = (numero9 - 9);
                }

                int impares = numero1 + numero3 + numero5 + numero7 + numero9;

                //Suma total
                int suma_total = (pares + impares);

                //extraemos el primero digito
                String primer_digito_suma = String.valueOf(suma_total).substring(0, 1);

                //Obtenemos la decena inmediata
                int decena = (parseInt(primer_digito_suma) + 1) * 10;

                //Obtenemos la resta de la decena inmediata - la suma_total esto nos da el digito validador
                int digito_validador = decena - suma_total;

                //Si el digito validador es = a 10 toma el valor de 0
                if (digito_validador == 10) {
                    digito_validador = 0;
                }

                //Validamos que el digito validador sea igual al de la cedula
                if (digito_validador == Integer.parseInt(ultimo_digito)) {
                    cedulaCorrecta = true;
                } else {
                    cedulaCorrecta = false;
                }

            } else {
                // imprimimos en consola si la region no pertenece
                //JOptionPane.showMessageDialog(null, "Esta cédula no pertenece a ninguna región");
                cedulaCorrecta = false;
            }
        } else {
            //imprimimos en consola si la cedula tiene mas o menos de 10 digitos
            //JOptionPane.showMessageDialog(null, "Esta cédula tiene menos de 10 digitos");
            cedulaCorrecta = false;
        }
        return cedulaCorrecta;
    }

    public static boolean validadorDeCedula(String cedula) {
        boolean cedulaCorrecta = false;
        int contDi = 0;

        try {
            if (cedula.length() == 10) // Logitud de una Cédula
            {
                int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                if (tercerDigito < 6) {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                    int verificador = Integer.parseInt(cedula.substring(9, 10));
                    int suma = 0;
                    int digito = 0;
                    for (int i = 0; i < (cedula.length() - 1); i++) {
                        digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
                        suma += ((digito % 10) + (digito / 10));
                    }

                    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
                        cedulaCorrecta = true;
                    } else if ((10 - (suma % 10)) == verificador) {
                        cedulaCorrecta = true;

                    } else {
                        cedulaCorrecta = false;
                    }
                } else {
                    cedulaCorrecta = false;
                }
            } else {
                cedulaCorrecta = false;
            }

        } catch (NumberFormatException nfe) {
            cedulaCorrecta = false;
        } catch (Exception err) {
            System.out.println("Una excepcion ocurrio en el proceso de validadcion");
            cedulaCorrecta = false;
        }

        if (!cedulaCorrecta) {
            //cedulaCorrecta=false;
        }
        return cedulaCorrecta;
    }

    public void MostrarTablaP(String Cedula) {
        DefaultTableModel Personal = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 6) {
                    return true;
                } else {
                    return false;
                }

            }

        };

        Personal.addColumn("Cédula");
        Personal.addColumn("Nombre");
        Personal.addColumn("Apellido");
        Personal.addColumn("Teléfono");
        Personal.addColumn("Correo");
        Personal.addColumn("Rol");
        //Huesped.addColumn("Habitación");
        this.jTableHuesped.setModel(Personal);
        String sql = "";

        if (Cedula.equals("")) {
            sql = "SELECT * FROM PERSONAL";
        } else {
            sql = "SELECT * FROM PERSONAL WHERE Cédula like '%" + Cedula + "%'";
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
                Personal.addRow(datos);
            }
            jTableHuesped.setModel(Personal);

        } catch (SQLException ex) {
            Logger.getLogger(JIFRegistrarPersonal.class
                    .getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void limpiarH() {
        this.jTFCedula.setText("");
        this.jTFNombre.setText("");
        this.jTFApellidos.setText("");
        this.jTFTelefono.setText("");
        this.jTFCorreo.setText("");
        //this.jTextHabitacion.setText("");
        //this.jTFPasaporte.setText("");
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
            jLAvisoCedula.setText("*");
        } else if (!jTFCedula.getText().isEmpty()) {
            jLAvisoCedula.setText("");
        }
        if (jTFNombre.getText().isEmpty()) {
            jLAvisoNombre.setText("*");
        } else {
            jLAvisoNombre.setText("");
        }

        if (jTFApellidos.getText().isEmpty()) {
            jLAvisoApellido.setText("*");
        } else {
            jLAvisoApellido.setText("");
        }

        if (jTFTelefono.getText().isEmpty()) {
            jLAvisoTelefono.setText("*");
        } else {
            jLAvisoTelefono.setText("");
        }

        if (jTFCorreo.getText().isEmpty()) {
            jLAvisoCorreo.setText("*");
        } else if (!jTFCorreo.getText().contains("@")
                || !jTFCorreo.getText().contains(".")) {

            jLAvisoCorreo.setText("*");
        } else {
            jLAvisoCorreo.setText("");
        }

    }

    public void validarPasaporte(java.awt.event.KeyEvent evento) {
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

        if (!(evento.getKeyChar() >= 48) && (evento.getKeyChar() <= 57) && !(evento.getKeyChar() == 32) 
                && !(evento.getKeyChar() == 127 && !(evento.getKeyChar() == 13))){

            evento.consume();
            JOptionPane.showMessageDialog(null, "No se permite letras o caracteres especiales");
        }else{
            
            //JOptionPane.showMessageDialog(null, "No se permite letras o caracteres especiales");
        }
    }*/
    public boolean habilitarBotonv1() {
        boolean boton;

        if ((!jTFCedula.getText().isEmpty())
                || !jTFNombre.getText().isEmpty()
                || !jTFApellidos.getText().isEmpty()
                || !jTFTelefono.getText().isEmpty()
                || !jTFCorreo.getText().isEmpty()
                || !jLAvisoCorreo.getText().isEmpty()) {
            boton = true;
            //jBRegistrarPersonal.setEnabled(boton);

        } else {
            boton = false;
            //jBRegistrarPersonal.setEnabled(boton);

        }
        return boton;
    }

    /*public void habilitarBoton() {

        if ((!jTFCedula.getText().isEmpty() && jTFPasaporte.getText().isEmpty())
                || (!jTFPasaporte.getText().isEmpty() && jTFCedula.getText().isEmpty())
                || !jTFNombre.getText().isEmpty()
                || !jTFApellidos.getText().isEmpty()
                || !jTFTelefono.getText().isEmpty()
                || !jTFCorreo.getText().isEmpty()
                || !jTextHabitacion.getText().isEmpty()) {
            
            jBGuardarHNacional.setEnabled(true);

        } else {
            
            jBGuardarHNacional.setEnabled(false);

        }
        //return boton;
    }*/
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
        jTableHuesped = new javax.swing.JTable();
        jLNombres = new javax.swing.JLabel();
        jLApellidos = new javax.swing.JLabel();
        jLTelefono = new javax.swing.JLabel();
        jLCorreo = new javax.swing.JLabel();
        jBRegistrarPersonal = new javax.swing.JButton();
        JLNombre = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        jComboBoxPer = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);
        setName(""); // NOI18N

        jPanelTablaH.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Registrado"));

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

        javax.swing.GroupLayout jPanelTablaHLayout = new javax.swing.GroupLayout(jPanelTablaH);
        jPanelTablaH.setLayout(jPanelTablaHLayout);
        jPanelTablaHLayout.setHorizontalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanelTablaHLayout.setVerticalGroup(
            jPanelTablaHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTablaHLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLNombres.setText("Nombres");

        jLApellidos.setText("Apellidos");

        jLTelefono.setText("Télefono");

        jLCorreo.setText("Correo electrónico");

        jBRegistrarPersonal.setText("Registrar Personal");
        jBRegistrarPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBRegistrarPersonalMouseClicked(evt);
            }
        });
        jBRegistrarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarPersonalActionPerformed(evt);
            }
        });

        JLNombre.setText("Cédula");

        jTFCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFCedulaMouseClicked(evt);
            }
        });
        jTFCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFCedulaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFCedulaKeyTyped(evt);
            }
        });

        jTFNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFNombreMouseClicked(evt);
            }
        });
        jTFNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombreActionPerformed(evt);
            }
        });
        jTFNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFNombreKeyTyped(evt);
            }
        });

        jTFApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTFApellidosMouseClicked(evt);
            }
        });
        jTFApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFApellidosKeyPressed(evt);
            }
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

        jLAvisoCedula.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLAvisoCedula.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoNombre.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLAvisoNombre.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoApellido.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLAvisoApellido.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoTelefono.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLAvisoTelefono.setForeground(new java.awt.Color(255, 0, 0));

        jLAvisoCorreo.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLAvisoCorreo.setForeground(new java.awt.Color(255, 0, 0));

        jLabel1.setText("Rol:");

        jComboBoxPer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Socio", "Administrador" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JLNombre)
                                .addComponent(jLTelefono)
                                .addComponent(jLApellidos)
                                .addComponent(jLNombres))
                            .addGap(42, 42, 42)))
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTFApellidos)
                                    .addComponent(jTFTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLAvisoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLAvisoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(202, 202, 202)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLAvisoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLAvisoCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLAvisoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jBRegistrarPersonal))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLAvisoCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTFCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(JLNombre)))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLAvisoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLNombres)
                                .addComponent(jTFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLApellidos)
                            .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLAvisoApellido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLTelefono))
                            .addComponent(jLAvisoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelTablaH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jBRegistrarPersonal))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLAvisoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLCorreo)))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxPer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRegistrarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarPersonalActionPerformed
        Personal aux = new Personal(this.jTFCedula.getText(), null, this.jTFNombre.getText(),
                this.jTFApellidos.getText(), this.jTFTelefono.getText(), this.jTFCorreo.getText(),
                this.jComboBoxPer.getSelectedItem().toString());
        if (!this.jTFCedula.getText().isEmpty() && !this.jTFNombre.getText().isEmpty()
                && !this.jTFApellidos.getText().isEmpty()) {
            if (habilitarBotonv1() == true && validadorCedulaV2(this.jTFCedula.getText()) == true) {

            try {
                ins.agregarRegistroPersonal(aux);
                MostrarTablaP("");
                //JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
                limpiarH();
            } catch (SQLException ex) {
                //Logger.getLogger(JIFHuespedesV1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "El huesped ha sido registrado de forma exitosa");
        } else {
            JOptionPane.showMessageDialog(null, "Formato Incorrecto");
            jTFCedula.setEnabled(true);
        }

        } else {
           JOptionPane.showMessageDialog(null, "Registros Vacíos");
        }

        

        //jBGuardarHNacional.setEnabled(false);
        //}
        //}

    }//GEN-LAST:event_jBRegistrarPersonalActionPerformed

    private void jTFCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyReleased
        //habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFCedulaKeyReleased

    private void jTFCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyTyped
        //validarNumero(evt);
        char variable = evt.getKeyChar();
        int ind = this.jTFCedula.getText().lastIndexOf(".");
        int ind2 = this.jTFCedula.getText().lastIndexOf(",");
        int ind3 = this.jTFCedula.getText().lastIndexOf("´");
        int ind4 = this.jTFCedula.getText().lastIndexOf("{");
        int ind5 = this.jTFCedula.getText().lastIndexOf("-");
        int ind6 = this.jTFCedula.getText().lastIndexOf("}");
        int ind7 = this.jTFCedula.getText().lastIndexOf("|");
        int ind8 = this.jTFCedula.getText().lastIndexOf("'");
        int ind9 = this.jTFCedula.getText().lastIndexOf("¿");
        int ind10 = this.jTFCedula.getText().lastIndexOf("@");
        if (Character.isLetter(variable) | Character.isSpaceChar(variable) | ind != -1 | ind2 != -1
                | ind3 != -1 | ind4 != -1 | ind5 != -1 | ind6 != -1 | ind7 != -1 | ind8 != -1 | ind9 != -1 | ind10 != -1) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS ENTEROS");
            this.jTFCedula.requestFocus();
        }
    }//GEN-LAST:event_jTFCedulaKeyTyped

    private void jTFNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombreActionPerformed

    }//GEN-LAST:event_jTFNombreActionPerformed

    private void jTFNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyReleased
        //habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFNombreKeyReleased

    private void jTFNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFNombreKeyTyped

    private void jTFApellidosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyReleased
        //habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFApellidosKeyReleased

    private void jTFApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyTyped
        validarCaracter(evt);
    }//GEN-LAST:event_jTFApellidosKeyTyped

    private void jTFTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyReleased
        //habilitarBoton();
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
        int ind11 = this.jTFTelefono.getText().lastIndexOf("+");
        if (Character.isLetter(variable) | Character.isSpaceChar(variable) | ind != -1 | ind2 != -1
                | ind3 != -1 | ind4 != -1 | ind5 != -1 | ind6 != -1 | ind7 != -1 | ind8 != -1 | ind9 != -1 | ind10 != -1
                | ind11 != -1) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS ENTEROS");
            this.jTFTelefono.requestFocus();
        }

    }//GEN-LAST:event_jTFTelefonoKeyTyped

    private void jTFCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyReleased
        // habilitarBoton();
        validarCamposVacios();
    }//GEN-LAST:event_jTFCorreoKeyReleased

    private void jTFCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyTyped
        validarCorreo(evt);
    }//GEN-LAST:event_jTFCorreoKeyTyped

    private void jTFCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCedulaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validadorCedulaV2(this.jTFCedula.getText()) == true) {
                //this.jBGuardarHNacional.setEnabled(false);
                this.jTFNombre.requestFocus();
            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Cédula no válida");
                this.jTFCedula.requestFocus();
            }
        }
        //this.jBGuardarHNacional.setEnabled(false);
        //this.jBGuardarHExtranjero.setEnabled(false);

        /*if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validadorCedulaV2(this.jTFCedula.getText());
        }*/
    }//GEN-LAST:event_jTFCedulaKeyPressed

    private void jTFNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTFApellidos.requestFocus();
            //this.jBGuardarHNacional.setEnabled(false);
            //this.jBGuardarHExtranjero.setEnabled(false);
        }
    }//GEN-LAST:event_jTFNombreKeyPressed

    private void jTFNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFNombreMouseClicked

        if (validadorCedulaV2(this.jTFCedula.getText()) == true || this.jTFCedula.getText().isEmpty()) {
            this.jTFNombre.requestFocus();
        } else {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Cédula no válida");
            this.jTFCedula.requestFocus();
        }
        //validadorCedulaV2(this.jTFCedula.getText());
    }//GEN-LAST:event_jTFNombreMouseClicked

    private void jTFApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFApellidosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.jTFTelefono.requestFocus();
            //this.jBGuardarHNacional.setEnabled(false);
            //this.jBGuardarHExtranjero.setEnabled(false);
        }
    }//GEN-LAST:event_jTFApellidosKeyPressed

    private void jTFTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTelefonoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validadorDeTelefono(this.jTFTelefono.getText()) == true) {
                this.jTFCorreo.requestFocus();
            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Teléfono no válido");
                this.jTFTelefono.requestFocus();
            }
        }
        //this.jBGuardarHNacional.setEnabled(false);
        //  this.jBGuardarHExtranjero.setEnabled(false);
    }//GEN-LAST:event_jTFTelefonoKeyPressed

    private void jTFCorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFCorreoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validadorDeCorreo(this.jTFCorreo.getText()) == true) {
                this.jComboBoxPer.requestFocus();

            } else {
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Email no válido");
                this.jTFCorreo.requestFocus();
            }
        }
        //this.jBGuardarHNacional.setEnabled(false);
        //   this.jBGuardarHExtranjero.setEnabled(false);
    }//GEN-LAST:event_jTFCorreoKeyPressed

    private void jTFCorreoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFCorreoMouseClicked
        //this.jBGuardarHNacional.setEnabled(false);
        //this.jBGuardarHExtranjero.setEnabled(false);

        if (validadorDeTelefono(this.jTFTelefono.getText()) == true) {
            this.jTFCorreo.requestFocus();
        } else {
            getToolkit().beep();
            JOptionPane.showMessageDialog(null, "Teléfono no válido");
            this.jTFTelefono.requestFocus();
        }
    }//GEN-LAST:event_jTFCorreoMouseClicked

    private void jTFCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFCedulaMouseClicked
        //this.jTFPasaporte.setText("");
        //this.jTFPasaporte.setEnabled(false);
        //this.jBGuardarHExtranjero.setEnabled(false);
        // habilitarBoton();
        //this.jBGuardarHNacional.setEnabled(false);

    }//GEN-LAST:event_jTFCedulaMouseClicked

    private void jBRegistrarPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBRegistrarPersonalMouseClicked

    }//GEN-LAST:event_jBRegistrarPersonalMouseClicked

    private void jTFApellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFApellidosMouseClicked
        //this.jBGuardarHNacional.setEnabled(false);
        //   this.jBGuardarHExtranjero.setEnabled(false);
    }//GEN-LAST:event_jTFApellidosMouseClicked

    private void jTFTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTFTelefonoMouseClicked
        // this.jBGuardarHNacional.setEnabled(false);
        //    this.jBGuardarHExtranjero.setEnabled(false);
    }//GEN-LAST:event_jTFTelefonoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLNombre;
    private javax.swing.JButton jBRegistrarPersonal;
    private javax.swing.JComboBox<String> jComboBoxPer;
    private javax.swing.JLabel jLApellidos;
    private javax.swing.JLabel jLAvisoApellido;
    private javax.swing.JLabel jLAvisoCedula;
    private javax.swing.JLabel jLAvisoCorreo;
    private javax.swing.JLabel jLAvisoNombre;
    private javax.swing.JLabel jLAvisoTelefono;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLNombres;
    private javax.swing.JLabel jLTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelTablaH;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFCedula;
    private javax.swing.JTextField jTFCorreo;
    private javax.swing.JTextField jTFNombre;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTableHuesped;
    // End of variables declaration//GEN-END:variables
}
