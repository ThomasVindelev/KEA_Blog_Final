package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.Message;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private Database database = new Database();
    private ResultSet resultSet;

    public MessageService() throws SQLException {

    }

    public void newPost(Message message) throws SQLException {
        database.createPost(message);
    }

    public List<Message> getNewMessages() throws SQLException {
        List<Message> messageList = new ArrayList<>();
        resultSet = database.getPosts(1);
        messageList = populateList(resultSet);
        return messageList;
    }

    public List<Message> getAllMessages() throws SQLException {
        List<Message> messageList = new ArrayList<>();
        resultSet = database.getPosts(2);
        messageList = populateList(resultSet);
        return messageList;
    }

    public List<Message> populateList(ResultSet resultSet) throws SQLException {
        List<Message> messageList = new ArrayList<>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setUsername(resultSet.getString("username"));
            message.setTitle(resultSet.getString("post_title"));
            message.setText(resultSet.getString("post_text"));
            messageList.add(message);
        }
        return messageList;
    }

}
