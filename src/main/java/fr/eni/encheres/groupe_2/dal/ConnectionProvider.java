package fr.eni.encheres.groupe_2.dal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

abstract class ConnectionProvider {
    private static DataSource dataSource;
    static {
        Context initContext;
        try {
            initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            ConnectionProvider.dataSource = (DataSource) envContext.lookup("jdbc/pool_cnx");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return ConnectionProvider.dataSource.getConnection();
    }
}
