/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.irfin.cafe;

import java.awt.Frame;
import javax.swing.UIManager;
import net.irfin.cafe.app.view.MainWindow;

/**
 *
 * @author Hansen
 */
public class MainApp {
    
    private static Frame mainWindowInstance;
    
    public static Frame getMainWindow() {
        return mainWindowInstance;
    }
    
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
        }
        catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.out);
        }
        //</editor-fold>
        
        /* Create and display the form */
        mainWindowInstance = new MainWindow();
        java.awt.EventQueue.invokeLater(() -> mainWindowInstance.setVisible(true));
        
//        java.awt.EventQueue.invokeLater(
//            new Runnable() {
//                @Override
//                public void run() {
//                    new MainWindow().setVisible(true);
//                }
//            }
//        );
    }
}
