
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel 2018-01-16.
 */
public class SQLGenerate {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    private String defaultIsNull = "NOT NULL";
    private String finalSqlQuery = "";
    private String partOfSqlQuery = "";
    private List<String> queryList = new ArrayList<String>();
    private int gapSeparatorLen = 4;
    private String gapSeparatorValue = "";

    public static void main(String args[]) {
        String appId = "";
        appId = RandomValue.getRandId(1111, 9999);
        System.out.println("New ID: " + appId);
        new SQLGenerate().populatTable();
        appId = RandomValue.getRandId(1111, 9999);
        System.out.println("New ID: " + appId);
        appId = RandomValue.getRandId(1111, 9999);
        System.out.println("New ID: " + appId);
        appId = RandomValue.getRandId(1111, 9999);
        System.out.println("New ID: " + appId);
    }

    private void openDatabase() {
        sQLiteConnection = SQLiteConnection.getInstance(DB_NAME);
        Connection conn = sQLiteConnection.onOpenConnection();
    }

    private void closeDatabase() {
        sQLiteConnection.onCloseStatement();
        sQLiteConnection.onClose();
    }

    private void populatTable() {
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                //resultSet.beforeFirst();
                if (resultSet != null) {
                    while (resultSet.next()) {
                        long rowId = resultSet.getLong("ttpro_id");
                        String colName = resultSet.getString("ttpro_tbl_name");
                        String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                        String colColPrefix = resultSet.getString("ttpro_col_prefix");
                        colName = removeSpace(colName, "_").toLowerCase();
                        colName = colTblPrefix + "_" + colName;
                        partOfSqlQuery = "DROP TABLE IF EXISTS " + colName + ";";
                        queryList.add(partOfSqlQuery);
                        finalSqlQuery += partOfSqlQuery;
                        partOfSqlQuery = "CREATE TABLE IF NOT EXISTS " + colName;
                        queryList.add(partOfSqlQuery);
                        finalSqlQuery += partOfSqlQuery;
                        partOfSqlQuery = "(";
                        queryList.add(partOfSqlQuery);
                        finalSqlQuery += partOfSqlQuery;
                        //----System.out.println("DATA: " + colName);
                        populatColumn(rowId, colColPrefix);
                        partOfSqlQuery = ");";
                        queryList.add(partOfSqlQuery);
                        finalSqlQuery += partOfSqlQuery;
                    }
                    printSql();
                }
                sQLiteConnection.onCloseResultSet(resultSet);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            closeDatabase();
        } else {
            closeDatabase();
            populatTable();
        }
    }

    private void populatColumn(long argTblId, String argColPrefix) {
        ResultSet resultSet = null;
        int totalRows = 0;
        if (sQLiteConnection != null) {
            //sqlQuery = "SELECT * FROM tbl_column_property ORDER BY tcpro_col_name ASC";
            sqlQuery = " SELECT * FROM tbl_column_property "
                    + " WHERE ttpro_id = '" + argTblId + "'; ";
            //System.out.println("SQL: " + sqlQuery);
            resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                //resultSet.beforeFirst();
                if (resultSet != null) {
                    boolean needCheckConstraint = false;
                    boolean hasNext = resultSet.next();
                    if (hasNext) {
                        needCheckConstraint = true;
                    }
                    while (hasNext) {
                        long rowId = resultSet.getLong("tcpro_id");
                        String colName = resultSet.getString("tcpro_col_name");
                        String colType = resultSet.getString("tcpro_col_dtype").toUpperCase();
                        String colLength = resultSet.getString("tcpro_length");
                        boolean colIsNull = resultSet.getBoolean("tcpro_is_null");
                        boolean colIsOnPrefix = resultSet.getBoolean("tcpro_no_prefix");
                        colName = removeSpace(colName, "_");
                        if (!colIsOnPrefix) {
                            colName = argColPrefix + "_" + colName;
                        }
                        colName = colName.toLowerCase();
                        //-----System.out.println("DATA: " + colName);
                        if (!colLength.isEmpty()) {
                            colLength = "(" + colLength + ")";
                        }
                        if (colIsNull) {
                            defaultIsNull = "NULL";
                        }
                        //gapSeparatorLen
                        colType = colType + colLength;
                        partOfSqlQuery = repeat(":", 4)
                                + colName
                                + repeat(":", 24 - colName.length())
                                + colType
                                + repeat(":", 18 - colType.length())
                                + defaultIsNull;
                        //queryList.add(partOfSqlQuery);
                        //------------------CHECK CONSTRAINT
                        hasNext = resultSet.next();
                        if (hasNext) {
                            partOfSqlQuery += ",";
                            //partOfSqlQuery = ",";
                            //queryList.add(partOfSqlQuery);
                        }
                        partOfSqlQuery = partOfSqlQuery.toString().replaceAll(":", " ");
                        queryList.add(partOfSqlQuery);
                        finalSqlQuery += partOfSqlQuery;
                    }
                    if (needCheckConstraint) {
                        populateConstraint(argTblId);
                    }
                }
                sQLiteConnection.onCloseResultSet(resultSet);
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
        } else {
            System.out.println("SQL connection null");
        }
    }

    private void populateConstraint(long argTblId) {
        ResultSet resultSet = null;
        if (sQLiteConnection != null) {
            sqlQuery = "SELECT * FROM tbl_constraint_property AS tcon_pro "
                    + " JOIN tbl_column_property As tcol_pro ON tcol_pro.tcpro_id = tcon_pro.tcpro_id "
                    + " JOIN tbl_table_property As ttbl_pro ON ttbl_pro.ttpro_id = tcol_pro.ttpro_id "
                    + " WHERE ttbl_pro.ttpro_id = '" + argTblId + "' "
                    + " ORDER BY tcon_pro.tconp_key DESC ";
            System.out.println("SQL: " + sqlQuery);
            resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                while (resultSet.next()) {
                    String colFixedTblName = resultSet.getString("ttpro_tbl_name");
                    String colTblName = resultSet.getString("ttpro_tbl_name");
                    String colTblPrefix = resultSet.getString("ttpro_tbl_prefix");
                    String colColPrefix = resultSet.getString("ttpro_col_prefix");

                    String colName = resultSet.getString("tcpro_col_name");
                    String colConKey = resultSet.getString("tconp_key");
                    String colConPrefix = resultSet.getString("tconp_con_prefix");
                    colTblName = removeSpace(colTblName, "_").toLowerCase();
                    colTblName = colTblPrefix + "_" + colTblName;
                    colName = removeSpace(colName, "_").toLowerCase();
                    colName = colColPrefix + "_" + colName;
                    if (colConPrefix == null || colConPrefix.isEmpty()) {
                        colConPrefix = removeSpace(colFixedTblName, "").toLowerCase();
                        colConPrefix = colConPrefix.replaceAll("\\_+", "");
                        colConPrefix = colConPrefix.substring(0, 5);
                    } else {
                        colConPrefix = removeSpace(colConPrefix, "_").toLowerCase();
                    }
                    System.out.println("KEY: " + colConPrefix);
                    String sqlData = "";
                    String constGap = repeat(":", 24 - "CONSTRAINT".length());
                    if (colConKey.equalsIgnoreCase("PRIMARY")) {
                        sqlData = "    CONSTRAINT" + constGap + "pk_%s_%s PRIMARY KEY (%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName);
                    } else if (colConKey.equalsIgnoreCase("FOREIGN")) {
                        sqlData = "    CONSTRAINT" + constGap + "fk_%s_%s FOREIGN KEY (%s) REFERENCES tbl_table(%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName, colName);
                    } else if (colConKey.equalsIgnoreCase("UNIQUE")) {
                        sqlData = "    CONSTRAINT" + constGap + "uk_%s_%s UNIQUE (%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName);
                    }
                    sqlData = sqlData.toString().replaceAll(":", " ");
                    queryList.add(sqlData);
                }
            } catch (SQLException e) {
                //Logger.getLogger(SQLGenerate.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQLException: " + e);
            }
            sQLiteConnection.onCloseResultSet(resultSet);
        }
    }

    private String getConstraint(String argConstKey) {
        String retVal = "";
        if (argConstKey.equalsIgnoreCase("PRIMARY")) {
            //
        }
        return retVal;
    }

    private String getMetaData(long argId, String argKey) {
        String retVal = "";
        ResultSet resultSet = null;
        if (sQLiteConnection != null) {
            sqlQuery = "SELECT * FROM tbl_metadata "
                    + " WHERE tm_meta_identity = '" + argId + "' "
                    + " AND tm_meta_key = '" + argKey + "' ";
            resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet.next()) {
                    retVal = resultSet.getString("tm_meta_value");
                }
            } catch (SQLException e) {
                //Logger.getLogger(SQLGenerate.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("SQLException: " + e);
            }
            sQLiteConnection.onCloseResultSet(resultSet);
        }
        return retVal;
    }

    private void printSql() {
        System.out.println("\n");
        String printData = "";
        /*for (String item : queryList) {
            printData = item.trim();
            if (printData.endsWith(",")) {
                printData = printData.substring(0, printData.length() - 1);
            }
            System.out.println(printData);
        }*/
 /*int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<String> iterator = queryList.iterator(); iterator.hasNext();) {
            counter++;
            String element = iterator.next();
            printData = element.trim();
            if (printData.endsWith(",")) {
                printData = printData.substring(0, printData.length() - 1);
            }
            if (iterator.hasNext()) {
                if (!printData.startsWith("DROP") && !printData.startsWith("CREATE") && !printData.startsWith("(")) {
                    //sb.append(", ");
                    printData += ",";
                }
            }
            System.out.println(printData);
        }*/
        boolean isAddComma = false;
        if (queryList.size() > 0) {
            String currentData = queryList.get(0);
            for (int iterator = 0; iterator < queryList.size() - 1; iterator++) {
                String nextData = queryList.get(iterator + 1);
                printData = currentData;
                //printData = printData.trim();
                if (printData.startsWith(");")) {
                    isAddComma = false;
                } else if (nextData.startsWith(");")) {
                    isAddComma = false;
                }
                if (isAddComma) {
                    if (!printData.endsWith(",")) {
                        printData += ",";
                    }
                }
                if (printData.startsWith("(")) {
                    isAddComma = true;
                }
                /*if (!printData.endsWith(",")) {
                    //printData = printData.substring(0, printData.length() - 1);
                    if (!nextData.equals(");") && !printData.startsWith("DROP") && !printData.startsWith("CREATE") && !printData.startsWith("(")) {
                        printData += ",";
                    }
                }
                System.out.println(printData);
                if (nextData.startsWith("DROP")) {
                    //System.out.println(");");
                }*/
                System.out.println(printData);
                currentData = nextData;
            }
            System.out.println(currentData);
            //System.out.println(");");
        }
        //System.out.println(finalSqlQuery);
    }

    private String removeSpace(String argValue, String argReplaceBy) {
        return argValue.replaceAll("\\s+", argReplaceBy);
    }

    public static String repeat(String argStr, int argTimes) {
        if (argTimes > 0) {
            return Stream.generate(() -> argStr).limit(argTimes).collect(joining());
        } else {
            return ":";
        }
    }
}
/*
-- Metadata
-- col_prefix
-- col_is_null
-- constraint
-- reference
DROP TABLE IF EXISTS tbl_metadata;
CREATE TABLE IF NOT EXISTS tbl_metadata
(
    tm_meta_ref_id      BIGINT(20) NULL,
    tm_meta_id          BIGINT(20) NOT NULL,
    tm_meta_identity    TEXT NULL,
    tm_meta_key         TEXT NOT NULL,
    tm_meta_value       TEXT NULL,
    CONSTRAINT pk_tm_tm_meta_id PRIMARY KEY (tm_meta_id)
);

DROP TABLE IF EXISTS tbl_table_property;
CREATE TABLE IF NOT EXISTS tbl_table_property
(
    ttpro_id            BIGINT(20) NOT NULL,
    ttpro_tbl_name      TEXT NOT NULL,
    ttpro_tbl_prefix    TEXT NULL,
    ttpro_col_prefix    TEXT NULL,
    ttpro_tbl_comment   TEXT NULL,
    CONSTRAINT pk_ttp_ttpro_id PRIMARY KEY (ttpro_id),
    CONSTRAINT uk_ttp_ttpro_tbl_name UNIQUE (ttpro_tbl_name)
);

DROP TABLE IF EXISTS tbl_column_property;
CREATE TABLE IF NOT EXISTS tbl_column_property
(
    ttpro_id            BIGINT(20) NOT NULL,
    tcpro_id            BIGINT(20) NOT NULL,
    tcpro_col_name      TEXT NOT NULL,
    tcpro_col_dtype     TEXT NOT NULL,
    tcpro_length        TEXT NOT NULL,
    tcpro_is_null       BOOLEAN NOT NULL,
    tcpro_no_prefix     BOOLEAN NULL,
    tcpro_col_comment   TEXT NOT NULL,
    CONSTRAINT pk_tcp_tcpro_id PRIMARY KEY (tcpro_id),
    CONSTRAINT fk_tcp_ttpro_id FOREIGN KEY (ttpro_id) REFERENCES tbl_table_property(ttpro_id),
    CONSTRAINT uk_tcp_ttpro_col_name UNIQUE (tcpro_col_name)
);

DROP TABLE IF EXISTS tbl_constraint_property;
CREATE TABLE IF NOT EXISTS tbl_constraint_property
(
    tcpro_id            BIGINT(20) NOT NULL,
    tconp_id            BIGINT(20) NOT NULL,
    tconp_key           TEXT NOT NULL,
    -- tconp_value         TEXT NOT NULL,
    tconp_ref_tbl       TEXT NULL,
    tconp_con_prefix    TEXT NULL,
    CONSTRAINT pk_tconp_tconp_id PRIMARY KEY (tconp_id),
    CONSTRAINT fk_tconp_tcpro_id FOREIGN KEY (tcpro_id) REFERENCES tbl_column_property(tcpro_id)
);
 */
