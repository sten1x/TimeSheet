package jdbc.dao;

import jdbc.dto.Employee;
import org.example.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IDAO<Employee> {

    @Override
    public List<Employee> GetAll() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM employee_info")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setPhone(resultSet.getString(4));

                employees.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = new Employee();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employee_info where employee_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setAge(resultSet.getInt(3));
                employee.setPhone(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public boolean update(final Employee object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee_info SET employee_name =?, employee_age =?, employee_phone_number =? where employee_id =?")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.setString(3, object.getPhone());
            preparedStatement.setInt(4, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Employee with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no employee with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(final Employee object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee_info(employee_name, employee_age, employee_phone_number) VALUES(?,?,?)")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.setString(3, object.getPhone());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Employee with name " + object.getName() + " has been added");
                return true;
            } else {
                Logger.info("Employee with name " + object.getName() + " cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(final Employee object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM employee_info where employee_id =?");
             PreparedStatement preparedStatement2 = connection.prepareStatement("DELETE FROM time_tracking where employee_id =?")) {

            preparedStatement1.setInt(1, object.getId());
            preparedStatement2.setInt(1, object.getId());

            if (preparedStatement1.executeUpdate() > 0) {
                Logger.info("Employee with id " + object.getId() + " and his logs have been deleted");
                preparedStatement2.executeUpdate();
                return true;
            } else {
                Logger.info("There is no employee with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}