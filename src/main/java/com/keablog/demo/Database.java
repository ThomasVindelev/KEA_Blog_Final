package com.keablog.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/products?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
    }

}