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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import tools.Utils;

/**
 *
 * @author developer
 */
public class UIAddNewConstraint extends javax.swing.JFrame {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    ArrayList<ModelTableData> modelTableDataList = new ArrayList<ModelTableData>();

    /**
     * Creates new form UIAddNewConstraint
     */
    public UIAddNewConstraint() {
        initComponents();
        this.setTitle("Add New Costraint");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(getWindowAdapter(this));
        onPopulateComboBox();
        jComBoxTableName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                System.out.println("Index: " + selectedIndex);
                long colRowId = 0l;
                if (selectedIndex >= 0) {
                    colRowId = modelTableDataList.get(selectedIndex).getTableId();
                }
                sqlQuery = " SELECT * FROM tbl_column_property AS column_property "
                        + " LEFT OUTER JOIN tbl_constraint_property AS constraint_property ON constraint_property.tcpro_id = column_property.tcpro_id "
                        + " WHERE column_property.ttpro_id = " + colRowId + "; ";
                /*sqlQuery = " SELECT * FROM tbl_constraint_property AS constraint_property "
                        + " LEFT OUTER JOIN tbl_column_property AS column_property ON column_property.tcpro_id = constraint_property.tcpro_id "
                        + " WHERE column_property.ttpro_id = " + colRowId + "; ";*/
                System.out.println(sqlQuery);
                onPopulateTable(sqlQuery);
            }
        });
        jBtnSaveAll.addActionListener(new OnButtonActionListener());
        jBtnDeleteRow.addActionListener(new OnButtonActionListener());
        jTblConstraintDetails.setRowHeight(28);
        jTblConstraintDetails.setRowMargin(6);
        jTblConstraintDetails.setIntercellSpacing(new Dimension(0, 0));
        jTblConstraintDetails.setShowGrid(true);
        jTblConstraintDetails.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        jTblConstraintDetails.setGridColor(Color.decode("#9297A1"));
        TableColumn tableColumn0 = jTblConstraintDetails.getColumnModel().getColumn(0);
        tableColumn0.setMinWidth(0);
        tableColumn0.setMaxWidth(0);
        tableColumn0.setPreferredWidth(0);
        for (int i = 0; i < jTblConstraintDetails.getColumnCount(); i++) {
            jTblConstraintDetails.getColumnModel().getColumn(i).setCellEditor(getCellEditor());
        }
    }

    private class OnButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent argActionEvent) {
            if (argActionEvent.getSource() == jBtnSaveAll) {
                System.out.println("Save Pressed");
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                if (selectedIndex > -1) {
                    onSaveData();
                } else {
                    System.out.println("Please select a table from combobox");
                }
            } else if (argActionEvent.getSource() == jBtnDeleteRow) {
                System.out.println("Delete Row Pressed");
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                if (selectedIndex >= 0) {
                    onDeleteSelectedTableRow();
                }
            }
        }
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
                    DefaultTableModel tableModel = (DefaultTableModel) jTblConstraintDetails.getModel();
                    tableModel.setRowCount(0);
                    while (resultSet.next()) {
                        String newId = RandomValue.getRandId(1111, 9999);
                        long colColRowId = resultSet.getLong("tcpro_id");
                        String colColName = resultSet.getString("tcpro_col_name");
                        String colColDataType = resultSet.getString("tcpro_col_dtype");
                        String colColLength = resultSet.getString("tcpro_length");
                        String colColIsNull = resultSet.getString("tcpro_is_null");
                        String colColNoPrefix = resultSet.getString("tcpro_no_prefix");
                        String colColComment = resultSet.getString("tcpro_col_comment");
                        long colConstRowId = resultSet.getLong("tconp_id");
                        String colConstName = resultSet.getString("tconp_key");
                        String colConstRefTbl = resultSet.getString("tconp_ref_tbl");
                        String colConstPrefix = resultSet.getString("tconp_con_prefix");
                        if (!isNullValue(colConstRowId + "")) {
                            if (colConstRowId <= 0) {
                                colConstRowId = Long.parseLong(newId);
                            }
                        }
                        Object[] tblRow = {colConstRowId, colColRowId + "(" + colColName + ")", colConstName, colConstRefTbl, colConstPrefix,};
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
        int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
        long colRowTableId = modelTableDataList.get(selectedIndex).getTableId();
        DefaultTableModel tableModel = (DefaultTableModel) jTblConstraintDetails.getModel();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        openDatabase();
        String tmpSql = "INSERT INTO tbl_constraint_property VALUES (%s, %s, %s, %s, %s);";
        boolean isError = false;
        boolean isDbIdExists = false;
        long colRowId;
        String strColumnId;
        long colColumnId;
        String colConstName = "";
        String colConstRefTbl = "";
        String colConstPrefix = "";
        for (int row = 0; row < rowCount; row++) {
            colRowId = (Long) tableModel.getValueAt(row, 0);
            strColumnId = (String) tableModel.getValueAt(row, 1);
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(strColumnId);
            StringBuilder stringBuilder = new StringBuilder();
            while (matcher.find()) {
                //System.out.println(matcher.group());
                stringBuilder.append(matcher.group());
            }
            colColumnId = Long.valueOf(stringBuilder.toString());
            //System.out.println(colColumnId);
            colConstName = (String) tableModel.getValueAt(row, 2);
            colConstRefTbl = (String) tableModel.getValueAt(row, 3);
            colConstPrefix = (String) tableModel.getValueAt(row, 4);
            if (isNullValue(colConstName)) {
                isError = true;
            }
            if (!isError) {
                colConstName = removeSpace(colConstName.trim(), "");
                colConstName = colConstName.toUpperCase();
            }
            isDbIdExists = isDbIdExists(colRowId);
            if (!isError) {
                colConstName = Utils.getDbFromat(colConstName);
                colConstRefTbl = Utils.getDbFromat(colConstRefTbl);
                colConstPrefix = Utils.getDbFromat(colConstPrefix);
                if (!isDbIdExists) {
                    sqlQuery = String.format(tmpSql, colColumnId, colColumnId, colConstName, colConstRefTbl, colConstPrefix);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteQuery(sqlQuery);
                } else {
                    sqlQuery = " UPDATE tbl_constraint_property SET tconp_key = %s, tconp_ref_tbl = %s, tconp_con_prefix = %s WHERE tcpro_id = %s AND tconp_id = %s ";
                    sqlQuery = String.format(sqlQuery, colConstName, colConstRefTbl, colConstPrefix, colColumnId, colRowId);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                }
                //System.out.println("SQL: " + sqlQuery);
            }
        }
        closeDatabase();
        sqlQuery = " SELECT * FROM tbl_column_property AS column_property "
                + " LEFT OUTER JOIN tbl_constraint_property AS constraint_property ON constraint_property.tcpro_id = column_property.tcpro_id "
                + " WHERE column_property.ttpro_id = " + colRowTableId + "; ";
        onPopulateTable(sqlQuery);
    }

    private void onDeleteSelectedTableRow() {
        int column = 0;
        int row = jTblConstraintDetails.getSelectedRow();
        if (row < 0) {
            System.out.println("Please select the row you want to delete");
            return;
        }
        String idValue = jTblConstraintDetails.getModel().getValueAt(row, column).toString();
        System.out.println("SELECTED VALUE: " + idValue);
        openDatabase();
        sqlQuery = "DELETE FROM tbl_constraint_property WHERE tconp_id = '" + idValue + "';";
        sQLiteConnection.onExecuteRawQuery(sqlQuery);
        closeDatabase();
        int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
        long colRowTableId = modelTableDataList.get(selectedIndex).getTableId();
        sqlQuery = " SELECT * FROM tbl_column_property AS column_property "
                + " LEFT OUTER JOIN tbl_constraint_property AS constraint_property ON constraint_property.tcpro_id = column_property.tcpro_id "
                + " WHERE column_property.ttpro_id = " + colRowTableId + "; ";
        onPopulateTable(sqlQuery);
    }

    private boolean isDbIdExists(long argDbId) {
        boolean retVal = false;
        //openDatabase();
        sqlQuery = " SELECT COUNT(*) AS total_row, * FROM tbl_constraint_property WHERE tconp_id = '" + argDbId + "'; ";
        //System.out.println(sqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        if (resultSet != null) {
            try {
                int rowSize = 0;
                if (resultSet.next()) {
                    rowSize = resultSet.getInt("total_row");
                    if (rowSize > 0) {
                        retVal = true;
                    }
                }
            } catch (SQLException ex) {
            }
        }
        //closeDatabase();
        return retVal;
    }

    private boolean isDbColumnNameExists(String argDbTableName) {
        boolean retVal = false;
        //openDatabase();
        sqlQuery = "SELECT COUNT(*) AS total_row, * FROM tbl_constraint_property WHERE tcpro_col_name = '" + argDbTableName + "';";
        //System.out.println(sqlQuery);
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        if (resultSet != null) {
            try {
                int rowSize = 0;
                if (resultSet.next()) {
                    rowSize = resultSet.getInt("total_row");
                    if (rowSize > 0) {
                        retVal = true;
                    }
                }
            } catch (SQLException ex) {
            }
        }
        //closeDatabase();
        return retVal;
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
        jTblConstraintDetails = new javax.swing.JTable();
        jBtnSaveAll = new javax.swing.JButton();
        jBtnDeleteRow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Table Name:");

        jTblConstraintDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Column Id", "Constraint", "Ref Table", "Key Prefix"
            }
        ));
        jScrollPane1.setViewportView(jTblConstraintDetails);

        jBtnSaveAll.setText("Save All");

        jBtnDeleteRow.setText("Delete");

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
                        .addComponent(jComBoxTableName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnDeleteRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSaveAll)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComBoxTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSaveAll)
                    .addComponent(jBtnDeleteRow))
                .addContainerGap(24, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(UIAddNewConstraint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIAddNewConstraint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIAddNewConstraint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIAddNewConstraint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIAddNewConstraint().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDeleteRow;
    private javax.swing.JButton jBtnSaveAll;
    private javax.swing.JComboBox<String> jComBoxTableName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblConstraintDetails;
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

    private boolean isNullValue(String argStrValue) {
        boolean isNull = false;
        if (argStrValue == null) {
            isNull = true;
        } else if (argStrValue.isEmpty()) {
            isNull = true;
        }
        return isNull;
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

    private WindowAdapter getWindowAdapter(JFrame argJFrame) {
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                super.windowClosing(we);
            }

            @Override
            public void windowIconified(WindowEvent we) {
                argJFrame.setState(JFrame.NORMAL);
                //JOptionPane.showMessageDialog(frame, "Cant Minimize");
                System.out.println("Cant Minimize");
            }
        };
    }
}