/*
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15161836772723', 'registration_temp', 'tbl', 'regtm', '');
INSERT INTO tbl_table_property VALUES ('15161836774627', 'user_profile', 'tbl', 'uspro', '');
INSERT INTO tbl_table_property VALUES ('15161836775101', 'userrole', 'tbl', 'usrol', '');
SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name;


DELETE FROM tbl_column_property;
INSERT INTO tbl_column_property
VALUES ('15161836774627', '15161837756926', 'user_id', 'BIGINT', '20', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836774627', '15161837756625', 'first_name', 'VARCHAR', '255', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836774627', '15161837755235', 'mid_name', 'VARCHAR', '255', '1', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836774627', '15161840676471', 'last_name', 'VARCHAR', '255', '1', '', '');
SELECT * FROM tbl_column_property ORDER BY tcpro_col_name;


;
CREATE TABLE IF NOT EXISTS tbl_constraint_property
(
    tcpro_id            BIGINT(20) NOT NULL,
    tconp_id            BIGINT(20) NOT NULL,
    tconp_key           TEXT NOT NULL,
    -- tconp_value         TEXT NOT NULL,
    tconp_ref_tbl       TEXT NULL,
    tconp_con_prefix    TEXT NULL,
    CONSTRAINT pk_tconp_tconp_id PRIMARY KEY (tconp_id),
    CONSTRAINT fk_tconp_tcpro_id FOREIGN KEY (tcpro_id) REFERENCES tbl_column_property(tcpro_id)
);
DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15161837756926', '15161842565701', 'PRIMARY', '', '');
SELECT * FROM tbl_constraint_property ORDER BY tconp_key DESC;
*/
