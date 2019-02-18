
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author developer
 */
public class LanguageClassGen {

    public static final String DB_NAME = "app-jar-libs/db-cattle-shurjohms.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    Map<String, String> dbBngLangMap = new TreeMap<>();
    Map<String, String> dbEngLangMap = new TreeMap<>();

    public static void main(String args[]) {
        new LanguageClassGen().onDbDataProcess();
    }

    private void onDbDataProcess() {
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = " SELECT * FROM tbl_form_label AS form_label "
                    + " INNER JOIN tbl_language AS language ON language.lan_id = form_label.lan_id "
                    + " ORDER BY form_label.frm_label_key ASC, form_label.lan_id ASC; ";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String lanISO1Code;
                        String formLabelKey;
                        String formLabelValue;
                        lanISO1Code = resultSet.getString("lan_iso1_code").trim();
                        formLabelKey = resultSet.getString("frm_label_key").trim();
                        formLabelValue = resultSet.getString("frm_label_value").trim();
                        if (lanISO1Code.equalsIgnoreCase("bn")) {
                            dbBngLangMap.put(formLabelKey, formLabelValue);
                        } else if (lanISO1Code.equalsIgnoreCase("en")) {
                            dbEngLangMap.put(formLabelKey, formLabelValue);
                        }
                    }
                    onGenerate();
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            onDbDataProcess();
        }
        System.out.println("\n\n\n");
        onFormatSql();
    }

    private void onGenerate() {
        //HashMap<String, String> bngLangMap = new HashMap<>();
        //HashMap<String, String> engLangMap = new HashMap<>();
        System.out.println("public class AppLanguage {" + "\n"
                + "    public static HashMap<String, HashMap<String, String>> languageMap = new HashMap<>();");
        System.out.println("public AppLanguage() {\n"
                + "    lanLoadBangla();\n"
                + "    lanLoadEnglish();\n"
                + "  }");
        if (dbBngLangMap.size() > 0) {
            System.out.println("private void lanLoadBangla() {\n"
                    + "    HashMap<String, String> propertyTempMap = new HashMap<>();");
            for (Map.Entry<String, String> entry : dbBngLangMap.entrySet()) {
                System.out.println("propertyTempMap.put(\"" + entry.getKey() + "\", \"" + entry.getValue() + "\");");
            }
            System.out.println("languageMap.put(Name.BANGLA.getValue(), propertyTempMap);");
            System.out.println("}");
        }
        if (dbEngLangMap.size() > 0) {
            System.out.println("private void lanLoadEnglish() {\n"
                    + "    HashMap<String, String> propertyTempMap = new HashMap<>();");
            Iterator iterator = dbEngLangMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                System.out.println("propertyTempMap.put(\"" + pair.getKey() + "\", \"" + pair.getValue() + "\");");
            }
            System.out.println("languageMap.put(Name.ENGLISH.getValue(), propertyTempMap);");
            System.out.println("}");
        }
        System.out.println("public String getValue(Name argName, String argKey) {\n"
                + "    if (languageMap.containsKey(argName.getValue())) {\n"
                + "      HashMap<String, String> propertyTempMap = languageMap.get(argName.getValue());\n"
                + "      if (propertyTempMap.containsKey(argKey)) {\n"
                + "        return propertyTempMap.get(argKey);\n"
                + "      }\n"
                + "    }\n"
                + "    return null;\n"
                + "  }");
        System.out.println("public enum Name {\n"
                + "        BANGLA(\"bn\"), ENGLISH(\"en\");\n"
                + "        private String value;\n"
                + "\n"
                + "        Name(String argValue) {\n"
                + "            value = argValue;\n"
                + "        }\n"
                + "\n"
                + "        public String getValue() {\n"
                + "            return value;\n"
                + "        }\n"
                + "    }");
        System.out.println("\n}");
    }

    private void onFormatSql() {
        String newId = "";
        String insertSqlData = "INSERT INTO tbl_form_label "
                + " VALUES (%s, %s, '%s', '%s');";
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = " SELECT * FROM tbl_form_label "
                    + " ORDER BY frm_label_key ASC, lan_id ASC; ";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    System.out.println("DELETE FROM tbl_form_label;");
                    int whileCount = 1;
                    while (resultSet.next()) {
                        String newInsertSql = "";
                        String formLanId;
                        String formLabelKey;
                        String formLabelValue;
                        formLanId = resultSet.getString("lan_id").trim();
                        newId = RandomValue.getRandId(1111, 9999);
                        formLabelKey = resultSet.getString("frm_label_key").trim();
                        formLabelValue = resultSet.getString("frm_label_value").trim();
                        newInsertSql = String.format(insertSqlData,
                                formLanId, newId, formLabelKey, formLabelValue);
                        System.out.println(newInsertSql);
                        if (whileCount % 10 == 0) {
                            System.out.println();
                        }
                        whileCount++;
                        Thread.sleep(200);
                    }
                    System.out.println("\n\n\n");
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            onFormatSql();
        }
    }

    private void onGenerateFake() {
        if (dbBngLangMap.size() > 0) {
            for (Map.Entry<String, String> entry : dbBngLangMap.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            System.out.println("");
        }
        if (dbEngLangMap.size() > 0) {
            Iterator iterator = dbEngLangMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                System.out.println(pair.getKey() + " - " + pair.getValue());
            }
            System.out.println("");
        }
        System.out.println("\n");
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
