/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dental.surgery.management.system;

import dental.surgery.management.system.frame.MainFrame;
import dental.surgery.management.system.process.Processor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 *
 */
public class DentalSurgeryManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MainFrame frame = new MainFrame();
                    // frame.setIconImage(Toolkit.getDefaultToolkit().getImage(LOGO));
                    frame.setTitle("Dental surgery management system");
                    frame.setVisible(true);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DentalSurgeryManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Processor.initializeApplication();
                }
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
