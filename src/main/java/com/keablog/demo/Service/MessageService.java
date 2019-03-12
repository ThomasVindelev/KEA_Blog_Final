package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.Message;
import com.keablog.demo.Objects.User;
import java.sql.SQLException;

public class MessageService {

    Database database = new Database();

    public MessageService() throws SQLException {

    }

    public void newPost(Message message) throws SQLException {
        database.createPost(message);
    }

}
