package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginService {

    @Autowired
    private Database database;

    public boolean verifyUser(User user) throws SQLException {
        ResultSet resultSet = database.verifyUser(user);
        return resultSet.next();
    }
}
