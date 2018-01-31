
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
        SQLiteConnection.isLogPrint = false;
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
                        partOfSqlQuery = ") ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;";
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
                        if (colLength != null) {
                            if (!colLength.isEmpty()) {
                                colLength = "(" + colLength + ")";
                            } else {
                                colLength = "";
                            }
                        } else {
                            colLength = "";
                        }
                        if (colIsNull) {
                            defaultIsNull = "NULL";
                        }
                        else
                        {
                            defaultIsNull = "NOT NULL";
                        }
                        //gapSeparatorLen
                        colType = colType + colLength;
                        partOfSqlQuery = repeat(":", 4)
                                + colName
                                + repeat(":", 32 - colName.length())
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
                    + " WHERE ttbl_pro.ttpro_id = '" + argTblId + "'; ";
            //+ " ORDER BY tcon_pro.tconp_key ASC ";
            //System.out.println("SQL: " + sqlQuery);
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
                    String constGap = repeat(":", 32 - "CONSTRAINT".length());
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
                } else if (nextData.startsWith(") ENGINE=InnoDB DEFAULT CHARACTER SET=utf8;")) {
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
 */
 /*
DELETE FROM tbl_table_property;
INSERT INTO tbl_table_property VALUES ('15161836772723', 'registration_temp', 'tbl', 'regtm', '');
INSERT INTO tbl_table_property VALUES ('15161836774627', 'user_profile', 'tbl', 'uspro', '');
INSERT INTO tbl_table_property VALUES ('15161836775101', 'userrole', 'tbl', 'usrol', '');
SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name;

##|----|----TABLE USER PROFILE
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

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15161837756926', '15161842565701', 'PRIMARY', '', '');
SELECT * FROM tbl_constraint_property ORDER BY tconp_key DESC;
##|----|----TABLE END

##|----|----TABLE USER ROLE
DELETE FROM tbl_column_property;
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161860599845', 'role_id', 'BIGINT', '20', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161860597350', 'role_title', 'VARCHAR', '255', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161860597802', 'role_priority', 'INT', '3', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161860732762', 'role_is_default', 'BOOLEAN', '', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161860735276', 'role_create_date', 'DATETIME', '', '0', '', '');
INSERT INTO tbl_column_property
VALUES ('15161836775101', '15161861391734', 'role_modify_date', 'DATETIME', '', '0', '', '');
SELECT * FROM tbl_column_property ORDER BY tcpro_col_name;

DELETE FROM tbl_constraint_property;
INSERT INTO tbl_constraint_property VALUES ('15161860599845', '15161863063756', 'PRIMARY', '', '');
INSERT INTO tbl_constraint_property VALUES ('15161860597350', '15161863064221', 'UNIQUE', '', '');
SELECT * FROM tbl_constraint_property ORDER BY tconp_key DESC;
##|----|----TABLE END
 */
