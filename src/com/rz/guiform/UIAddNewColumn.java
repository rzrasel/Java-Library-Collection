/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Rz Rasel
 */
public class UIAddNewColumn extends javax.swing.JFrame {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    ArrayList<ModelTableData> modelTableDataList = new ArrayList<ModelTableData>();

    /**
     * Creates new form UIAddNewColumn
     */
    public UIAddNewColumn() {
        initComponents();
        this.setTitle("Add New Column");
        this.setLocationRelativeTo(null);
        onPopulateComboBox();
        /*jComBoxTableName.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent argItemEvent) {
                int state = argItemEvent.getStateChange();
                /--*System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");
                System.out.println("Item: " + argItemEvent.getItem());
                ItemSelectable is = argItemEvent.getItemSelectable();
                Object selected[] = is.getSelectedObjects();
                System.out.println(", Selected: " + selected[0]);
                System.out.println(comboBox.getSelectedItem().toString());*--/
                //System.err.println("\n");
                if (state == ItemEvent.SELECTED) {
                    System.out.println("Selected: " + jComBoxTableName.getSelectedItem());
                    System.out.print(", Position: " + jComBoxTableName.getSelectedIndex());
                }
            }
        });*/
        jComBoxTableName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                /*System.out.println("Selected: " + jComBoxTableName.getSelectedItem()
                        + ", Position: " + jComBoxTableName.getSelectedIndex());*/
                System.out.println("Index: " + selectedIndex);
                if (selectedIndex >= 0) {
                    System.out.println("Value: " + modelTableDataList.get(selectedIndex).getTableName());
                    long colRowId = modelTableDataList.get(selectedIndex).getTableId();
                    sqlQuery = " SELECT * FROM tbl_column_property WHERE ttpro_id = " + colRowId + "; ";
                    System.out.println(sqlQuery);
                    onPopulateTable(sqlQuery);
                }
            }
        });
        jTblColumnDetails.setRowHeight(28);
        jTblColumnDetails.setRowMargin(6);
        jTblColumnDetails.setIntercellSpacing(new Dimension(0, 0));
        jTblColumnDetails.setShowGrid(true);
        jTblColumnDetails.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        jTblColumnDetails.setGridColor(Color.decode("#9297A1"));
        TableColumn tableColumn0 = jTblColumnDetails.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(0);
        tableColumn0.setMaxWidth(0);
        tableColumn0.setPreferredWidth(0);
        for (int i = 0; i < jTblColumnDetails.getColumnCount(); i++) {
            jTblColumnDetails.getColumnModel().getColumn(i).setCellEditor(getCellEditor());
        }
        //jTblColumnDetails.getColumnModel().getColumn(1).setPreferredWidth(270);
        jTblColumnDetails.getColumnModel().getColumn(2).setMinWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(2).setMaxWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(3).setMinWidth(50);
        jTblColumnDetails.getColumnModel().getColumn(3).setMaxWidth(50);
        jTblColumnDetails.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTblColumnDetails.getColumnModel().getColumn(4).setMinWidth(45);
        jTblColumnDetails.getColumnModel().getColumn(4).setMaxWidth(45);
        jTblColumnDetails.getColumnModel().getColumn(4).setPreferredWidth(45);
        jTblColumnDetails.getColumnModel().getColumn(5).setMinWidth(55);
        jTblColumnDetails.getColumnModel().getColumn(5).setMaxWidth(55);
        jTblColumnDetails.getColumnModel().getColumn(5).setPreferredWidth(55);
        /*jTblColumnDetails.getColumnModel().getColumn(6).setMinWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(6).setMaxWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(6).setPreferredWidth(70);*/
    }

    private TableCellEditor getCellEditor() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#00c958")));
        return new DefaultCellEditor(textField);
    }

    private void onPopulateComboBox() {
        openDatabase();
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        try {
            if (resultSet != null) {
                modelTableDataList.clear();
                jComBoxTableName.addItem("Please Select");
                while (resultSet.next()) {
                    long colRowId = resultSet.getLong("ttpro_id");
                    String colName = resultSet.getString("ttpro_tbl_name");
                    String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                    String colColPrefix = resultSet.getString("ttpro_col_prefix");
                    String colColComment = resultSet.getString("ttpro_tbl_comment");
                    jComBoxTableName.addItem(colName);
                    modelTableDataList.add(ModelTableData.onGetSetRow(colRowId, colName));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
        closeDatabase();
    }

    private void onPopulateTable(String argSqlQuery) {
        closeDatabase();
        if (sQLiteConnection == null) {
            openDatabase();
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    DefaultTableModel tableModel = (DefaultTableModel) jTblColumnDetails.getModel();
                    tableModel.setRowCount(0);
                    while (resultSet.next()) {
                        long colRowId = resultSet.getLong("tcpro_id");
                        String colName = resultSet.getString("tcpro_col_name");
                        String colColDataType = resultSet.getString("tcpro_col_dtype");
                        String colColLength = resultSet.getString("tcpro_length");
                        String colColIsNull = resultSet.getString("tcpro_is_null");
                        String colColNoPrefix = resultSet.getString("tcpro_no_prefix");
                        String colColComment = resultSet.getString("tcpro_col_comment");
                        Object[] tblRow = {colRowId, colName, colColDataType, colColLength, colColIsNull, colColNoPrefix, colColComment,};
                        tableModel.addRow(tblRow);
                    }
                    tableModel.fireTableDataChanged();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            onPopulateTable(argSqlQuery);
        }
        closeDatabase();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComBoxTableName = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblColumnDetails = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setText("Table Name:");

        jTblColumnDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(146, 151, 161)));
        jTblColumnDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Data Type", "Length", "Is Null", "Is Prefix", "Comment"
            }
        ));
        jScrollPane1.setViewportView(jTblColumnDetails);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComBoxTableName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComBoxTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(UIAddNewColumn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIAddNewColumn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIAddNewColumn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIAddNewColumn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIAddNewColumn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComBoxTableName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblColumnDetails;
    // End of variables declaration//GEN-END:variables

    private void openDatabase() {
        sQLiteConnection = SQLiteConnection.getInstance(DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
    }

    private void closeDatabase() {
        if (sQLiteConnection != null) {
            sQLiteConnection.onCloseStatement();
            sQLiteConnection.onClose();
            sQLiteConnection = null;
        }
    }

    private String removeSpace(String argValue, String argReplaceBy) {
        return argValue.replaceAll("\\s+", argReplaceBy);
    }

    public static class ModelTableData {

        private long tableId;
        private String tableName;

        public ModelTableData(long argTableId, String argTableName) {
            this.tableId = argTableId;
            this.tableName = argTableName;
        }

        public long getTableId() {
            return this.tableId;
        }

        public void setTableId(long argTableId) {
            this.tableId = argTableId;
        }

        public String getTableName() {
            return this.tableName;
        }

        public void setTableName(String argTableName) {
            this.tableName = argTableName;
        }

        public static ModelTableData onGetSetRow(long argTableId, String argTableName) {
            return new ModelTableData(argTableId, argTableName);
        }
    }
}
