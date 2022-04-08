package me.fivevl.doublejump;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public interface Database {
    void connect() throws SQLException;
    void disconnect() throws SQLException;
    boolean isConnected() throws SQLException;
    void setUsername(String username);
    void setPassword(String password);
    void setHost(String host);
    void setDatabase(String database);
    void setPort(String port);
    CachedRowSet query(String query) throws SQLException;
    void execute(String query) throws SQLException;
}
