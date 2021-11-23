package dao.database;

import dao.database.exeptions.DAOExeption;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws DAOExeption;
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPass();
    int getSize();
}
