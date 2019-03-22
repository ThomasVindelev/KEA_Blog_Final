package com.keablog.demo.Database;
import com.keablog.demo.Entities.Chat;
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

    public void editPost(Message message) throws SQLException {
        query = "UPDATE blogposts SET post_title = '" + message.getTitle() + "', post_text = '" + message.getText() + "' WHERE id = " + message.getId() + "";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deletePost(Message message) throws SQLException {
        query = "DELETE FROM blogposts WHERE id = " + message.getId() + "";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void newChat(Chat chat) throws SQLException {
        query = "INSERT INTO chat (`id_chat`, `chat_text`, `sent_to`, `sent_from`) VALUES (?, ?, ?, ?)";
        sendChat(chat, query);
    }

    public void sendChat(Chat chat, String query) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, chat.getId());
        preparedStatement.setString(2, chat.getText());
        preparedStatement.setInt(3, chat.getSent_to());
        preparedStatement.setInt(4, chat.getSent_from());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet getChat() throws SQLException {
        query = "SELECT * FROM chat " +
                "INNER JOIN users AS u1 ON chat.sent_to = u1.id_users " +
                "INNER JOIN users AS u2 ON chat.sent_from = u2.id_users ORDER BY id_chat DESC";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
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
            query = "SELECT * FROM blogposts INNER JOIN users ON blogposts.id_username = users.id_users ORDER BY date DESC LIMIT 10";
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

    public void addUser(User user) throws SQLException {
        query = "INSERT INTO users (`username`, `firstname`, `lastname`, `age`, `password`, `id_role`) VALUES (?, ?, ?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getFirstname());
        preparedStatement.setString(3, user.getLastname());
        preparedStatement.setInt(4, user.getAge());
        preparedStatement.setString(5, user.getPassword());
        preparedStatement.setInt(6, user.getId_role());
        preparedStatement.executeUpdate();
        preparedStatement.close();
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