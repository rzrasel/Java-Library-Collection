/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author Rz Rasel
 */
public class UIDashboard extends javax.swing.JFrame {

    public UIDashboard uiDashboard;

    /**
     * Creates new form UIDashboard
     */
    public UIDashboard() {
        initComponents();
        this.setTitle("Dashboard");
        uiDashboard = this;
        //this.setSize(150, 150);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jMenuItemAddNewTable.addActionListener(new OnMenuItemActionListener());
        jMenuItemAddNewColumn.addActionListener(new OnMenuItemActionListener());
        jMenuItemAddNewConstraint.addActionListener(new OnMenuItemActionListener());
    }

    private class OnMenuItemActionListener implements java.awt.event.ActionListener {

        public void actionPerformed(java.awt.event.ActionEvent argActionEvent) {
            if (argActionEvent.getSource() == jMenuItemAddNewTable) {
                System.out.println("Menu Item Add New Table clicked");
                UIAddNewTable uiAddNewTable = new UIAddNewTable();
                uiAddNewTable.setLocationRelativeTo(uiDashboard);
                uiAddNewTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                uiAddNewTable.setResizable(false);
                //removeMinMaxClose(jFAppAddProjects);
                //uiAddNewTable.addWindowListener(getWindowAdapter(uiAddNewTable));
                //frame.pack();
                ModalFrameUtil.showAsModal(uiAddNewTable, uiDashboard);
            } else if (argActionEvent.getSource() == jMenuItemAddNewColumn) {
                System.out.println("Menu Item Add New Table clicked");
                UIAddNewColumn uiAddNewColumn = new UIAddNewColumn();
                uiAddNewColumn.setLocationRelativeTo(uiDashboard);
                uiAddNewColumn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                uiAddNewColumn.setResizable(false);
                //uiAddNewColumn.addWindowListener(getWindowAdapter(uiAddNewColumn));
                ModalFrameUtil.showAsModal(uiAddNewColumn, uiDashboard);
            } else if (argActionEvent.getSource() == jMenuItemAddNewConstraint) {
                System.out.println("Menu Item Add New Table clicked");
                UIAddNewConstraint uiAddNewConstraint = new UIAddNewConstraint();
                uiAddNewConstraint.setLocationRelativeTo(uiDashboard);
                uiAddNewConstraint.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                uiAddNewConstraint.setResizable(false);
                //uiAddNewConstraint.addWindowListener(getWindowAdapter(uiAddNewConstraint));
                ModalFrameUtil.showAsModal(uiAddNewConstraint, uiDashboard);
            }
        }
    }

    private WindowAdapter getWindowAdapter(JFrame argJFrame) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we);
                //JOptionPane.showMessageDialog(frame, "Cant Exit");
            }

            @Override
            public void windowIconified(WindowEvent we) {
                argJFrame.setState(JFrame.NORMAL);
                //JOptionPane.showMessageDialog(frame, "Cant Minimize");
                System.out.println("CLICK_MIN_BUTTON");
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenuBarMain = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemAddNewTable = new javax.swing.JMenuItem();
        jMenuItemAddNewColumn = new javax.swing.JMenuItem();
        jMenuItemAddNewConstraint = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenuBarMain.add(jMenu1);

        jMenu2.setText("Option");

        jMenuItemAddNewTable.setText("Add New Table");
        jMenu2.add(jMenuItemAddNewTable);

        jMenuItemAddNewColumn.setText("Add New Column");
        jMenu2.add(jMenuItemAddNewColumn);

        jMenuItemAddNewConstraint.setText("Add New Constraint");
        jMenu2.add(jMenuItemAddNewConstraint);

        jMenuBarMain.add(jMenu2);

        setJMenuBar(jMenuBarMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(UIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBarMain;
    private javax.swing.JMenuItem jMenuItemAddNewColumn;
    private javax.swing.JMenuItem jMenuItemAddNewConstraint;
    private javax.swing.JMenuItem jMenuItemAddNewTable;
    // End of variables declaration//GEN-END:variables
}