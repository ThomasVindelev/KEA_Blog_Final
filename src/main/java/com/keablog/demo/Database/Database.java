package com.keablog.demo.Database;
import com.keablog.demo.Objects.Message;
import com.keablog.demo.Objects.User;

import java.sql.*;

public class Database {

    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/keablog?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "keablog", "Rf40H?f1LB?V");
    }

    public void createPost(Message message) throws SQLException {
        query = "INSERT INTO blogposts (`id_username`,`post_title`, `post_text`) VALUES (?, ?, ?)";
        executeQuery(query, message);
    }

    public void executeQuery(String query, Message message) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, message.getTitle());
        preparedStatement.setString(3, message.getText());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet getPosts(int choice) throws SQLException {
        if (choice == 1) {
            query = "SELECT * FROM blogposts INNER JOIN users ON blogposts.id_username = users.id_users ORDER BY id DESC LIMIT 10";
        } else {
            query = "SELECT * FROM blogposts INNER JOIN users ON blogposts.id_username = users.id_users ORDER BY id DESC";
        }
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public boolean verifyUser(User user) throws SQLException {
        query = "SELECT * FROM users WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }







}