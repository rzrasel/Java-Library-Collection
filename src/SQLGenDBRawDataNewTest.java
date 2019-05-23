
import com.rz.librarycore.RandomValue;
import com.rz.librarycore.dbhandler.SQLiteConnection;
import com.rz.librarycore.logger.LogWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rz Rasel
 */
public class SQLGenDBRawDataNewTest {

    public static final String DB_NAME = "app-jar-libs/app-system.sqlite3";
    private SQLiteConnection sQLiteConnection;
    private String sqlQuery = "";
    private boolean isFirstConst = true;
    private ArrayList<ModelTableProperty> modelTablePropertyList = new ArrayList<>();

    public static void main(String args[]) {
        SQLiteConnection.isLogPrint = false;
        LogWriter.isDebug = false;
        LogWriter.Log("Hi");
        new SQLGenDBRawDataNewTest().getTableProperty();
    }

    private void getTableProperty() {
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_table_property ORDER BY ttpro_tbl_name ASC;";
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    //System.out.println("");
                    //System.out.println("DELETE FROM tbl_table_property;");
                    while (resultSet.next()) {
                        ModelTableProperty modelTableProperty = new ModelTableProperty();
                        modelTableProperty.onGetSetData(resultSet);
                        modelTablePropertyList.add(modelTableProperty);
                        Thread.sleep(200);
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.toString());
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            closeDatabase();
        } else {
            closeDatabase();
            getTableProperty();
        }
        System.out.println("MODEL_TABLE_PROPERTY_SIZE: " + modelTablePropertyList.size());
        for (ModelTableProperty modelTableProperty : modelTablePropertyList) {
            getColumnProperty(modelTableProperty, modelTableProperty.getExistId(), modelTableProperty.getNewId());
        }
        for (ModelTableProperty modelTableProperty : modelTablePropertyList) {
            //ArrayList<ModelColumnProperty> modelColumnPropertyList;
            ArrayList<ModelColumnProperty> modelColumnPropertyList = modelTableProperty.getModelColumnProperty();
            for (ModelColumnProperty modelColumnProperty : modelColumnPropertyList) {
                //System.out.println("\t" + modelColumnProperty.getColumnPrefix() + "_" + modelColumnProperty.getName());
                getConstraintProperty(modelTablePropertyList, modelColumnProperty, modelColumnProperty.getExistId(), modelColumnProperty.getNewId());
            }
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        String tmpSqlString;
        System.out.println("DELETE FROM tbl_constraint_property;");
        System.out.println("DELETE FROM tbl_column_property;");
        System.out.println("DELETE FROM tbl_table_property;");
        System.out.println();
        System.out.println();
        for (ModelTableProperty modelTableProperty : modelTablePropertyList) {
            //System.out.println("MODEL_COLUMN_PROPERTY_SIZE: " + modelTableProperty.getModelColumnProperty().size());
            //System.out.println(modelTableProperty.getTablePrefix() + "_" + modelTableProperty.getName());
            String rowTableId = modelTableProperty.getNewId();
            String colTblName = modelTableProperty.getName();
            String colTblPrefix = modelTableProperty.getTablePrefix();
            String colColPrefix = modelTableProperty.getColumnPrefix();
            String colTblComment = modelTableProperty.getTableComment();
            rowTableId = getDbFormat(rowTableId);
            //colTblName = getDbFormat(colTblPrefix + "_" + colTblName);
            System.out.println("-- TABLE: " + colTblName.toUpperCase());
            colTblName = getDbFormat(colTblName);
            colTblPrefix = getDbFormat(colTblPrefix);
            colColPrefix = getDbFormat(colColPrefix);
            colTblComment = getDbFormat(colTblComment);
            tmpSqlString = "INSERT INTO tbl_table_property VALUES (%s, %s, %s, %s, %s);";
            tmpSqlString = String.format(tmpSqlString, rowTableId, colTblName, colTblPrefix, colColPrefix, colTblComment);
            System.out.println(tmpSqlString);
            ArrayList<ModelColumnProperty> modelColumnPropertyList = modelTableProperty.getModelColumnProperty();
            ArrayList<ModelConstraintProperty> modelConstraintPropertyList = new ArrayList<>();
            ArrayList<ModelConstraintProperty> modelConstraintPropertyTempList = null;
            System.out.println("-- COLUMN_PROPERTY");
            for (ModelColumnProperty modelColumnProperty : modelColumnPropertyList) {
                //System.out.println("\t" + modelColumnProperty.getColumnPrefix() + "_" + modelColumnProperty.getName());
                //ArrayList<ModelConstraintProperty> modelConstraintPropertyTempList = modelColumnProperty.getModelConstraintProperty();
                String rowColumnId = modelColumnProperty.getNewId();
                String colColName = modelColumnProperty.getName();
                colColPrefix = modelColumnProperty.getColumnPrefix();
                String colColDataType = modelColumnProperty.getDataType();
                String colLength = modelColumnProperty.getColumnLength();
                String colIsNull = modelColumnProperty.getColumnIsNull();
                String colPrefixType = modelColumnProperty.getColumnPrefixType();
                String colComment = modelColumnProperty.getColumnComment();
                rowColumnId = getDbFormat(rowColumnId);
                //colColName = getDbFormat(colColPrefix + "_" + colColName);
                colColName = getDbFormat(colColName);
                colColDataType = getDbFormat(colColDataType).toUpperCase();
                colLength = getDbFormat(colLength);
                colIsNull = getDbFormat(colIsNull);
                colPrefixType = getDbFormat(colPrefixType);
                colComment = getDbFormat(colComment);
                tmpSqlString = "INSERT INTO tbl_column_property VALUES (%s, %s, %s, %s, %s, %s, %s, %s);";
                tmpSqlString = String.format(tmpSqlString, rowTableId, rowColumnId, colColName, colColDataType, colLength, colIsNull, colPrefixType, colComment);
                System.out.println(tmpSqlString);
                //System.out.println("LENGTH: " + colColName.length());
                modelConstraintPropertyTempList = modelColumnProperty.getModelConstraintProperty();
                if (modelConstraintPropertyTempList.size() > 0) {
                    modelConstraintPropertyList.addAll(modelConstraintPropertyTempList);
                }
                /*else {
                 System.out.println("IS_NULL");
                 }*/
                /*ArrayList<ModelConstraintProperty> modelConstraintPropertyList = modelColumnProperty.getModelConstraintProperty();
                 for (ModelConstraintProperty modelConstraintProperty : modelConstraintPropertyList) {
                 System.out.println("\t\t" + modelConstraintProperty.getKeyConstraint());
                 }*/
            }
            System.out.println("-- CONSTRAINT_PROPERTY");
            //System.out.println("SIZE: " + modelConstraintPropertyList.size());
            for (ModelConstraintProperty modelConstraintProperty : modelConstraintPropertyList) {
                //System.out.println("\t\t" + modelConstraintProperty.getKeyConstraint());
                String rowNewTableId = modelConstraintProperty.getNewTableId();
                String rowNewColumnId = modelConstraintProperty.getNewColumnId();
                String rowConstraintId = modelConstraintProperty.getNewId();
                String colKeyConstraint = modelConstraintProperty.getKeyConstraint();
                String colRefTbl = modelConstraintProperty.getRefTableId();
                String colConPrefix = modelConstraintProperty.getConPrefix();
                if (rowNewTableId != null) {
                    colRefTbl = rowNewTableId;
                }
                rowNewColumnId = getDbFormat(rowNewColumnId);
                rowConstraintId = getDbFormat(rowConstraintId);
                colKeyConstraint = getDbFormat(colKeyConstraint);
                colRefTbl = getDbFormat(colRefTbl);
                colConPrefix = getDbFormat(colConPrefix);
                tmpSqlString = "INSERT INTO tbl_constraint_property VALUES (%s, %s, %s, %s, %s);";
                tmpSqlString = String.format(tmpSqlString, rowNewColumnId, rowConstraintId, colKeyConstraint, colRefTbl, colConPrefix);
                System.out.println(tmpSqlString);
            }
            modelConstraintPropertyList.clear();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private void getColumnProperty(ModelTableProperty argModelTableProperty, String argExistId, String argNewId) {
        //HashMap<String, String> mapColProId = new HashMap<String, String>();
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_column_property WHERE ttpro_id = '" + argExistId + "';";
            //System.out.println("SQL: " + sqlQuery);
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    //System.out.println("");
                    //System.out.println("DELETE FROM tbl_column_property;");
                    String[] arrOfStr = argNewId.split("-");
                    ArrayList<ModelColumnProperty> modelColumnPropertyList = new ArrayList<>();
                    while (resultSet.next()) {
                        ModelColumnProperty modelColumnProperty = new ModelColumnProperty();
                        modelColumnProperty.onGetSetData(resultSet, argModelTableProperty);
                        modelColumnPropertyList.add(modelColumnProperty);
                        Thread.sleep(200);
                    }
                    argModelTableProperty.setModelColumnProperty(modelColumnPropertyList);
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.toString());
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            closeDatabase();
        } else {
            closeDatabase();
            getColumnProperty(argModelTableProperty, argExistId, argNewId);
        }
    }

