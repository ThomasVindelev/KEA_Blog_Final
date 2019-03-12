package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.Message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MessageService {

    private Database database = new Database();
    private ResultSet resultSet;
    private Message message;
    private List<Message> messageList;

    public MessageService() throws SQLException {

    }

    public void newPost(Message message) throws SQLException {
        database.createPost(message);
    }

    public List<Message> readNewMessages() throws SQLException {
        resultSet = database.getNewestPosts();
        while (resultSet.next()) {
            message.setUsername(resultSet.getString("id_username"));
            message.setTitle(resultSet.getString("post_title"));
            message.setText(resultSet.getString("post_text"));
            messageList.add(message);
        }
        return messageList;
    }

}
