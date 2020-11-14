/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;


/**
 *
 * @author user
 */
public class Registry extends javax.swing.JFrame {

    /** Creates new form Registry */
    public Registry() {
        initComponents();
    }
    
    public Registry(int maxDays) {
        initComponents();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        expiryDate.setMinSelectableDate(new Date());
        expiryDate.setDate(new Date());
        expiryDate.setMaxSelectableDate(Date.from(LocalDate.now().plusDays(maxDays).atStartOfDay(defaultZoneId).toInstant()));
    }   
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parkingRegistrationPanel = new javax.swing.JPanel();
        contactnoField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        contactNoLabel = new javax.swing.JLabel();
        emailidField = new javax.swing.JTextField();
        EmailIdLabel = new javax.swing.JLabel();
        NameLabel = new javax.swing.JLabel();
        vehicleIdLabel = new javax.swing.JLabel();
        vehicleIdField = new javax.swing.JTextField();
        resetFieldButton = new javax.swing.JButton();
        submitButton = new javax.swing.JButton();
        issueDatelabel = new javax.swing.JLabel();
        issueDate = new com.toedter.calendar.JDateChooser();
        expiryDateLabel = new javax.swing.JLabel();
        expiryDate = new com.toedter.calendar.JDateChooser();
        resetexpDatebutton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setResizable(false);

        parkingRegistrationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Parking Registration", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Yu Gothic UI Semibold", 1, 24), new java.awt.Color(0, 102, 204))); // NOI18N
        parkingRegistrationPanel.setForeground(new java.awt.Color(255, 255, 255));
        parkingRegistrationPanel.setEnabled(false);

        contactnoField.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        nameField.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        contactNoLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        contactNoLabel.setForeground(new java.awt.Color(51, 51, 51));
        contactNoLabel.setText("Contact No.");

        emailidField.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        EmailIdLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        EmailIdLabel.setForeground(new java.awt.Color(51, 51, 51));
        EmailIdLabel.setText("Email ID");

        NameLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        NameLabel.setForeground(new java.awt.Color(51, 51, 51));
        NameLabel.setText("Person's Name");

        vehicleIdLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        vehicleIdLabel.setForeground(new java.awt.Color(51, 51, 51));
        vehicleIdLabel.setText("Vehicle ID");

        vehicleIdField.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        resetFieldButton.setBackground(new java.awt.Color(255, 255, 255));
        resetFieldButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        resetFieldButton.setForeground(new java.awt.Color(51, 51, 51));
        resetFieldButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Reset.png"))); // NOI18N
        resetFieldButton.setText("Reset Fields");
        resetFieldButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        resetFieldButton.setFocusable(false);

        submitButton.setBackground(new java.awt.Color(255, 255, 255));
        submitButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        submitButton.setForeground(new java.awt.Color(51, 51, 51));
        submitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Done.png"))); // NOI18N
        submitButton.setText("Submit");
        submitButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        submitButton.setFocusable(false);

        issueDatelabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        issueDatelabel.setForeground(new java.awt.Color(51, 51, 51));
        issueDatelabel.setText("Issue Date");

        issueDate.setBackground(new java.awt.Color(255, 255, 255));
        issueDate.setDate(new Date());
        issueDate.setDateFormatString("yyyy-MM-dd");
        issueDate.setEnabled(false);
        issueDate.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N

        expiryDateLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        expiryDateLabel.setForeground(new java.awt.Color(51, 51, 51));
        expiryDateLabel.setText("Expiry Date");

        expiryDate.setDateFormatString("yyyy-MM-dd");

        resetexpDatebutton.setBackground(new java.awt.Color(255, 255, 255));
        resetexpDatebutton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 11)); // NOI18N
        resetexpDatebutton.setForeground(new java.awt.Color(51, 51, 51));
        resetexpDatebutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Reset.png"))); // NOI18N
        resetexpDatebutton.setText("Reset");
        resetexpDatebutton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        resetexpDatebutton.setFocusable(false);
        resetexpDatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetexpDatebuttonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(255, 255, 255));
        backButton.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(51, 51, 51));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/back.png"))); // NOI18N
        backButton.setText("Back");
        backButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        backButton.setFocusable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 51));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Denotes that these fields are compulsary.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 51));
        jLabel7.setText("*");

        org.jdesktop.layout.GroupLayout parkingRegistrationPanelLayout = new org.jdesktop.layout.GroupLayout(parkingRegistrationPanel);
        parkingRegistrationPanel.setLayout(parkingRegistrationPanelLayout);
        parkingRegistrationPanelLayout.setHorizontalGroup(
            parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parkingRegistrationPanelLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(expiryDateLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(issueDatelabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(NameLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(contactNoLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, parkingRegistrationPanelLayout.createSequentialGroup()
                                .add(EmailIdLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(parkingRegistrationPanelLayout.createSequentialGroup()
                                .add(vehicleIdLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(27, 27, 27)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(backButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(39, 39, 39)
                        .add(resetFieldButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(33, 33, 33)
                        .add(submitButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(vehicleIdField)
                            .add(nameField)
                            .add(issueDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(emailidField)
                            .add(expiryDate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(contactnoField))
                        .add(28, 28, 28)
                        .add(resetexpDatebutton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(66, 66, 66))))
            .add(parkingRegistrationPanelLayout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 298, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        parkingRegistrationPanelLayout.setVerticalGroup(
            parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(parkingRegistrationPanelLayout.createSequentialGroup()
                .add(26, 26, 26)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(33, 33, 33)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(vehicleIdLabel)
                    .add(vehicleIdField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(20, 20, 20)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(NameLabel)
                    .add(nameField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .add(25, 25, 25)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(contactNoLabel)
                    .add(contactnoField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(28, 28, 28)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(EmailIdLabel)
                    .add(emailidField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(30, 30, 30)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(issueDatelabel)
                    .add(issueDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(28, 28, 28)
                .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                .add(expiryDateLabel)
                                .add(jLabel4))
                            .add(expiryDate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 51, Short.MAX_VALUE)
                        .add(parkingRegistrationPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(submitButton)
                            .add(resetFieldButton)
                            .add(backButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(parkingRegistrationPanelLayout.createSequentialGroup()
                        .add(resetexpDatebutton)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(parkingRegistrationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(parkingRegistrationPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void resetexpDatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetexpDatebuttonActionPerformed
        // TODO add your handling code here:
        expiryDate.setDate(new Date());
    }//GEN-LAST:event_resetexpDatebuttonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registry().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailIdLabel;
    private javax.swing.JLabel NameLabel;
    public javax.swing.JButton backButton;
    private javax.swing.JLabel contactNoLabel;
    public javax.swing.JTextField contactnoField;
    public javax.swing.JTextField emailidField;
    public com.toedter.calendar.JDateChooser expiryDate;
    private javax.swing.JLabel expiryDateLabel;
    public com.toedter.calendar.JDateChooser issueDate;
    private javax.swing.JLabel issueDatelabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public javax.swing.JTextField nameField;
    public javax.swing.JPanel parkingRegistrationPanel;
    public javax.swing.JButton resetFieldButton;
    public javax.swing.JButton resetexpDatebutton;
    public javax.swing.JButton submitButton;
    public javax.swing.JTextField vehicleIdField;
    private javax.swing.JLabel vehicleIdLabel;
    // End of variables declaration//GEN-END:variables
//    implementationParking newPark = new implementationParking();
}
