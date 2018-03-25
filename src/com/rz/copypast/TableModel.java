/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.copypast;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author developer
 */
public class TableModel extends AbstractTableModel {

    private String[] names = {"1", "2", "3", "4", "5"};
    private String[][] values = new String[5][5];

    public TableModel() {
        values = new String[10][names.length];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < names.length; col++) {
                values[row][col] = String.valueOf((char) ((row * names.length) + col + 65));
            }
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (value instanceof Double || value instanceof Integer) {
            values[row][col] = value.toString();
        } else {
            values[row][col] = (String) value;
        }

        fireTableCellUpdated(row, col);
    }

    @Override
    public int getRowCount() {
        return values.length;
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return values[rowIndex][columnIndex];
    }
}