package com.controller;

import com.model.Customer;

import com.control.db.ConnectionDB;
import com.model.DetailCustomer;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class controller_Customer {

    //
    public List<Customer> getAllCustomers(int status, String name) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String sp = "GetCustomerData(?,?)";

        try {
            customers.clear();
            CallableStatement statement = cnn.prepareCall("{call " + sp + "}");
            statement.setInt(1, status);
            statement.setString(2, name);
            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int id = re.getInt("CustomerId");
                String customername = re.getString("CustomerName");
                String email = re.getString("Email");
                String address = re.getString("Address");
                int totalAmount = re.getInt("TotalAmount");
                Customer customer = new Customer(id, customername, email, address, totalAmount);
                customers.add(customer);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomer(int id) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String query = "SELECT * FROM CUSTOMERS";

        try {

            PreparedStatement statement = cnn.prepareStatement(query);
            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int cusomterId = re.getInt("CustomerId");
                String customername = re.getString("CustomerName");
                String email = re.getString("Email");
                String address = re.getString("Address");
                int totalAmount = re.getInt("TotalAmount");
                Customer customer = new Customer(cusomterId, customername, email, address, totalAmount);
                return customer;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return null;

    }

    public void addCustomer(Customer customer) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String sp = "{call AddCustomer(?,?,?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setString(1, customer.getCustomerName());
            pre.setString(2, customer.getEmail());
            pre.setString(3, customer.getAddress());
            int tmp = pre.executeUpdate();
            pre.close();
            cnn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    public void editCustomer(Customer customer) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call EditCustomer(?,?,?,?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setString(2, customer.getCustomerName());
            pre.setString(3, customer.getEmail());
            pre.setString(4, customer.getAddress());
            pre.setInt(1, customer.getCustomerId());
            int tmp = pre.executeUpdate();
            pre.close();
            cnn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    public boolean deleteCustomer(int CustomerId) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        Statement statement = cnn.createStatement();
        String sp = "{call DeleteCustomer(?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setInt(1, CustomerId);
            int tmp = pre.executeUpdate();

            statement.close();
            cnn.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Customer> findListCustomer(String name) throws SQLException {
        List<Customer> customers = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call FindListCustomer(?)}";

        try {
            customers.clear();
            PreparedStatement statement = cnn.prepareStatement(sp);
            statement.setString(1, name);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int id = re.getInt("CustomerId");
                String customerName = re.getString("CustomerName");
                String email = re.getString("Email");
                String address = re.getString("Address");
                int totalAmount = re.getInt("TotalAmount");
                Customer customer = new Customer(id, customerName, email, address, totalAmount);
                customers.add(customer);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return customers;
    }

    public List<DetailCustomer> getDetail_Customers(int id) throws SQLException {
        List<DetailCustomer> details = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String sp = "{call GetDetailCustomers(?)}";

         try {
            details.clear();
            PreparedStatement statement = cnn.prepareStatement(sp);
            statement.setInt(1, id);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                String productName = re.getString("ProductName");
                int quanity = re.getInt("Quantity");
                BigDecimal sellPrice = re.getBigDecimal("SellPrice");
                BigDecimal total = re.getBigDecimal("TotalPrice");
                DetailCustomer customer = new DetailCustomer(productName, quanity, sellPrice, total);
                details.add(customer);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return details;
    }

}
