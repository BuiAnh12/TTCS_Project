package com.controller;

import com.control.db.ConnectionDB;
import com.model.Staff;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.Types;

public class controller_Staff {

    public List<Staff> getAllStaff(int status, String name) throws SQLException {
        List<Staff> staffs = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call GetAllStaff(?,?)}";
        try {
            staffs.clear();
            PreparedStatement statement = cnn.prepareStatement(sp);
            statement.setInt(1, status);
            statement.setString(2, name);
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int id = re.getInt("StaffId");
                String staffsname = re.getString("Name");
                int age = re.getInt("Age");
                String email = re.getString("Email");
                String address = re.getString("Address");
                String username = re.getString("Username");
                String password = re.getString("Password");
                int accountPrevilege = re.getInt("AccountPrevilege");
                Staff staff = new Staff(id, staffsname, age, email, address, username, password, accountPrevilege);
                staffs.add(staff);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staffs;
    }

    public int addStaff(Staff staff) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String sp = "{call AddStaff(?,?,?,?,?,?,?,?)}"; // Thêm một tham số output
        try {
            CallableStatement cs = cnn.prepareCall(sp);
            cs.setString(1, staff.getName());
            cs.setInt(2, staff.getAge());
            cs.setString(3, staff.getEmail());
            cs.setString(4, staff.getAddress());
            cs.setString(5, staff.getUsername());
            cs.setString(6, staff.getPassword());
            cs.setInt(7, staff.getPrevilege());

            // Đăng ký tham số output
            cs.registerOutParameter(8, Types.INTEGER);

            int rowsAffected = cs.executeUpdate();

            // Lấy giá trị trả về từ thủ tục lưu trữ
            int returnValue = cs.getInt(8);

            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int editStaff(Staff staff) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call EditStaff(?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cnn.prepareCall(sp);
            cs.setInt(1, staff.getStaffId());
            cs.setString(2, staff.getName());
            cs.setInt(3, staff.getAge());
            cs.setString(4, staff.getEmail());
            cs.setString(5, staff.getAddress());
            cs.setInt(6, staff.getPrevilege());
            cs.setString(7, staff.getUsername());
            cs.setString(8, staff.getPassword());

            // Đăng ký tham số output
            cs.registerOutParameter(8, Types.INTEGER);

            int rowsAffected = cs.executeUpdate();

            // Lấy giá trị trả về từ thủ tục lưu trữ
            int returnValue = cs.getInt(8);

            return returnValue;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public void editAccount(Staff staff) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call EditAccount(?,?,?,?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setInt(1, staff.getStaffId());
            pre.setString(2, staff.getUsername());
            pre.setString(3, staff.getPassword());
            pre.setInt(4, staff.getPrevilege());

            int tmp = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    public boolean deleteStaff(int staffId) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        Statement statement = cnn.createStatement();
        String sp = "{call DeleteStaff(?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setInt(1, staffId);
            int tmp = pre.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameAvailable(String usernameString) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String query = "SELECT COUNT(*) AS count FROM Staffs WHERE Username = ?";

        try (PreparedStatement pre = cnn.prepareStatement(query)) {
            pre.setString(1, usernameString);
            ResultSet resultSet = pre.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count == 0) {
                    System.out.println("Valid Username");
                    return true;

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public List<Staff> findListStaff(String name) throws SQLException {
        List<Staff> staffs = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String sp = "{call FindListStaff(?)}";
        String searchTerm = "%" + name + "%";

        try {
            staffs.clear();
            PreparedStatement statement = cnn.prepareStatement(sp);
            statement.setString(1, searchTerm);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int id = re.getInt("StaffId");
                String staffName = re.getString("Name");
                int age = re.getInt("Age");
                String email = re.getString("Email");
                String address = re.getString("Address");
                String username = re.getString("Username");
                String password = re.getString("Password");
                int accountPrevilege = re.getInt("AccountPrevilege");
                Staff staff = new Staff(id, staffName, age, email, address, username, password, accountPrevilege);
                staffs.add(staff);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return staffs;
    }

    public List<Integer> getAccountPrevilege() throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        List<Integer> list = new ArrayList<>();
        String sp = "{call GetAccountPrevilege}";
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);

            ResultSet re = pre.executeQuery();
            while (re.next()) {
                list.add(re.getInt("AccountPrevilege"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int getInfoStaff(int id) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String sp = "{call GetAccPreStaff(?)}";
        int accountPrivilege = -1;
        try {
            PreparedStatement pre = cnn.prepareStatement(sp);
            pre.setInt(1, id);
            ResultSet re = pre.executeQuery();
            while (re.next()) {
                accountPrivilege = re.getInt("AccountPrevilege");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return accountPrivilege;
    }
}
