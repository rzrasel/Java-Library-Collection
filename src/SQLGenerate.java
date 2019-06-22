
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
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
                        System.out.println("TABLE_LENGTH: " + colName + ": " + colName.length());
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
                        boolean colIsnNoPrefix = resultSet.getBoolean("tcpro_no_prefix");
                        //colName = removeSpace(colName, "_");
                        if (colIsnNoPrefix) {
                            //System.out.println("DATA---------------: " + colName);
                            HashMap<String, String> getColPrefix = getColumPrefix(colName);
                            //System.out.println("DATA---------------: " + getColPrefix.size());
                            String[] checkPartOfName = colName.split("[\\s|,|;|:|>]");
                            if (checkPartOfName.length > 1) {
                                int lastPart = checkPartOfName.length - 1;
                                colName = checkPartOfName[lastPart];
                            }
                            colName = getColPrefix.get("column_prefix") + "_" + colName;
                            //colName = argColPrefix + "_" + colName;
                        } else {
                            String[] checkPartOfName = colName.split("[\\s|,|;|:|>]");
                            if (checkPartOfName.length > 1) {
                                int lastPart = checkPartOfName.length - 1;
                                colName = checkPartOfName[lastPart];
                            }
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
                        } else {
                            defaultIsNull = "NOT NULL";
                        }
                        //gapSeparatorLen
                        colType = colType + colLength;
                        partOfSqlQuery = repeat(":", 4)
                                + colName
                                + repeat(":", 40 - colName.length())
                                + colType
                                + repeat(":", 18 - colType.length())
                                + defaultIsNull;
                        //queryList.add(partOfSqlQuery);
                        //------------------CHECK CONSTRAINT
                        System.out.println("COLUMN_LENGTH: " + colName + ": " + colName.length());
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

                    String colNameSaved = resultSet.getString("tcpro_col_name");
                    String colConKey = resultSet.getString("tconp_key");
                    String colConPrefix = resultSet.getString("tconp_con_prefix");
                    boolean colIsNoPrefix = resultSet.getBoolean("tcpro_no_prefix");
                    colTblName = removeSpace(colTblName, "_").toLowerCase();
                    colTblName = colTblPrefix + "_" + colTblName;
                    String colName = removeSpace(colNameSaved, "_").toLowerCase();
                    colName = colColPrefix + "_" + colName;
                    if (colConPrefix == null || colConPrefix.isEmpty()) {
                        colConPrefix = removeSpace(colFixedTblName, " ").toLowerCase();
                        colConPrefix = colConPrefix.replaceAll("[\\s|_]", " ");
                        colConPrefix = getSuffix(colConPrefix, 2, "") + getSuffix(colConPrefix, 1, "");
                        /*colConPrefix = removeSpace(colFixedTblName, "").toLowerCase();
                         colConPrefix = colConPrefix.replaceAll("\\_+", "");*/
                        int strLengt = 12;
                        if (colConPrefix.length() > strLengt) {
                            colConPrefix = colConPrefix.substring(0, strLengt);
                        }
                    } else {
                        colConPrefix = removeSpace(colConPrefix, "_").toLowerCase();
                    }
                    if (colIsNoPrefix) {
                        //System.out.println("DATA---------------: " + colName);
                        //System.out.println("DATA---------------: " + colNameSaved);
                        HashMap<String, String> getColPrefix = getColumPrefix(colNameSaved);
                        //System.out.println("DATA---------------: " + getColPrefix.size());
                        //colName = getColPrefix.get("column_prefix") + "_" + colNameSaved;
                        //colName = removeSpace(colNameSaved, "_").toLowerCase();
                        //System.out.println("--------------->" + colName);
                        String[] checkPartOfName = colNameSaved.split("[\\s|,|;|:|>]");
                        if (checkPartOfName.length > 1) {
                            int lastPart = checkPartOfName.length - 1;
                            colName = checkPartOfName[lastPart];
                        }
                        //System.out.println("--------------->" + colName);
                        colName = getColPrefix.get("column_prefix") + "_" + colName;
                        //System.out.println("--------------->" + colName);
                        colName = removeSpace(colName, "_").toLowerCase();
                    } else {
                        String[] checkPartOfName = colNameSaved.split("[\\s|,|;|:|>]");
                        if (checkPartOfName.length > 1) {
                            int lastPart = checkPartOfName.length - 1;
                            colName = colColPrefix + "_" + checkPartOfName[lastPart];
                        }
                    }
                    //System.out.println("KEY: " + colConPrefix);
                    String sqlData = "";
                    String constGap = repeat(":", 40 - "CONSTRAINT".length());
                    if (colConKey.equalsIgnoreCase("PRIMARY")) {
                        sqlData = "    CONSTRAINT" + constGap + "pk_%s_%s PRIMARY KEY (%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName);
                    } else if (colConKey.equalsIgnoreCase("UNIQUE")) {
                        sqlData = "    CONSTRAINT" + constGap + "uk_%s_%s UNIQUE (%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName);
                    } else if (colConKey.equalsIgnoreCase("FOREIGN")) {
                        long colColId = resultSet.getLong("tcpro_id");
                        long colTblNameRef = resultSet.getLong("tconp_ref_tbl");
                        sqlQuery = " SELECT * FROM tbl_column_property AS column_property "
                                + " JOIN tbl_table_property As table_property ON table_property.ttpro_id = column_property.ttpro_id "
                                + " WHERE table_property.ttpro_id = " + colTblNameRef + " "
                                + " AND column_property.ttpro_id = " + colTblNameRef + "; ";
                        //+ " WHERE column_property.tcpro_id = " + colColId;
                        System.out.println(sqlQuery);
                        String colRefTblPrefix = "";
                        String colRefTblName = "";
                        ResultSet subResultSet = sQLiteConnection.onSqlQuery(sqlQuery);
                        if (subResultSet.next()) {
                            colRefTblPrefix = subResultSet.getString("ttpro_tbl_prefix");
                            colRefTblName = subResultSet.getString("ttpro_tbl_name");
                        }
                        colRefTblName = colRefTblPrefix + "_" + colRefTblName;
                        //System.out.println("REFERENCES: " + colRefTblName);
                        sqlData = "    CONSTRAINT" + constGap + "fk_%s_%s FOREIGN KEY (%s) REFERENCES %s(%s)";
                        sqlData = String.format(sqlData, colConPrefix, colName, colName, colRefTblName, colName);
                        //System.out.println(sqlData);
                        //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + colConPrefix);
                        //
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

    public HashMap<String, String> getColumPrefix(String argColumnName) {
        //String retVal = null;
        String colColName = argColumnName;
        /*String[] checkPartOfName = colColName.split("[\\s|,|;|:|>]");
         if (checkPartOfName.length > 1) {
         int lastPart = checkPartOfName.length - 1;
         colColName = checkPartOfName[lastPart];
         }*/
        HashMap<String, String> retVal = new HashMap<>();
        String strSqlQuery = "SELECT * "
                + " FROM tbl_table_property AS table_property "
                + " JOIN tbl_column_property As column_property ON column_property.ttpro_id = table_property.ttpro_id "
                + " JOIN tbl_constraint_property As constraint_property ON constraint_property.tcpro_id = column_property.tcpro_id "
                + " WHERE "
                + " column_property.tcpro_col_name = '" + colColName + "' "
                + " AND (constraint_property.tconp_key = 'PRIMARY' "
                + " OR constraint_property.tconp_key != 'FOREIGN')";
        //System.out.println("SQL: " + strSqlQuery);
        //closeDatabase();
        //getColumPrefix(argColumnName);
        ResultSet resultSet1 = sQLiteConnection.onSqlQuery(strSqlQuery);
        try {
            if (resultSet1.next()) {
                //retVal = resultSet1.getString("ttpro_col_prefix");
                retVal.put("table_id", resultSet1.getString("ttpro_id"));
                retVal.put("column_prefix", resultSet1.getString("ttpro_col_prefix"));
                //System.out.println("DATA---------------: " + resultSet1.getString("ttpro_col_prefix"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
        sQLiteConnection.onCloseResultSet(resultSet1);
        //System.out.println("==============: " + retVal + "_" + argColumnName);
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

    public String getSuffix(String argString, int argNumOfChar, String argJoinBy) {
        String mainStr = argString.trim();
        //System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + mainStr);
        String[] mainStrings = mainStr.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mainStrings.length; i++) {
            String word = mainStrings[i];
            //System.out.println(word.charAt(0));
            if (word.length() > argNumOfChar) {
                stringBuilder.append(word.substring(0, argNumOfChar));
            } else {
                stringBuilder.append(word);
            }
            if (i < mainStrings.length - 1) {
                stringBuilder.append(argJoinBy);
            }
        }
        //System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<" + stringBuilder.toString());
        return stringBuilder.toString();
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
