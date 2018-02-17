
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class FbPageViewCounter {

    public static final String DB_NAME_LOCAL = "app-dir/main-app-system.sqlite3";
    public static final String DB_NAME_ONLINE = "app-dir/online-app-system.sqlite3";
    private SQLiteConnection sQLiteConnectionLocal;
    private SQLiteConnection sQLiteConnectionOnlie;
    private Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String sqlQuery = "";

    public static void main(String args[]) {
        FbPageViewCounter fbPageViewCounter = new FbPageViewCounter();
        fbPageViewCounter.getOnlineData();
    }

    private void getOnlineData() {
        if (sQLiteConnectionOnlie == null) {
            openDatabaseOnline();
            sqlQuery = "SELECT * FROM tbtmp_fb_obscene_dirt ORDER BY fobsd_dirt_slug ASC;";
            ResultSet resultSet = sQLiteConnectionOnlie.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        long colId = resultSet.getLong("fobsd_dirt_id");
                        String colSlug = resultSet.getString("fobsd_dirt_slug");
                        long colViewCount = resultSet.getLong("fobsd_dirt_view_count");
                        long colSessionCount = resultSet.getLong("fobsd_dirt_session_count");
                        //System.out.println(colSlug + " SLUG: " + colViewCount + " S: " + colSessionCount);
                        onModifyLocalData(colId, colViewCount, colSessionCount);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabaseOnline();
        } else {
            closeDatabaseOnline();
            getOnlineData();
        }
    }

    private void onModifyLocalData(long argId, long argViewCount, long argSessionCount) {
        if (sQLiteConnectionLocal == null) {
            openDatabaseLocal();
            sqlQuery = "SELECT * FROM tbtmp_fb_obscene_dirt WHERE fobsd_dirt_id = '" + argId + "' ORDER BY fobsd_dirt_slug ASC;";
            ResultSet resultSet = sQLiteConnectionLocal.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        long colId = resultSet.getLong("fobsd_dirt_id");
                        String colSlug = resultSet.getString("fobsd_dirt_slug");
                        long colViewCount = resultSet.getLong("fobsd_dirt_view_count");
                        long colSessionCount = resultSet.getLong("fobsd_dirt_session_count");
                        System.out.println(colSlug + " SLUG: "
                                + colViewCount + " S: " + colSessionCount + " -- "
                                + argViewCount + " S: " + argSessionCount);
                    }
                }
                sqlQuery = "UPDATE tbtmp_fb_obscene_dirt "
                        + "SET fobsd_dirt_view_count = '%s', "
                        + "fobsd_dirt_session_count = '%s' "
                        + "WHERE fobsd_dirt_id = '%s';";
                sqlQuery = String.format(sqlQuery, argViewCount, argSessionCount, argId);
                //System.out.println(sqlQuery);
                sQLiteConnectionLocal.onExecuteQuery(sqlQuery);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabaseLocal();
        } else {
            closeDatabaseLocal();
            onModifyLocalData(argId, argViewCount, argSessionCount);
        }
    }

    private void openDatabaseLocal() {
        //sQLiteConnectionLocal = SQLiteConnection.getInstance(DB_NAME_LOCAL);
        sQLiteConnectionLocal = new SQLiteConnection(DB_NAME_LOCAL);
        Connection conn = sQLiteConnectionLocal.onOpenConnection();
    }

    private void closeDatabaseLocal() {
        sQLiteConnectionLocal.onCloseStatement();
        sQLiteConnectionLocal.onClose();
        sQLiteConnectionLocal = null;
    }

    private void openDatabaseOnline() {
        //sQLiteConnectionOnlie = SQLiteConnection.getInstance(DB_NAME_ONLINE);
        sQLiteConnectionOnlie = new SQLiteConnection(DB_NAME_ONLINE);
        Connection conn = sQLiteConnectionOnlie.onOpenConnection();
    }

    private void closeDatabaseOnline() {
        sQLiteConnectionOnlie.onCloseStatement();
        sQLiteConnectionOnlie.onClose();
        sQLiteConnectionOnlie = null;
    }
}
