package com.keablog.demo.Service;

import com.keablog.demo.Database.Database;
import com.keablog.demo.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private Database database;

    public List<User> getUserList() throws SQLException {
        ResultSet resultSet = database.getUsers();
        List<User> userList = populateUserList(resultSet);
        return userList;
    }

    public List<User> populateUserList(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id_users"));
            user.setUsername(resultSet.getString("username"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setAge(resultSet.getInt("age"));
            user.setId_role(resultSet.getInt("id_role"));
            userList.add(user);
        }
        return userList;
    }

}
