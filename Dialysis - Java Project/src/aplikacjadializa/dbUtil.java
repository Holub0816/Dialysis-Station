/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacjadializa;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class dbUtil {

    private static dbUtil dbUtil;
    private static BasicDataSource ds;

   

    dbUtil() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/dializa_baza?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("");

    }
 
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void close() throws SQLException {
        ds.close();
    }

    public static dbUtil getInstance() {
        if (dbUtil == null) {
            dbUtil = new dbUtil();
        }
        return dbUtil;
    }

}
