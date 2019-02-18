
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel 2018-01-16.
 */
public class TestClass {

    public static void main(String args[]) {
        int steps = 4;
        int space = steps - 1;

        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            space--;
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
        space = 1;
        for (int i = 0; i < steps - 1; i++) {
            
            for (int j = 0; j < space; j++) {
                System.out.print(" ");
            }
            //space++;
            
            for (int j = 0; j < (steps - i) - 1; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void main_temp(String args[]) {
        String DB_NAME = "app-jar-libs/app-system.sqlite3";
        String sqlQuery = "";
        String appId = "";
        appId = RandomValue.getRandId(1111, 9999);
        System.out.println("New ID: " + appId);
        sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name";
        SQLiteConnection sQLiteConnection;
        sQLiteConnection = SQLiteConnection.getInstance(DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
        ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
        try {
            //resultSet.beforeFirst();
            if (resultSet != null) {
                while (resultSet.next()) {
                    System.out.println("DATA: " + resultSet.getString("ttpro_tbl_name"));
                }
            }
            sQLiteConnection.onCloseResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
        //onSqlQuery
        //sQLiteConnection.onExecuteRawQuery(sqlQuery);
        sQLiteConnection.onCloseStatement();
        sQLiteConnection.onClose();
    }
}
