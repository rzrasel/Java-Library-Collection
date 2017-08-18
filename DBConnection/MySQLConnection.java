package com.rz.Libraries.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rz Rasel
 */
public class MySQLConnection {

    private static MySQLConnection instance = null;
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost/";
    private String DB_USER = "";
    private String DB_PASSWORD = "";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public static MySQLConnection getInstance(String argDbName, String argDbUser, String argDbPassword) {
        if (instance == null) {
            instance = new MySQLConnection(argDbName, argDbUser, argDbPassword);
        }
        return instance;
    }

    public MySQLConnection(String argDbName, String argDbUser, String argDbPassword) {
        DB_URL += argDbName;
        DB_USER = argDbUser;
        DB_PASSWORD = argDbPassword;
    }

    @Deprecated
    public Connection onOpen() {
        try {
            //|------------------------|REGISTER JDBC DRIVER|------------------------|
            Class.forName(JDBC_DRIVER);
            //|------------------------|OPEN A CONNECTION WITH MYSQL DATABASE|------------------------|
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //|------------------------|CREATE STATEMENT FOR MYSQL DATABASE|------------------------|
            /*PreparedStatement pstmt  = connection.prepareStatement("Sql");
            rs = pstmt.executeQuery();*/
            //statement = connection.createStatement();
            //|------------------------|CREATE EXECUTE QUERY FOR MYSQL DATABASE|------------------------|
            //ResultSet resultSet = statement.executeQuery("SELECT * from store_me");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public Connection onOpenConnection() {
        try {
            //|------------------------|REGISTER JDBC DRIVER|------------------------|
            Class.forName(JDBC_DRIVER);
            //|------------------------|OPEN A CONNECTION WITH MYSQL DATABASE|------------------------|
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //|------------------------|CREATE STATEMENT FOR MYSQL DATABASE|------------------------|
            /*PreparedStatement pstmt  = connection.prepareStatement("Sql");
            rs = pstmt.executeQuery();*/
            //statement = connection.createStatement();
            //|------------------------|CREATE EXECUTE QUERY FOR MYSQL DATABASE|------------------------|
            //ResultSet resultSet = statement.executeQuery("SELECT * from store_me");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    @Deprecated
    public void onExecuteQuery(String argSqlQuery) {
        try {
            //PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //int numRowsChanged = pst.executeUpdate(sql);
            statement = connection.createStatement();
            statement.executeUpdate(argSqlQuery);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
//System.out.println(resultSet.getInt(1) + "  " + resultSet.getString("name"));