    private void getConstraintProperty(ArrayList<ModelTableProperty> argModelTablePropertyList, ModelColumnProperty argModelColumnProperty, String argExistId, String argNewId) {
        if (sQLiteConnection == null) {
            openDatabase();
            sqlQuery = "SELECT * FROM tbl_constraint_property WHERE tcpro_id = '" + argExistId + "';";
            //System.out.println("SQL: " + sqlQuery);
            ResultSet resultSet = sQLiteConnection.onSqlQuery(sqlQuery);
            try {
                if (resultSet != null) {
                    //System.out.println("");
                    //System.out.println("DELETE FROM tbl_column_property;");
                    ArrayList<ModelConstraintProperty> modelConstraintPropertyList = new ArrayList<>();
                    while (resultSet.next()) {
                        ModelConstraintProperty modelConstraintProperty = new ModelConstraintProperty();
                        modelConstraintProperty.onGetSetData(resultSet, argModelTablePropertyList, argModelColumnProperty);
                        modelConstraintPropertyList.add(modelConstraintProperty);
                        Thread.sleep(200);
                    }
                    argModelColumnProperty.setModelConstraintProperty(modelConstraintPropertyList);
                }
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            } catch (InterruptedException e) {
                System.out.println("InterruptedException: " + e.toString());
            }
            sQLiteConnection.onCloseResultSet(resultSet);
            closeDatabase();
        } else {
            closeDatabase();
            getConstraintProperty(argModelTablePropertyList, argModelColumnProperty, argExistId, argNewId);
        }
    }

