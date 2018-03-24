/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import tools.Utils;

/**
 *
 * @author Rz Rasel 2018-03-21.
 */
public class UIAddNewTable extends javax.swing.JFrame {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";

    /**
     * Creates new form UIAddNewTable
     */
    public UIAddNewTable() {
        initComponents();
        this.setTitle("Add New Table");
        this.setLocationRelativeTo(null);
        jBtnAddRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent argActionEvent) {
                System.out.println("Add Pressed");
                long colRowId = Long.parseLong(RandomValue.getRandId(1111, 9999));
                String colName = null;
                String colTblPrefix = null;
                String colColPrefix = null;
                String colColComment = null;
                Object[] tblRow = {colRowId, colName, colTblPrefix, colColPrefix, colColComment,};
                DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
                tableModel.addRow(tblRow);
                tableModel.fireTableDataChanged();
            }
        });
        jBtnSave.addActionListener(new OnButtonActionListener());
        jBtnReload.addActionListener(new OnButtonActionListener());
        jBtnDeleteRow.addActionListener(new OnButtonActionListener());
        jBtnPrint.addActionListener(new OnButtonActionListener());
        jBtnPrintNew.addActionListener(new OnButtonActionListener());
        jTableDetails.setRowHeight(28);
        jTableDetails.setRowMargin(6);
        jTableDetails.setGridColor(Color.decode("#9297A1"));
        jTableDetails.setIntercellSpacing(new Dimension(0, 0));
        TableColumn tableColumn0 = jTableDetails.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(0);
        tableColumn0.setMaxWidth(0);
        tableColumn0.setPreferredWidth(0);
        for (int i = 0; i < jTableDetails.getColumnCount(); i++) {
            jTableDetails.getColumnModel().getColumn(i).setCellEditor(getCellEditor());
        }
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
        onPopulateTable(sqlQuery);
        getTableValue();
    }

    private class OnButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent argActionEvent) {
            if (argActionEvent.getSource() == jBtnSave) {
                System.out.println("Save Pressed");
                onSaveData();
            } else if (argActionEvent.getSource() == jBtnReload) {
                System.out.println("Reload Pressed");
                sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
                onPopulateTable(sqlQuery);
            } else if (argActionEvent.getSource() == jBtnDeleteRow) {
                System.out.println("Delete Row Pressed");
                onDeleteSelectedTableRow();
            } else if (argActionEvent.getSource() == jBtnPrint) {
                System.out.println("Print Pressed");
                onPrintTableData(false);
            } else if (argActionEvent.getSource() == jBtnPrintNew) {
                System.out.println("Print All Pressed");
                onPrintTableData(true);
            }
        }
    }

    private TableCellEditor getCellEditor() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#00c958")));
        return new DefaultCellEditor(textField);
    }

    private void onPrintTableData(boolean argIsNewId) {
        openDatabase();
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        try {
            if (resultSet != null) {
                System.out.println("-- |----|");
                System.out.println("DELETE FROM tbl_table_property;");
                while (resultSet.next()) {
                    long newId = Long.parseLong(RandomValue.getRandId(1111, 9999));
                    long colRowId = resultSet.getLong("ttpro_id");
                    String colName = resultSet.getString("ttpro_tbl_name");
                    String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                    String colColPrefix = resultSet.getString("ttpro_col_prefix");
                    String colColComment = resultSet.getString("ttpro_tbl_comment");
                    if (argIsNewId) {
                        colRowId = newId;
                    }
                    colName = Utils.getDbFromat(colName);
                    colTblPrefix = Utils.getDbFromat(colTblPrefix);
                    colColPrefix = Utils.getDbFromat(colColPrefix);
                    colColComment = Utils.getDbFromat(colColComment);
                    sqlQuery = "INSERT INTO tbl_table_property VALUES (%s, %s, %s, %s, %s);";
                    sqlQuery = String.format(sqlQuery, colRowId, colName, colTblPrefix, colColPrefix, colColComment);
                    System.out.println(sqlQuery);
                    Thread.sleep(100);
                }
                System.out.println("-- |----|");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: " + e.toString());
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
                    DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
                    tableModel.setRowCount(0);
                    while (resultSet.next()) {
                        long colRowId = resultSet.getLong("ttpro_id");
                        String colName = resultSet.getString("ttpro_tbl_name");
                        String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                        String colColPrefix = resultSet.getString("ttpro_col_prefix");
                        String colColComment = resultSet.getString("ttpro_tbl_comment");
                        Object[] tblRow = {colRowId, colName, colTblPrefix, colColPrefix, colColComment,};
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

    private void onSaveData() {
        DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        //closeDatabase();
        openDatabase();
        for (int row = 0; row < rowCount; row++) {
            //Object[] tblRow = new Object[4];
            boolean isError = false;
            boolean isDbDataExists = false;
            boolean isDbIdExists = false;
            boolean isDbTableNameExists = false;
            String tmpSql = "INSERT INTO tbl_table_property VALUES (%s, %s, %s, %s, %s);";
            String newId = "";
            long colRowId;
            String colTblName = "";
            String colTblPrefix = "";
            String colColPrefix = "";
            String colTblComment = "";
            newId = RandomValue.getRandId(1111, 9999);
            colRowId = (Long) tableModel.getValueAt(row, 0);
            colTblName = (String) tableModel.getValueAt(row, 1);
            colTblPrefix = (String) tableModel.getValueAt(row, 2);
            colColPrefix = (String) tableModel.getValueAt(row, 3);
            colTblComment = (String) tableModel.getValueAt(row, 4);
            if (colTblName == null) {
                isError = true;
                System.out.println("ERROR NULL");
            } else if (colTblName.isEmpty()) {
                isError = true;
                System.out.println("ERROR EMPTY");
            }
            if (!isError) {
                colTblName = removeSpace(colTblName.trim(), " ");
                colTblName = removeSpace(colTblName.toLowerCase(), "_");
                colTblPrefix = removeSpace(colTblPrefix.trim(), " ");
                colTblPrefix = removeSpace(colTblPrefix.toLowerCase(), "_");
                colColPrefix = removeSpace(colColPrefix.trim(), " ");
                colColPrefix = removeSpace(colColPrefix.toLowerCase(), "_");
            }
            isDbIdExists = isDbIdExists(colRowId);
            isDbTableNameExists = isDbTableNameExists(colTblName);
            if (!isError) {
                colTblName = Utils.getDbFromat(colTblName);
                colTblPrefix = Utils.getDbFromat(colTblPrefix);
                colColPrefix = Utils.getDbFromat(colColPrefix);
                colTblComment = Utils.getDbFromat(colTblComment);
                String formedDbColId = Utils.getDbFromat(colRowId + "");
                //System.out.println("FORMED_ID: " + formedDbColId);
                if (!isDbIdExists) {
                    tmpSql = String.format(tmpSql, newId, colTblName, colTblPrefix, colColPrefix, colTblComment);
                    //System.out.println(row + ") " + tmpSql);
                    sqlQuery = tmpSql;
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteQuery(sqlQuery);
                } else if (!isDbTableNameExists) {
                    sqlQuery = " UPDATE tbl_table_property SET ttpro_tbl_name = %s, ttpro_tbl_prefix = %s, ttpro_col_prefix = %s, ttpro_tbl_comment = %s  WHERE ttpro_id = %s ";
                    sqlQuery = String.format(sqlQuery, colTblName, colTblPrefix, colColPrefix, colTblComment, formedDbColId);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                } else {
                    sqlQuery = " UPDATE tbl_table_property SET ttpro_tbl_prefix = %s, ttpro_col_prefix = %s, ttpro_tbl_comment = %s  WHERE ttpro_id = %s ";
                    sqlQuery = String.format(sqlQuery, colTblPrefix, colColPrefix, colTblComment, formedDbColId);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                }
                //System.out.println("FORMED_ID: " + formedDbColId);
            }
        }
        closeDatabase();
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
        onPopulateTable(sqlQuery);
    }

    private boolean isDbIdExists(long argDbId) {
        boolean retVal = false;
        //openDatabase();
        sqlQuery = " SELECT COUNT(*) AS total_row, * FROM tbl_table_property WHERE ttpro_id = '" + argDbId + "'; ";
        //System.out.println(sqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        if (resultSet != null) {
            try {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                int rowSize = 0;
                if (resultSet.next()) {
                    rowSize = resultSet.getInt("total_row");
                }
                if (rowSize > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
            }
        }
        //closeDatabase();
        return retVal;
    }

    private boolean isDbTableNameExists(String argDbTableName) {
        boolean retVal = false;
        //openDatabase();
        sqlQuery = "SELECT COUNT(*) AS total_row, * FROM tbl_table_property WHERE ttpro_tbl_name = '" + argDbTableName + "';";
        //System.out.println(sqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        if (resultSet != null) {
            try {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int numberOfColumns = rsmd.getColumnCount();
                int rowSize = 0;
                if (resultSet.next()) {
                    rowSize = resultSet.getInt("total_row");
                }
                if (rowSize > 0) {
                    retVal = true;
                }
            } catch (SQLException ex) {
            }
        }
        //closeDatabase();
        return retVal;
    }

    private void onDeleteSelectedTableRow() {
        int column = 0;
        int row = jTableDetails.getSelectedRow();
        String value = jTableDetails.getModel().getValueAt(row, column).toString();
        System.out.println("SELECTED VALUE: " + value);
        openDatabase();
        sqlQuery = "DELETE FROM tbl_table_property WHERE ttpro_id = '" + value + "';";
        sQLiteConnection.onExecuteRawQuery(sqlQuery);
        closeDatabase();
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
        onPopulateTable(sqlQuery);
    }

    private void getTableValue() {
        DefaultTableModel tableModel = (DefaultTableModel) jTableDetails.getModel();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Object cellValue = tableModel.getValueAt(i, j);
                //now display this value graphically.
                System.out.print(cellValue + "");
                if (j < columnCount - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("");
            //http://www.codejava.net/java-se/swing/editable-jtable-example
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDetails = new javax.swing.JTable();
        jBtnAddRow = new javax.swing.JButton();
        jBtnSave = new javax.swing.JButton();
        jBtnReload = new javax.swing.JButton();
        jBtnDeleteRow = new javax.swing.JButton();
        jBtnPrint = new javax.swing.JButton();
        jBtnPrintNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jTableDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(146, 151, 161)));
        jTableDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Table Prefix", "Column Prefix", "Comments"
            }
        ));
        jScrollPane1.setViewportView(jTableDetails);

        jBtnAddRow.setText("Add Row");

        jBtnSave.setText("Save All");

        jBtnReload.setText("Reload");

        jBtnDeleteRow.setText("Delete");

        jBtnPrint.setText("Print");

        jBtnPrintNew.setText("Print New");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnPrintNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnPrint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnDeleteRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnAddRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSave)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSave)
                    .addComponent(jBtnAddRow)
                    .addComponent(jBtnReload)
                    .addComponent(jBtnDeleteRow)
                    .addComponent(jBtnPrint)
                    .addComponent(jBtnPrintNew))
                .addContainerGap(52, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(UIAddNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIAddNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIAddNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIAddNewTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIAddNewTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAddRow;
    private javax.swing.JButton jBtnDeleteRow;
    private javax.swing.JButton jBtnPrint;
    private javax.swing.JButton jBtnPrintNew;
    private javax.swing.JButton jBtnReload;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDetails;
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
}
