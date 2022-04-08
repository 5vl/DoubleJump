package me.fivevl.doublejump;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Database {
    private Connection connection;
    private String username;
    private String password;
    private String database;
    private String host;
    private String port;
    @Override
    public void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
    }

    @Override
    public void disconnect() throws SQLException {
        if (isConnected()) {
            connection.close();
        }
    }

    @Override
    public boolean isConnected() throws SQLException {
        return !connection.isClosed();
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public void setDatabase(String database) {
        this.database = database;
    }

    @Override
    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public CachedRowSet query(String query) throws SQLException{
        CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowSet.populate(connection.prepareStatement(query).executeQuery());
        return cachedRowSet;
    }

    @Override
    public void execute(String query) throws SQLException {
        connection.prepareStatement(query).execute();
    }
}