    public HashMap<String, String> getColumPrefix(String argColumnName) {
        //String retVal = null;
        HashMap<String, String> retVal = new HashMap<>();
        String strSqlQuery = "SELECT * "
                + " FROM tbl_table_property AS table_property "
                + " JOIN tbl_column_property As column_property ON column_property.ttpro_id = table_property.ttpro_id "
                + " JOIN tbl_constraint_property As constraint_property ON constraint_property.tcpro_id = column_property.tcpro_id "
                + " WHERE "
                + " column_property.tcpro_col_name = '" + argColumnName + "' "
                + " AND (constraint_property.tconp_key = 'PRIMARY' "
                + " OR constraint_property.tconp_key != 'FOREIGN')";
        if (sQLiteConnection == null) {
            openDatabase();
            ResultSet resultSet1 = sQLiteConnection.onSqlQuery(strSqlQuery);
            try {
                if (resultSet1.next()) {
                    //retVal = resultSet1.getString("ttpro_col_prefix");
                    retVal.put("table_id", resultSet1.getString("ttpro_id"));
                    retVal.put("column_prefix", resultSet1.getString("ttpro_col_prefix"));
                }

            } catch (SQLException e) {
                System.out.println("SQLException: " + e.toString());
            }
            sQLiteConnection.onCloseResultSet(resultSet1);
            closeDatabase();
        } else {
            closeDatabase();
            getColumPrefix(argColumnName);
        }
        //System.out.println("==============: " + retVal + "_" + argColumnName);
        return retVal;
    }

