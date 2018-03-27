/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.sitemeta;

import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import tools.Utils;

/**
 *
 * @author Rz Rasel 2018-03-27.
 */
public class SiteMeta {

    public static final String DB_NAME = "app-jar-libs/db-site-meta.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    private boolean isFirstConst = true;

    public static void main(String args[]) {
        SiteMeta siteMeta = new SiteMeta();
        siteMeta.getSetPrepare();
    }

    private void getSetPrepare() {
        openDatabase();
        sqlQuery = "SELECT * FROM tbl_site_page_meta_store ORDER BY spgmatr_pgmstore_meta_genre ASC, spgmatr_pgmstore_type ASC, spgmatr_pgmstore_name ASC;";
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        try {
            if (resultSet != null) {
                System.out.println("|----|------------|");
                System.out.println("");
                System.out.println("DELETE FROM tbl_site_page_meta_store;");
                while (resultSet.next()) {
                    String newId = RandomValue.getRandId(1111, 9999);
                    long colRowId = resultSet.getLong("spgmatr_pgmstore_id");
                    String colTblName = resultSet.getString("spgmatr_pgmstore_name");
                    String colTblDetails = resultSet.getString("spgmatr_pgmstore_details");
                    String colColType = resultSet.getString("spgmatr_pgmstore_type");
                    String colTblGenre = resultSet.getString("spgmatr_pgmstore_meta_genre");

                    colTblName = Utils.getDbFromat(colTblName);
                    colTblDetails = Utils.getDbFromat(colTblDetails);
                    colColType = Utils.getDbFromat(colColType);
                    colTblGenre = Utils.getDbFromat(colTblGenre);

                    sqlQuery = "INSERT INTO tbl_site_page_meta_store VALUES (%s, %s, %s, %s, %s);";
                    sqlQuery = String.format(sqlQuery, newId, colTblName, colTblDetails, colColType, colTblGenre);
                    System.out.println(sqlQuery);
                    Thread.sleep(100);
                }
                System.out.println("");
                System.out.println("|----|------------|");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        } catch (InterruptedException e) {
            System.out.println("SQLException: " + e.toString());
        }
        sQLiteConnection.onCloseResultSet(resultSet);
        closeDatabase();
    }

    private void openDatabase() {
        sQLiteConnection = SQLiteConnection.getInstance(DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
    }

    private void closeDatabase() {
        sQLiteConnection.onCloseStatement();
        sQLiteConnection.onClose();
        sQLiteConnection = null;
    }
}
