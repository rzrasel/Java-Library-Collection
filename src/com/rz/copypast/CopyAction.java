/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.copypast;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTable;

/**
 *
 * @author developer
 */
public class CopyAction extends AbstractAction {

    private JTable table;

    public CopyAction(JTable table) {
        this.table = table;
        putValue(NAME, "Copy");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();

        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(new CellTransferable(table.getValueAt(row, col)), null);

    }

}
