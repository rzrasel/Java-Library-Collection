
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import com.rz.librarycore.logger.LogWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class AppPlayStoreDataFormat {
    
    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    
    public static void main(String args[]) {
        AppPlayStoreDataFormat appPlayStoreDataFormat = new AppPlayStoreDataFormat();
        appPlayStoreDataFormat.onDataFormat();
    }
    
    private void onDataFormat() {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(new Date());
        System.out.println("DEBUG: " + currentDate);
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbtmp_app_play_store ORDER BY apstre_aplstor_title ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    System.out.println("");
                    while (resultSet.next()) {
                        String insertSqlData = "INSERT INTO tbtmp_app_play_store "
                                + " VALUES ("
                                + "%s, %s, %s, '%s', '%s'"
                                + ");";
                        currentDate = formatter.format(new Date());
                        String colTitle = resultSet.getString("apstre_aplstor_title").trim();
                        String colDescription = resultSet.getString("apstre_aplstor_description").trim();
                        String newId = "";
                        newId = RandomValue.getRandId(1111, 9999);
                        newId = Utils.getDbFromat(newId);
                        colTitle = Utils.getDbFromat(Utils.toProperCase(colTitle));
                        LogWriter.Log("TEST: " + Utils.toSlugCase(colTitle));
                        colDescription = Utils.getDbFromat(colDescription);
                        String newInsertSql = String.format(insertSqlData,
                                newId,
                                colTitle,
                                colDescription,
                                currentDate,
                                currentDate
                        );
                        System.out.println(newInsertSql);
                        Thread.sleep(200);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            onDataFormat();
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
