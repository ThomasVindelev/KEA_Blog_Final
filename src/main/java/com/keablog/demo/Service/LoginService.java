package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Model.User;
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
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id_users"));
            user.setUsername(resultSet.getString("username"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
            user.setId_role(resultSet.getInt("id_role"));
            return true;
        } else {
            return false;
        }

    }
}
