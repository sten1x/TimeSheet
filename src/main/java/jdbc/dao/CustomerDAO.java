package jdbc.dao;

import jdbc.dto.Customer;
import jdbc.dto.Project;
import org.example.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements IDAO<Customer> {

    @Override
    public List<Customer> GetAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedstatement = connection.prepareStatement("SELECT * FROM customer_info")) {

            ResultSet resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setPhone(resultSet.getString(3));
                customer.setDisabled(resultSet.getInt(4) == 1);

                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer getById(Integer id) {
        Customer customer = new Customer();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from customer_info where customer_id=?")) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setPhone(resultSet.getString(3));
                customer.setDisabled(resultSet.getInt(4) == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    @Override
    public boolean update(final Customer object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer_info SET customer_name =?, customer_contact_number =? where customer_id =?")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getPhone());
            preparedStatement.setInt(3, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Customer with id " + object.getId() + " has been updated");
                return true;
            } else {
                Logger.info("There is no customer with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean create(final Customer object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer_info(customer_name, customer_contact_number) VALUES(?,?)")) {

            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getPhone());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Customer with name " + object.getName() + " has been added");
                return true;
            } else {
                Logger.info("Customer with name " + object.getName() + " cannot be added");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Customer object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer_info where customer_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Customer with id " + object.getId() + " has been deleted");
                return true;
            } else {
                Logger.info("There is no project with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean disable(final Customer object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE customer_info SET disabled=1 where customer_id =?");
        PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE project_info SET disabled=1 where customer_id =?")) {

            preparedStatement1.setInt(1, object.getId());
            preparedStatement2.setInt(1, object.getId());

            if (preparedStatement1.executeUpdate() > 0) {
                Logger.info("Customer with id " + object.getId() + " has been disabled");
                if(preparedStatement2.executeUpdate() > 0){
                    Logger.info("Projects with customer id " + object.getId() + " have been disabled");
                    return true;
                }
                else{
                    Logger.info("There are no projects with customer id " + object.getId());
                }
            } else {
                Logger.info("There is no customer with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean enable(final Customer object) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/timesheet", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer_info SET disabled=0 where customer_id =?")) {

            preparedStatement.setInt(1, object.getId());

            if (preparedStatement.executeUpdate() > 0) {
                Logger.info("Customer with id " + object.getId() + " has been enabled");
                return true;
            } else {
                Logger.info("There is no customer with id " + object.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
