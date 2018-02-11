
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import com.rz.librarycore.logger.LogWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class OnLoadFbPageData {

    public static final String DB_NAME = "app-dir/main-app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private String sqlQuery = "";
    private String rawTitle = "";
    private String rawDescription = "";

    public static void main(String args[]) {
        OnLoadFbPageData onLoadFbPageData = new OnLoadFbPageData();
        onLoadFbPageData.onReadFileData();
        onLoadFbPageData.onFillDatabase();
    }

    private void onReadFileData() {
        InputStream inputStream = null;
        StringBuilder stringBuilder = null;
        try {
            inputStream = new FileInputStream("app-dir/html-entity.txt");
            Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            int data = reader.read();
            stringBuilder = new StringBuilder();
            while (data != -1) {
                char theChar = (char) data;
                data = reader.read();
                stringBuilder.append(theChar);
            }
            reader.close();
            String fileRawData = "";
            fileRawData = stringBuilder.toString();
            String[] fileData = fileRawData.split("\\|\\|");
            //LogWriter.Log("DEBUG: " + fileData.length);
            if (fileData.length == 2) {
                rawTitle = fileData[0];
                rawDescription = fileData[1];
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OnLoadFbPageData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnLoadFbPageData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(OnLoadFbPageData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void onFillDatabase() {
        String databaseId = "";
        databaseId = RandomValue.getRandId(1111, 9999);
        //LogWriter.Log("DEBUG: " + databaseId);
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "INSERT INTO tbtmp_fb_obscene_dirt "
                    + " VALUES ("
                    + "%s, "
                    + "%s, %s, %s, "
                    + " %s, %s,"
                    + " %s, %s"
                    + ");";
            sqlQuery = String.format(sqlQuery,
                    databaseId,
                    "'" + rawTitle + "'", "'" + rawDescription + "'", "'n-0000'",
                    "'not-story'", "'ImageURL'",
                    "'0'", "'0'"
            );
            //LogWriter.Log("DEBUG: " + sqlQuery);
            sQLiteConnection.onExecuteQuery(sqlQuery);
            closeDatabase();
        } else {
            closeDatabase();
            onFillDatabase();
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
