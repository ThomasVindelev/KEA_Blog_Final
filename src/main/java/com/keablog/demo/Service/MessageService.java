package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private Database database;

    private ResultSet resultSet;

    public void newPost(Message message) throws SQLException {
        database.createPost(message);
    }

    public void deleteMessage(Message message) {
        database.deletePost(message);
    }

    public List<Message> getNewMessages() throws SQLException {
        List<Message> messageList;
        resultSet = database.getPosts(1);
        messageList = populateList(resultSet);
        return messageList;
    }

    public List<Message> getAllMessages() throws SQLException {
        List<Message> messageList;
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
            message.setDate(resultSet.getDate("date"));
            message.setTime(resultSet.getTime("date"));
            messageList.add(message);
        }
        return messageList;
    }

}