    public String getDbFormat(String argData) {
        String retVal = null;
        if (argData != null) {
            argData = argData.trim();
            if (argData.isEmpty()) {
                retVal = null;
            } else {
                retVal = "'" + argData.trim() + "'";
            }
        } else {
            retVal = null;
        }
        return retVal;
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

class ModelTableProperty {

    private String existId;
    private String newId;
    private String name;
    private String tablePrefix;
    private String columnPrefix;
    private String tableComment;
    private ArrayList<ModelColumnProperty> modelColumnPropertyList;

    public String getExistId() {
        return this.existId;
    }

    public void setExistId(String argExistId) {
        this.existId = argExistId;
    }

    public String getNewId() {
        return this.newId;
    }

    public void setNewId(String argId) {
        this.newId = argId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String argName) {
        this.name = argName;
    }

    public String getTablePrefix() {
        return this.tablePrefix;
    }

    public void setTablePrefix(String argTablePrefix) {
        this.tablePrefix = argTablePrefix;
    }

    public String getColumnPrefix() {
        return this.columnPrefix;
    }

    public void setColumnPrefix(String argColumnPrefix) {
        this.columnPrefix = argColumnPrefix;
    }

    public String getTableComment() {
        return this.tableComment;
    }

    public void setTableComment(String argTableComment) {
        this.tableComment = argTableComment;
    }

    public ArrayList<ModelColumnProperty> getModelColumnProperty() {
        return this.modelColumnPropertyList;
    }

    public void setModelColumnProperty(ArrayList<ModelColumnProperty> argModelColumnPropertyList) {
        this.modelColumnPropertyList = argModelColumnPropertyList;
    }

    public void onGetSetData(ResultSet argResultSet) {
        String tmpSql = "";
        String newId = "";
        try {
            long rowId = argResultSet.getLong("ttpro_id");
            String colTblName = argResultSet.getString("ttpro_tbl_name");
            String colTblPrefix = argResultSet.getString("ttpro_tbl_prefix");
            String colColPrefix = argResultSet.getString("ttpro_col_prefix");
            String colTblComment = argResultSet.getString("ttpro_tbl_comment");
            newId = RandomValue.getRandId(1111, 9999);
            this.setExistId(rowId + "");
            this.setNewId(newId);
            this.setName(colTblName);
            this.setTablePrefix(colTblPrefix);
            this.setColumnPrefix(colColPrefix);
            this.setTableComment(colTblComment);
            //System.out.println("TABLE_ID: " + rowId + " -- " + newId);
        } catch (SQLException e) {
            //System.out.println("SQLException: " + e.toString());
        }
        //return this;
    }
}

class ModelColumnProperty {

    private String existTableId;
    private String existId;
    private String newId;
    private String name;
    private String columnPrefix;
    private String dataType;
    private String columnLength;
    private String columnIsNull;
    private String columnPrefixType; //NoPrefix 0 = add prefix, 1 = don't add prefix
    private String columnComment;
    private ArrayList<ModelConstraintProperty> modelConstraintPropertyList;

    public String getExistTableId() {
        return this.existTableId;
    }

    public void setExistTableId(String argExistTableId) {
        this.existTableId = argExistTableId;
    }

    public String getExistId() {
        return this.existId;
    }

    public void setExistId(String argExistId) {
        this.existId = argExistId;
    }

    public String getNewId() {
        return this.newId;
    }

    public void setNewId(String argId) {
        this.newId = argId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String argName) {
        this.name = argName;
    }

    public String getColumnPrefix() {
        return this.columnPrefix;
    }

    public void setColumnPrefix(String argColumnPrefix) {
        this.columnPrefix = argColumnPrefix;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String argDataType) {
        this.dataType = argDataType;
    }

    public String getColumnLength() {
        return this.columnLength;
    }

    public void setColumnLength(String argColumnLength) {
        this.columnLength = argColumnLength;
    }

    public String getColumnIsNull() {
        return this.columnIsNull;
    }

    public void setColumnIsNull(String argColumnIsNull) {
        this.columnIsNull = argColumnIsNull;
    }

    public String getColumnPrefixType() {
        return this.columnPrefixType;
    }

    public void setColumnPrefixType(String argColumnPrefixType) {
        this.columnPrefixType = argColumnPrefixType;
    }

    public String getColumnComment() {
        return this.columnComment;
    }

    public void setColumnComment(String argColumnComment) {
        this.columnComment = argColumnComment;
    }

    public ArrayList<ModelConstraintProperty> getModelConstraintProperty() {
        return this.modelConstraintPropertyList;
    }

    public void setModelConstraintProperty(ArrayList<ModelConstraintProperty> argModelConstraintPropertyList) {
        this.modelConstraintPropertyList = argModelConstraintPropertyList;
    }

    public void onGetSetData(ResultSet argResultSet, ModelTableProperty argModelTableProperty) {
        String tmpSql = "";
        String newId = "";
        try {
            long rowTableId = argResultSet.getLong("ttpro_id");
            long rowId = argResultSet.getLong("tcpro_id");
            String colColName = argResultSet.getString("tcpro_col_name");
            String colColDataType = argResultSet.getString("tcpro_col_dtype");
            String colLength = argResultSet.getString("tcpro_length");
            String colIsNull = argResultSet.getString("tcpro_is_null");
            String colPrefixType = argResultSet.getString("tcpro_no_prefix");
            String colComment = argResultSet.getString("tcpro_col_comment");
            newId = RandomValue.getRandId(1111, 9999);
            /*String[] checkPartOfName = colColName.split("[\\s|,|;|:|>]");
             if (checkPartOfName.length > 1) {
             System.out.println(colColName + " Name Has Multiple Parts");
             System.out.println("Total Parts: " + checkPartOfName.length);
             int lastPart = checkPartOfName.length - 1;
             colColName = checkPartOfName[lastPart];
             }*/
            this.setExistId(rowId + "");
            this.setNewId(newId);
            this.setName(colColName);
            this.setDataType(colColDataType);
            this.setColumnLength(colLength);
            this.setColumnIsNull(colIsNull);
            this.setColumnPrefixType(colPrefixType);
            this.setColumnComment(colComment);
            if (colPrefixType.equals("0")) {
                //System.out.println("COLUMN_PREFIX_TYPE: " + colPrefixType);
                this.setColumnPrefix(argModelTableProperty.getColumnPrefix());
                this.setExistTableId(rowTableId + "");
                //System.out.println("COLUMN_PREFIX_TYPE: " + this.getColumnPrefix());
            } else if (colPrefixType.equals("1")) {
                //System.out.println("COLUMN_PREFIX_TYPE: " + colPrefixType);
                HashMap<String, String> detailsMap = new SQLGenDBRawDataNewTest().getColumPrefix(this.name);
                this.setExistTableId(detailsMap.get("table_id"));
                this.setColumnPrefix(detailsMap.get("column_prefix"));
            }
            //System.out.println("COLUMN_PREFIX_TYPE: " + colPrefixType);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
        //return this;
    }
}

class ModelConstraintProperty {

    private String newTableId;
    private String newColumnId;
    private String existId;
    private String newId;
    private String keyConstraint;
    private String refTableId;
    private String conPrefix;

    public String getNewTableId() {
        return this.newTableId;
    }

    public void setNewTableId(String argNewTableId) {
        this.newTableId = argNewTableId;
    }

    public String getNewColumnId() {
        return this.newColumnId;
    }

    public void setNewColumnId(String argNewColumnId) {
        this.newColumnId = argNewColumnId;
    }

    public String getExistId() {
        return this.existId;
    }

    public void setExistId(String argExistId) {
        this.existId = argExistId;
    }

    public String getNewId() {
        return this.newId;
    }

    public void setNewId(String argId) {
        this.newId = argId;
    }

    public String getKeyConstraint() {
        return this.keyConstraint;
    }

    public void setKeyConstraint(String argKeyConstraint) {
        this.keyConstraint = argKeyConstraint;
    }

    public String getRefTableId() {
        return this.refTableId;
    }

    public void setRefTableId(String argRefTableId) {
        this.refTableId = argRefTableId;
    }

    public String getConPrefix() {
        return this.conPrefix;
    }

    public void setConPrefix(String argConPrefix) {
        this.conPrefix = argConPrefix;
    }

    public void onGetSetData(ResultSet argResultSet, ArrayList<ModelTableProperty> argModelTablePropertyList, ModelColumnProperty argModelColumnProperty) {
        try {
            String newId = RandomValue.getRandId(1111, 9999);
            long rowId = argResultSet.getLong("tconp_id");
            String colKey = argResultSet.getString("tconp_key");
            String colRefTbl = argResultSet.getString("tconp_ref_tbl");
            String colConPrefix = argResultSet.getString("tconp_con_prefix");
            //newId = RandomValue.getRandId(1111, 9999);
            //System.out.println("KEY: " + colKey);
            String tableNewId = null;
            if (colRefTbl != null) {
                //System.out.println("FOREIGN: " + colRefTbl);
                for (ModelTableProperty modelTableProperty : argModelTablePropertyList) {
                    //System.out.println("FOREIGN: " + colRefTbl + " -- " + modelTableProperty.getExistId());
                    //if (colRefTbl == modelTableProperty.getExistId()) {
                    if (colRefTbl.equals(modelTableProperty.getExistId())) {
                        //System.out.println("FOREIGN: " + colRefTbl + " -- " + modelTableProperty.getNewId());
                        tableNewId = modelTableProperty.getNewId();
                        this.setNewTableId(tableNewId);
                    }
                }
            }
            //this.setNewTableId(tableNewId);
            this.setNewColumnId(argModelColumnProperty.getNewId());
            this.setExistId(rowId + "");
            this.setNewId(newId + "");
            this.setKeyConstraint(colKey);
            this.setRefTableId(colRefTbl);
            this.setConPrefix(colConPrefix);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.toString());
        }
    }
}
/*
 Device Information
 pro_key_id
 ddetails_id
 ddetails_android_id
 dev_dtls_serial
 dev_dtls_build_num
 dev_dtls_model_num
 dev_dtls_build_hardware
 dev_dtls_build_product
 dev_dtls_build_user
 dev_dtls_build_brand
 dev_dtls_uuid
 dev_dtls_imei_num
 dev_dtls_iccid_num
 dev_dtls_build_incremental
 dev_dtls_main_gen_unique_id
 dev_dtls_second_gen_unique_id

 Device Version
 dev_dtls_build_sdk_vers
 dev_dtls_release_vers
 dev_dtls_os_vers

 Device Network
 dev_dtls_general_ip
 dev_dtls_wifi_ip
 dev_dtls_used_network_type

 Device Mac Address
 dev_dtls_mac_address_lan
 dev_dtls_mac_address_eth

 Request Access Tracker
 device id
 user id
 req ip

 */
