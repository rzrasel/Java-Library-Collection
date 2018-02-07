
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import com.rz.librarycore.logger.LogWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

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

    public String convertStringToHex(String str) {

        char[] chars = str.toCharArray();

        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString();
    }

    public String convertHexToString(String hex) {

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }

    public static void main(String args[]) {
        AppPlayStoreDataFormat appPlayStoreDataFormat = new AppPlayStoreDataFormat();
        appPlayStoreDataFormat.onDataFormat();
    }

    private void onDataFormat() {
        boolean isPrintMySql = false;
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(new Date());
        System.out.println("DEBUG: " + currentDate);
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbtmp_app_play_store ORDER BY apstre_aplstor_title ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    System.out.println("\n\n");
                    while (resultSet.next()) {
                        currentDate = formatter.format(new Date());
                        String colTitle = resultSet.getString("apstre_aplstor_title").trim();
                        String colDescription = resultSet.getString("apstre_aplstor_description").trim();
                        String colSlug = resultSet.getString("apstre_aplstor_slug").trim();
                        String colImageURL = resultSet.getString("apstre_aplstor_image_url").trim();
                        String colApkURL = resultSet.getString("apstre_aplstor_apk_url").trim();
                        String colSEO = resultSet.getString("apstre_aplstor_seo").trim();
                        String colTAG = resultSet.getString("apstre_aplstor_tag").trim();
                        String colIsFeatured = resultSet.getInt("apstre_aplstor_is_featured") + "";
                        String colIsOwn = resultSet.getInt("apstre_aplstor_is_own") + "";
                        String colStatus = resultSet.getInt("apstre_aplstor_status") + "";
                        //String colCreateDate = resultSet.getString("apstre_aplstor_create_date").trim();
                        String newId = "";
                        newId = RandomValue.getRandId(1111, 9999);
                        newId = Utils.getDbFromat(newId);
                        //String escapedString = newInsertSql.replace("'", "''");
                        /*colTitle = colTitle.replace("'", "\\'");
                        colDescription = colDescription.replace("'", "\\'");*/
                        colSlug = Utils.toEmptyToNull(colSlug);
                        if (colSlug == null) {
                            colSlug = Utils.toSlugCase(colTitle + "");
                        } else {
                            colSlug = Utils.toSlugCase(colSlug + "");
                        }
                        if (isPrintMySql) {
                            colTitle = Utils.htmlEntityCode(Utils.toProperCase(colTitle));
                            colDescription = Utils.htmlEntityCode(colDescription);
                            colTitle = colTitle.replaceAll("&#32;", " ");
                            colDescription = colDescription.replaceAll("&#32;", " ");
                        } else {
                            //colTitle = colTitle.replaceAll("'","''");
                            String regexStr = "(?=[]\\[+&|!(){}^\"~*?:\\\\-])";
                            //colTitle = colTitle.replaceAll(regexStr, "\\\\");
                            //colDescription = colDescription.replaceAll(regexStr, "\\\\");
                            /*colTitle = Utils.urlEncode(colTitle);
                            colDescription = Utils.urlEncode(colDescription);*/
                            regexStr = "['\\+]";
                            colTitle = "+-*/\"\\";
                            colTitle = "\\";
                            colTitle = Utils.escapeSqlSpecial(colTitle);
                            //colDescription = Utils.escapeSqlSpecial(colDescription);
                        }
                        colTitle = Utils.getDbFromat(Utils.toProperCase(colTitle));
                        colDescription = Utils.getDbFromat(colDescription);
                        colSlug = Utils.getDbFromat(colSlug);
                        colImageURL = Utils.getDbFromat(colImageURL);
                        colApkURL = Utils.getDbFromat(colApkURL);
                        colSEO = Utils.getDbFromat(colSEO);
                        colTAG = Utils.getDbFromat(colTAG);
                        //LogWriter.Log("TEST: " + colTitle.substring(0, 6) + " - " + colSlug + "");
                        //LogWriter.Log("TEST: " + Utils.toSlugCase(colTitle + ""));
                        String newInsertSql = "";
                        if (isPrintMySql) {
                            String insertSqlData = "INSERT INTO tbtmp_app_play_store "
                                    + " VALUES ("
                                    + "%s, %s, %s, %s,"
                                    + " %s, %s, %s,"
                                    + " %s, %s, %s, %s,"
                                    + " '%s', '%s'"
                                    + ");";
                            newInsertSql = String.format(insertSqlData,
                                    newId, colTitle, colDescription, colSlug,
                                    colImageURL, colApkURL, colSEO,
                                    colTAG, colIsFeatured, colIsOwn, colStatus,
                                    currentDate, currentDate
                            );
                        } else {
                            String insertSqlData = "INSERT INTO tbtmp_app_play_store "
                                    + " VALUES ("
                                    + "%s, %s, %s, %s,"
                                    + " %s, %s, %s,"
                                    + " %s, %s, %s, %s"
                                    + ");";
                            newInsertSql = String.format(insertSqlData,
                                    newId, colTitle, colDescription, colSlug,
                                    colImageURL, colApkURL, colSEO,
                                    colTAG, colIsFeatured, colIsOwn, colStatus
                            );
                        }
                        System.out.println(newInsertSql);
                        Thread.sleep(200);
                    }
                    System.out.println("\n\n");
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
