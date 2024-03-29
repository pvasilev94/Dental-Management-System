/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dental.surgery.management.system.frame;

import dental.surgery.management.system.process.Processor;

/**
 *
 *
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        mainPanel1 = new dental.surgery.management.system.panel.MainPanel();
        procedurePanel1 = new dental.surgery.management.system.panel.ProcedurePanel();
        jPanel1 = new javax.swing.JPanel();
        allPatientsReportButton = new javax.swing.JButton();
        allPatientConditionReportButton = new javax.swing.JButton();
        saveAndQuitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addTab("Patient", mainPanel1);
        jTabbedPane1.addTab("Procedures", procedurePanel1);

        allPatientsReportButton.setText("All Patients Report");
        allPatientsReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPatientsReportButtonActionPerformed(evt);
            }
        });

        allPatientConditionReportButton.setText("All Patients with condition");
        allPatientConditionReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allPatientConditionReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(allPatientsReportButton)
                .addGap(55, 55, 55)
                .addComponent(allPatientConditionReportButton)
                .addContainerGap(172, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(allPatientsReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allPatientConditionReportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reports", jPanel1);

        saveAndQuitButton.setText("Save & Quit");
        saveAndQuitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAndQuitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveAndQuitButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(saveAndQuitButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveAndQuitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAndQuitButtonActionPerformed
        Processor.commit();
        System.exit(0);
    }//GEN-LAST:event_saveAndQuitButtonActionPerformed

    private void allPatientsReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPatientsReportButtonActionPerformed
       Processor.generateReportWithAllPatients();
    }//GEN-LAST:event_allPatientsReportButtonActionPerformed

    private void allPatientConditionReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allPatientConditionReportButtonActionPerformed
         Processor.generateReportWithCondition();
    }//GEN-LAST:event_allPatientConditionReportButtonActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allPatientConditionReportButton;
    private javax.swing.JButton allPatientsReportButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private dental.surgery.management.system.panel.MainPanel mainPanel1;
    private dental.surgery.management.system.panel.ProcedurePanel procedurePanel1;
    private javax.swing.JButton saveAndQuitButton;
    // End of variables declaration//GEN-END:variables
}
