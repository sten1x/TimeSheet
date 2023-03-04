package jdbc.dao;

import jdbc.dto.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM users")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmployeeId(resultSet.getInt(4));
                user.setAdmin(resultSet.getInt(5) == 1);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User getUsersByNameAndPassword(String username, String password) {
        if (username != null & password != null) {
            List<User> users = new UserDAO().getAllUsers();

            for (User user : users) {
                if (username.equals(user.getUsername()) & password.equals(user.getPassword()))
                    return user;
            }
        }
        return new User(0, "", "", 0, false);
    }
}
