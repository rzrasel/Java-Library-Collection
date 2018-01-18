
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    }

    private void getTableProperty() {
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
                        if (colTblComment != null) {
                            if (colTblComment.isEmpty()) {
                                colTblComment = null;
                            } else {
                                colTblComment = "'" + colTblComment + "'";
                            }
                        } else {
                            colTblComment = null;
                        }
                        tmpSql = "INSERT INTO tbl_table_property VALUES ('%s', '%s', '%s', '%s', %s);";
                        tmpSql = String.format(tmpSql, newId, colTblName, colTblPrefix, colColPrefix, colTblComment);
                        System.out.println(tmpSql);
                        Thread.sleep(20);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException ex) {
                Logger.getLogger(DBRawData.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeDatabase();
        } else {
            closeDatabase();
            getTableProperty();
        }
    }
}
