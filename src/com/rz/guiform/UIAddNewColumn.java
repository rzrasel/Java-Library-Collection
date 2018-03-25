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
        this.setResizable(false);
        this.addWindowListener(getWindowAdapter(this));
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
                /*if (selectedIndex >= 0) {
                    //System.out.println("Value: " + modelTableDataList.get(selectedIndex).getTableName());
                    long colRowId = modelTableDataList.get(selectedIndex).getTableId();
                    sqlQuery = " SELECT * FROM tbl_column_property WHERE ttpro_id = " + colRowId + "; ";
                    //System.out.println(sqlQuery);
                    onPopulateTable(sqlQuery);
                }*/
                long colRowId = 0l;
                if (selectedIndex >= 0) {
                    colRowId = modelTableDataList.get(selectedIndex).getTableId();
                }
                sqlQuery = " SELECT * FROM tbl_column_property WHERE ttpro_id = " + colRowId + "; ";
                onPopulateTable(sqlQuery);
            }
        });
        jBtnSaveAll.addActionListener(new OnButtonActionListener());
        jBtnAddRow.addActionListener(new OnButtonActionListener());
        jBtnReload.addActionListener(new OnButtonActionListener());
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
        jTblColumnDetails.getColumnModel().getColumn(5).setMinWidth(60);
        jTblColumnDetails.getColumnModel().getColumn(5).setMaxWidth(60);
        jTblColumnDetails.getColumnModel().getColumn(5).setPreferredWidth(60);
        /*jTblColumnDetails.getColumnModel().getColumn(6).setMinWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(6).setMaxWidth(70);
        jTblColumnDetails.getColumnModel().getColumn(6).setPreferredWidth(70);*/
        //new ExcelAdapter(jTblColumnDetails);
        /*ActionListener listenerCopy = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                doCopy();
            }//end actionPerformed(ActionEvent)
        };
        final KeyStroke strokeCopy = KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK, false);
        jTblColumnDetails.registerKeyboardAction(listenerCopy, "Copy", strokeCopy, JComponent.WHEN_FOCUSED);

        ActionListener listenerPast = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                doPaste();
            }//end actionPerformed(ActionEvent)
        };

        final KeyStroke strokePast = KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK, false);
        jTblColumnDetails.registerKeyboardAction(listenerPast, "Paste", strokePast, JComponent.WHEN_FOCUSED);*/
 /*jTblColumnDetails = new JTable(new TableModel());
        final JPopupMenu pm = new JPopupMenu();
        pm.add(new CopyAction(jTblColumnDetails));
        pm.add(new PasteAction(jTblColumnDetails));
        jTblColumnDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    highlightRow(e);
                    doPopup(e);
                }
            }

            protected void doPopup(MouseEvent e) {
                pm.show(e.getComponent(), e.getX(), e.getY());
            }

            protected void highlightRow(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int col = table.columnAtPoint(point);

                table.setRowSelectionInterval(row, row);
                table.setColumnSelectionInterval(col, col);
            }
        });*/
    }

    /*private void doCopy() {
        int col = jTblColumnDetails.getSelectedColumn();
        int row = jTblColumnDetails.getSelectedRow();
        if (col != -1 && row != -1) {
            Object value = jTblColumnDetails.getValueAt(row, col);
            String data;
            if (value == null) {
                data = "";
            } else {
                data = value.toString();
            }//end if

            final StringSelection selection = new StringSelection(data);

            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }//end if
    }

    private void doPaste() {
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final Transferable content = clipboard.getContents(this);
        if (content != null) {
            try {
                final String value = content.getTransferData(DataFlavor.stringFlavor).toString();

                final int col = jTblColumnDetails.getSelectedColumn();
                final int row = jTblColumnDetails.getSelectedRow();
                if (jTblColumnDetails.isCellEditable(row, col)) {
                    jTblColumnDetails.setValueAt(value, row, col);
                    if (jTblColumnDetails.getEditingRow() == row && jTblColumnDetails.getEditingColumn() == col) {
                        final CellEditor editor = jTblColumnDetails.getCellEditor();
                        editor.cancelCellEditing();
                        jTblColumnDetails.editCellAt(row, col);
                    }//end if
                }//end if
                jTblColumnDetails.repaint();
            } catch (UnsupportedFlavorException e) {
                // String have to be the standard flavor
                System.err.println("UNSUPPORTED FLAVOR EXCEPTION " + e.getLocalizedMessage());
            } catch (IOException e) {
                // The data is consumed?
                System.err.println("DATA CONSUMED EXCEPTION " + e.getLocalizedMessage());
            }//end try
        }//end if
    }//end doPaste()
     */

 /*public void registerKeyboardAction(ActionListener anAction, String aCommand, KeyStroke aKeyStroke, int aCondition) {
        final InputMap inputMap = getInputMap(aCondition, true);
        if (inputMap != null) {
            final ActionMap actionMap = getActionMap(true);
            final ActionStandin action = new ActionStandin(anAction, aCommand);
            inputMap.put(aKeyStroke, action);
            if (actionMap != null) {
                actionMap.put(action, action);
            }
        }
    }*/
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
            } else if (argActionEvent.getSource() == jBtnAddRow) {
                System.out.println("Add Row Pressed");
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                if (selectedIndex > -1) {
                    long colRowId = Long.parseLong(RandomValue.getRandId(1111, 9999));
                    Object[] tblRow = {colRowId, null, null, null, null, null, null,};
                    DefaultTableModel tableModel = (DefaultTableModel) jTblColumnDetails.getModel();
                    tableModel.addRow(tblRow);
                    tableModel.fireTableDataChanged();
                } else {
                    System.out.println("Please select a table from combobox");
                }
            } else if (argActionEvent.getSource() == jBtnReload) {
                System.out.println("Reload Pressed");
                int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
                long colRowId = 0l;
                if (selectedIndex >= 0) {
                    colRowId = modelTableDataList.get(selectedIndex).getTableId();
                }
                sqlQuery = " SELECT * FROM tbl_column_property WHERE ttpro_id = " + colRowId + "; ";
                onPopulateTable(sqlQuery);
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

    private void onSaveData() {
        int selectedIndex = jComBoxTableName.getSelectedIndex() - 1;
        long colRowTableId = modelTableDataList.get(selectedIndex).getTableId();
        DefaultTableModel tableModel = (DefaultTableModel) jTblColumnDetails.getModel();
        int rowCount = tableModel.getRowCount();
        int columnCount = tableModel.getColumnCount();
        openDatabase();
        String tmpSql = "INSERT INTO tbl_column_property VALUES (%s, %s, %s, %s, %s, %s, %s, %s);";
        boolean isError = false;
        boolean isDbIdExists = false;
        boolean isDbNameExists = false;
        String newId = "";
        long colRowId;
        String colColName = "";
        String colColDataType = "";
        String colColLength = "";
        String colColIsNull = "";
        String colColNoPrefix = "";
        String colColComment = "";
        for (int row = 0; row < rowCount; row++) {
            isError = false;
            newId = RandomValue.getRandId(1111, 9999);
            colRowId = (Long) tableModel.getValueAt(row, 0);
            colColName = (String) tableModel.getValueAt(row, 1);
            colColDataType = (String) tableModel.getValueAt(row, 2);
            colColLength = (String) tableModel.getValueAt(row, 3);
            colColIsNull = (String) tableModel.getValueAt(row, 4);
            colColNoPrefix = (String) tableModel.getValueAt(row, 5);
            colColComment = (String) tableModel.getValueAt(row, 6);
            if (isNullValue(colColName)) {
                isError = true;
            }
            if (isNullValue(colColDataType)) {
                isError = true;
            }
            if (isNullValue(colColIsNull)) {
                colColIsNull = "0";
            }
            if (!isError) {
                colColName = removeSpace(colColName.trim(), " ");
                colColName = removeSpace(colColName.toLowerCase(), "_");
                colColDataType = removeSpace(colColDataType.trim(), "");
                colColDataType = colColDataType.toUpperCase();
            }
            isDbIdExists = isDbIdExists(colRowId);
            isDbNameExists = isDbColumnNameExists(colColName);
            if (!isError) {
                colColName = Utils.getDbFromat(colColName);
                colColDataType = Utils.getDbFromat(colColDataType);
                colColLength = Utils.getDbFromat(colColLength);
                colColIsNull = Utils.getDbFromat(colColIsNull);
                colColNoPrefix = Utils.getDbFromat(colColNoPrefix);
                colColComment = Utils.getDbFromat(colColComment);
                String formedDbColId = Utils.getDbFromat(colRowId + "");
                //System.out.println("FORMED_ID: " + formedDbColId);
                if (!isDbIdExists) {
                    sqlQuery = String.format(tmpSql, colRowTableId, newId, colColName, colColDataType, colColLength, colColIsNull, colColNoPrefix, colColComment);
                    //System.out.println(row + ") " + tmpSql);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteQuery(sqlQuery);
                } else if (!isDbNameExists) {
                    sqlQuery = " UPDATE tbl_column_property SET tcpro_col_name = %s, tcpro_col_dtype = %s, tcpro_length = %s, tcpro_is_null = %s, tcpro_no_prefix = %s, tcpro_col_comment = %s  WHERE ttpro_id = %s AND tcpro_id = %s ";
                    sqlQuery = String.format(sqlQuery, colColName, colColDataType, colColLength, colColIsNull, colColNoPrefix, colColComment, colRowTableId, colRowId);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                } else {
                    sqlQuery = " UPDATE tbl_column_property SET tcpro_col_dtype = %s, tcpro_length = %s, tcpro_is_null = %s, tcpro_no_prefix = %s, tcpro_col_comment = %s  WHERE ttpro_id = %s AND tcpro_id = %s ";
                    sqlQuery = String.format(sqlQuery, colColDataType, colColLength, colColIsNull, colColNoPrefix, colColComment, colRowTableId, colRowId);
                    System.out.println(sqlQuery);
                    sQLiteConnection.onExecuteRawQuery(sqlQuery);
                }
                //System.out.println("FORMED_ID: " + formedDbColId);
            }
        }
        closeDatabase();
        sqlQuery = " SELECT * FROM tbl_column_property WHERE ttpro_id = " + colRowTableId + "; ";
        onPopulateTable(sqlQuery);
    }

    private boolean isDbIdExists(long argDbId) {
        boolean retVal = false;
        //openDatabase();
        sqlQuery = " SELECT COUNT(*) AS total_row, * FROM tbl_column_property WHERE tcpro_id = '" + argDbId + "'; ";
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
        sqlQuery = "SELECT COUNT(*) AS total_row, * FROM tbl_column_property WHERE tcpro_col_name = '" + argDbTableName + "';";
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
        jTblColumnDetails = new javax.swing.JTable();
        jBtnSaveAll = new javax.swing.JButton();
        jBtnAddRow = new javax.swing.JButton();
        jBtnReload = new javax.swing.JButton();

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
        jTblColumnDetails.setShowGrid(true);
        jScrollPane1.setViewportView(jTblColumnDetails);

        jBtnSaveAll.setText("Save All");

        jBtnAddRow.setText("Add Row");

        jBtnReload.setText("Reload");

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
                        .addComponent(jBtnReload)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnAddRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSaveAll)
                    .addComponent(jBtnAddRow)
                    .addComponent(jBtnReload))
                .addContainerGap(15, Short.MAX_VALUE))
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
    private javax.swing.JButton jBtnAddRow;
    private javax.swing.JButton jBtnReload;
    private javax.swing.JButton jBtnSaveAll;
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
