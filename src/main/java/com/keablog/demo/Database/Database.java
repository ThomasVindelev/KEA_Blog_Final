package com.keablog.demo.Database;
import com.keablog.demo.Entities.Message;
import com.keablog.demo.Entities.User;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class Database {

    private PreparedStatement preparedStatement;
    private Connection connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/keablog?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "keablog", "Rf40H?f1LB?V");;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public Database() throws SQLException {

    }

    public void createPost(Message message) throws SQLException {
        query = "INSERT INTO blogposts (`id_username`,`post_title`, `post_text`) VALUES (?, ?, ?)";
        sendPostQuery(query, message);
    }

    public void deletePost(Message message) throws SQLException {
        query = "DELETE FROM blogposts WHERE id = " + message.getId() + "";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void sendPostQuery(String query, Message message) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, message.getUid());
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

    public ResultSet getUsers() throws SQLException {
        query = "SELECT * FROM users INNER JOIN roles ON users.id_role = id_roles ORDER BY id_users ASC";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    public void editUser(User user) throws SQLException {
        query = "UPDATE users SET username = '" + user.getUsername() + "', firstname = '" + user.getFirstname() +
                "', lastname = '" + user.getLastname() + "', age = " + user.getAge() + ", password = '" + user.getPassword() +
                "', id_role = " + user.getId_role() + " WHERE id_users = " + user.getId() + "";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(User user) throws SQLException {
        query = "DELETE FROM users WHERE id_users = " + user.getId() + "";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet verifyUser(User user) throws SQLException {
        query = "SELECT * FROM users WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
}