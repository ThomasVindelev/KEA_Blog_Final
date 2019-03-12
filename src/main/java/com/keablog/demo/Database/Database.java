package com.keablog.demo.Database;
import com.keablog.demo.Objects.Message;

import java.sql.*;

public class Database {

    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;
    private String query;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/blog?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
    }

    public void createPost(Message message) throws SQLException {
        query = "INSERT INTO blogposts (`post_title`, `post_text`) VALUES (?, ?)";
        executeQuery(query, message);
    }

    public void executeQuery(String query, Message message) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(3, message.title);
        preparedStatement.setString(4, message.text);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }







}