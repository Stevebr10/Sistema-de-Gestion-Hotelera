/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package CasosDeUsos;

import CasoUsoHuesped.JIFActualizarHuespedes;
import CasoUsoHuesped.JIFConsultarHuesped;
import CasoUsoHuesped.JIFRegistrarHuesped;
import CasoUsoHuesped.JIFReservaHabitacion;
import javax.swing.JInternalFrame;

/**
 *
 * @author HP
 */
public class JIFcasosUsoHuesped extends javax.swing.JInternalFrame {

    /**
     * Creates new form jj
     */
    //JIFActualizarHuespedes jifActualizarH;
    //V1:
    JIFRegistrarHuesped jiHuespedV1;
    JIFActualizarHuespedes jifActualizarHV1;
    //JFHabitacionV1 jfHabitacionV1;
    JIFReservaHabitacion jifReservaHabi;
    JIFConsultarHuesped jifConsultarH;
    
    public JIFcasosUsoHuesped() {
        initComponents();
    }
    
     public void centrarVentanaI(JInternalFrame jfcentrar){
        int x1 = (jDeskHuesped.getWidth()/2)- jfcentrar.getWidth()/2;
        int y1 = (jDeskHuesped.getHeight()/2)- jfcentrar.getHeight()/2;
        if(jfcentrar.isShowing()){
            jfcentrar.setLocation(x1, y1);
        }else{
            jDeskHuesped.add(jfcentrar);
            jfcentrar.setLocation(x1, y1);
            jfcentrar.show();
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDeskHuesped = new javax.swing.JDesktopPane();
        jLabel7 = new javax.swing.JLabel();
        jBRegistrarHuesped = new javax.swing.JButton();
        jBActualizarHuesped = new javax.swing.JButton();
        jBHabitaciones = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jDeskHuesped.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 128, 131));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/home.png"))); // NOI18N
        jLabel7.setText("HUÉSPEDES");

        jBRegistrarHuesped.setText("REGISTRAR");
        jBRegistrarHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRegistrarHuespedActionPerformed(evt);
            }
        });

        jBActualizarHuesped.setText("ACTUALIZAR");
        jBActualizarHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBActualizarHuespedActionPerformed(evt);
            }
        });

        jBHabitaciones.setText("HABITACIONES");
        jBHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBHabitacionesActionPerformed(evt);
            }
        });

        jButton1.setText("CONSULTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDeskHuesped.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDeskHuesped.setLayer(jBRegistrarHuesped, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDeskHuesped.setLayer(jBActualizarHuesped, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDeskHuesped.setLayer(jBHabitaciones, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDeskHuesped.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDeskHuespedLayout = new javax.swing.GroupLayout(jDeskHuesped);
        jDeskHuesped.setLayout(jDeskHuespedLayout);
        jDeskHuespedLayout.setHorizontalGroup(
            jDeskHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDeskHuespedLayout.createSequentialGroup()
                .addGroup(jDeskHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jDeskHuespedLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDeskHuespedLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jBRegistrarHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jBActualizarHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(jBHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jDeskHuespedLayout.setVerticalGroup(
            jDeskHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDeskHuespedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jDeskHuespedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jBActualizarHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBRegistrarHuesped, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(320, Short.MAX_VALUE))
        );

        jPanel1.add(jDeskHuesped, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBRegistrarHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRegistrarHuespedActionPerformed
        /*if(!(jifHuesped instanceof JIFHuespedes)){
            jifHuesped = new JIFHuespedes();
        }
        centrarVentanaI(jifHuesped);*/
        if(!(jiHuespedV1 instanceof JIFRegistrarHuesped)){
            jiHuespedV1 = new JIFRegistrarHuesped();
        }
        centrarVentanaI(jiHuespedV1);

    }//GEN-LAST:event_jBRegistrarHuespedActionPerformed

    private void jBActualizarHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBActualizarHuespedActionPerformed
        if(!(jifActualizarHV1 instanceof JIFActualizarHuespedes)){
            jifActualizarHV1 = new JIFActualizarHuespedes();
        }
        centrarVentanaI(jifActualizarHV1);
    }//GEN-LAST:event_jBActualizarHuespedActionPerformed

    private void jBHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBHabitacionesActionPerformed
        if(!(jifReservaHabi instanceof JIFReservaHabitacion)){
            jifReservaHabi = new JIFReservaHabitacion();
        }
        centrarVentanaI(jifReservaHabi);
        //JFHabitacionV1 habitacion = new JFHabitacionV1();
        //habitacion.setVisible(true);
    }//GEN-LAST:event_jBHabitacionesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if(!(jifConsultarH instanceof JIFConsultarHuesped)){
            jifConsultarH = new JIFConsultarHuesped();
        }
        centrarVentanaI(jifConsultarH);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBActualizarHuesped;
    private javax.swing.JButton jBHabitaciones;
    private javax.swing.JButton jBRegistrarHuesped;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDeskHuesped;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
