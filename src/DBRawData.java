
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel 2018-01-18.
 */
public class DBRawData {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";

    public static void main(String args[]) {
        DBRawData dbRawData = new DBRawData();
        dbRawData.getTableProperty();
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

    private void getTableProperty() {
        List<String> tblProIdList = new ArrayList<String>();
        HashMap<String, String> mapTblProId = new HashMap<String, String>();
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    System.out.println("");
                    System.out.println("DELETE FROM tbl_table_property;");
                    while (resultSet.next()) {
                        String tmpSql = "";
                        String newId = "";
                        long rowId = resultSet.getLong("ttpro_id");
                        String colTblName = resultSet.getString("ttpro_tbl_name");
                        String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                        String colColPrefix = resultSet.getString("ttpro_col_prefix");
                        String colTblComment = resultSet.getString("ttpro_comment");
                        newId = RandomValue.getRandId(1111, 9999);
                        newId = getDbFormat(newId);
                        colTblName = getDbFormat(colTblName);
                        colTblPrefix = getDbFormat(colTblPrefix);
                        colColPrefix = getDbFormat(colColPrefix);
                        colTblComment = getDbFormat(colTblComment);
                        tmpSql = "INSERT INTO tbl_table_property VALUES (%s, %s, %s, %s, %s);";
                        tmpSql = String.format(tmpSql, newId, colTblName, colTblPrefix, colColPrefix, colTblComment);
                        System.out.println(tmpSql);
                        //tblProIdList.add(rowId + "");
                        mapTblProId.put(rowId + "", newId);
                        Thread.sleep(20);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("SQLException: " + e.toString());
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            closeDatabase();
        } else {
            closeDatabase();
            getTableProperty();
        }
        if (mapTblProId.size() > 0) {
            System.out.println("");
            System.out.println("DELETE FROM tbl_column_property;");
            //for (Iterator<String> iterator = queryList.iterator(); iterator.hasNext();) -FOR LIST<STRING>
            //for (Map.Entry<String, String> entry : gfg.entrySet())
            for (Iterator<Map.Entry<String, String>> iterator = mapTblProId.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, String> element = iterator.next();
                getColumnProperty(element.getKey(), element.getValue());
            }
            //System.out.println(mapTblProId.size());
        }
    }

    private void getColumnProperty(String argOldId, String argNewId) {
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_column_property WHERE ttpro_id = '" + argOldId + "';";
            //System.out.println("SQL: " + sqlQuery);
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    //System.out.println("");
                    //System.out.println("DELETE FROM tbl_column_property;");
                    while (resultSet.next()) {
                        String tmpSql = "";
                        String newId = "";
                        newId = RandomValue.getRandId(1111, 9999);
                        long rowId = resultSet.getLong("ttpro_id");
                        String colColName = resultSet.getString("tcpro_col_name");
                        String colColDataType = resultSet.getString("tcpro_col_dtype");
                        String colLength = resultSet.getString("tcpro_length");
                        String colIsNull = resultSet.getString("tcpro_is_null");
                        String colNoPrefix = resultSet.getString("tcpro_no_prefix");
                        String colComment = resultSet.getString("tcpro_col_comment");
                        newId = getDbFormat(newId);
                        colColName = getDbFormat(colColName);
                        colColDataType = getDbFormat(colColDataType).toUpperCase();
                        colLength = getDbFormat(colLength);
                        colIsNull = getDbFormat(colIsNull);
                        colNoPrefix = getDbFormat(colNoPrefix);
                        colComment = getDbFormat(colComment);
                        tmpSql = "INSERT INTO tbl_column_property VALUES (%s, %s, %s, %s, %s, %s, %s, %s);";
                        tmpSql = String.format(tmpSql, argNewId, newId, colColName, colColDataType, colLength, colIsNull, colNoPrefix, colComment);
                        System.out.println(tmpSql);
                        Thread.sleep(20);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException ex) {
                Logger.getLogger(DBRawData.class.getName()).log(Level.SEVERE, null, ex);
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            closeDatabase();
        } else {
            closeDatabase();
            getColumnProperty(argOldId, argNewId);
        }
    }

    public String getDbFormat(String argData) {
        String retVal = null;
        if (argData != null) {
            argData = argData.trim();
            if (argData.isEmpty()) {
                retVal = null;
            } else {
                retVal = "'" + argData.trim() + "'";
            }
        } else {
            retVal = null;
        }
        return retVal;
    }
}
