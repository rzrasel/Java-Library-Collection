package com.rz.Libraries.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rz Rasel
 */
public class SQLiteConnection {

    private static SQLiteConnection instance = null;
    private String JDBC_DRIVER = "org.sqlite.JDBC";
    private String DB_URL = "jdbc:sqlite:";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public static SQLiteConnection getInstance(String argDbFilePath) {
        if (instance == null) {
            instance = new SQLiteConnection(argDbFilePath);
        }
        return instance;
    }

    public SQLiteConnection(String argDbFilePath) {
        DB_URL += argDbFilePath;
    }

    public Connection onOpenConnection() {
        try {
            //String url = "jdbc:sqlite:test.sqlite3";
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not init JDBC driver - driver not found");
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public void onExecuteRawQuery(String argSqlQuery) {
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //int numRowsChanged = pst.executeUpdate(sql);
            statement = connection.createStatement();
            statement.executeUpdate(argSqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet onSqlQuery(String argSqlQuery) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(argSqlQuery);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }

    public void onCloseResultSet(ResultSet argResultSet) {
        if (argResultSet != null) {
            try {
                argResultSet.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseStatement() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseStatement(Statement argStatement) {
        if (argStatement != null) {
            try {
                argStatement.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onCloseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }

    public void onClose() {
        //onCloseResultSet(resultSet);
        onCloseStatement();
        onCloseConnection();
    }
}
/*
https://www.tutorialspoint.com/sqlite/sqlite_java.htm
https://www.experts-exchange.com/questions/27497760/How-do-I-connect-to-a-Remote-Server-or-Computer-for-updating-a-SQLite-Database.html
https://stackoverflow.com/questions/1525444/how-to-connect-sqlite-with-java
if (c.moveToFirst())
    {
        while ( !c.isAfterLast() ){
           dirArray.add( c.getString( c.getColumnIndex("name")) );
           c.moveToNext();
        }
    }
*/
