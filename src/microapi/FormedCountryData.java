/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microapi;

import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tools.Utils;

/**
 *
 * @author Rz Rasel
 */
public class FormedCountryData {

    //private static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private static final String DB_NAME = "db-generated-db-table.sqlite3";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";

    public static void main(String args[]) {
        FormedCountryData formedCountryData = new FormedCountryData();
        formedCountryData.onPopulate();
    }

    private void onPopulate() {
        String currentDate = simpleDateFormat.format(new Date());
        System.out.println("DEBUG: " + currentDate);
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM apps_countries_detailed ORDER BY countryName ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    int counter = 0;
                    System.out.println("\n\n");
                    System.out.println("DELETE FROM tbtmp_country;");
                    while (resultSet.next()) {
                        String newId = RandomValue.getRandId(1111, 9999);
                        String colCountryName = resultSet.getString("countryName").trim();
                        String colCountryCode = resultSet.getString("countryCode").trim();
                        String colIsoAlpha3 = resultSet.getString("isoAlpha3").trim();
                        String colIsoNumeric = resultSet.getString("isoNumeric").trim();
                        String colCapital = resultSet.getString("capital").trim();
                        String colContinentName = resultSet.getString("continentName").trim();
                        String colNorth = resultSet.getString("north").trim();
                        String colSouth = resultSet.getString("south").trim();
                        String colEast = resultSet.getString("east").trim();
                        String colWest = resultSet.getString("west").trim();
                        colCountryName = Utils.getDbFromat(colCountryName);
                        colCountryCode = Utils.getDbFromat(colCountryCode);
                        colIsoAlpha3 = Utils.getDbFromat(colIsoAlpha3);
                        colIsoNumeric = Utils.getDbFromat(colIsoNumeric);
                        colCapital = Utils.getDbFromat(colCapital);
                        colContinentName = Utils.getDbFromat(colContinentName);
                        colNorth = Utils.getDbFromat(colNorth);
                        colSouth = Utils.getDbFromat(colSouth);
                        colEast = Utils.getDbFromat(colEast);
                        colWest = Utils.getDbFromat(colWest);
                        String insertSqlData = "INSERT INTO tbtmp_country "
                                + " VALUES ("
                                + "%s, %s, %s, %s,"
                                + "%s, %s, %s, %s,"
                                + "%s, %s, %s,"
                                + " '%s', '%s'"
                                + ");";
                        String newInsertSql = String.format(insertSqlData,
                                newId, colCountryName, colCountryCode, colIsoAlpha3,
                                colIsoNumeric, colCapital, colContinentName, colNorth,
                                colSouth, colEast, colWest,
                                currentDate, currentDate);
                        if (Utils.isUnicode(colCountryName) || Utils.isUnicode(colCapital + "") || Utils.isUnicode(colContinentName + "")) {
                            System.out.println("" + newInsertSql);
                            counter++;
                            if (counter % 5 == 0) {
                                System.out.println("");
                            }
                        }
                        /*counter++;
                        if (counter % 5 == 0) {
                            System.out.println("");
                        }*/
                        Thread.sleep(10);
                    }
                    System.out.println("\n\n");
                    System.out.println("TOTAL: " + counter);
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            onPopulate();
        }
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
//https://github.com/raramuridesign/mysql-country-list/blob/master/mysql-country-list-detailed-info.sql
