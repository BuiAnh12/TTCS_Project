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

public class controller_Staff {
     public List<Staff>getAllStaff(int status , String name) throws SQLException{
         List<Staff> staffs =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         
         String query="";
         if(status==1){
             query="SELECT * FROM Staffs WHERE Name LIKE ? ORDER BY Name";
         }else if(status==2){
             query="SELECT * FROM Staffs WHERE Name LIKE ? ORDER BY Email";
         }else if(status==3){
             query="SELECT * FROM Staffs WHERE Name LIKE ? ORDER BY AccountPrevilege";
         }
         String searchTerm = "%" + name + "%";
         try{
                staffs.clear();
                PreparedStatement statement = cnn.prepareStatement(query);
                statement.setString(1, searchTerm);
                ResultSet re = statement.executeQuery();
                while(re.next()){
                    int id=re.getInt("StaffId");
                    String staffsname=re.getString("Name");
                    int age=re.getInt("Age");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    String username = re.getString("Username");
                    String password = re.getString("Password");
                    int accountPrevilege = re.getInt("AccountPrevilege");
                    Staff staff =new Staff(id, staffsname, age, email, address,username, password, accountPrevilege);
                    staffs.add(staff);
                }
                
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return staffs;
    }
    // 
     public void addStaff(Staff staff) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Staffs (Name, Age, Email,Address, Username, Password, AccountPrevilege) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, staff.getName());   
            pre.setInt(2, staff.getAge());       
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getAddress());
            pre.setString(5, staff.getUsername());
            pre.setString(6, staff.getPassword());
            pre.setInt(7, staff.getPrevilege());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     //
        public void editStaff(Staff staff) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query = "UPDATE Staffs SET Name=?, Age=?, Email=?, Address=?, AccountPrevilege=?, Username=?, Password=? WHERE StaffId=?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, staff.getName());
            pre.setInt(2, staff.getAge());
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getAddress());
            pre.setInt(5, staff.getPrevilege());
            pre.setString(6, staff.getUsername());
            pre.setString(7, staff.getPassword());
            pre.setInt(8, staff.getStaffId());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        
        public void editAccount(Staff staff) throws SQLException{
            Connection cnn=ConnectionDB.getConnection();

            String query="UPDATE Staffs SET  Username =?,Password =?,AccountPrevilege =? WHERE StaffId =?";
            try{
                PreparedStatement pre=cnn.prepareStatement(query);
                pre.setString(1, staff.getUsername());       
                pre.setString(2, staff.getPassword());
                pre.setInt(3, staff.getPrevilege());
                pre.setInt(4, staff.getStaffId());
                int tmp=pre.executeUpdate();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    //
     public void deleteStaff(int staffId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Staffs WHERE StaffId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,staffId);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
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
                    if (count == 0){
                        System.out.println("Valid Username");
                        return true;
                        
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return false; 
        }
         public List<Staff> findListStaff(String name) throws SQLException{
            List<Staff> staffs = new ArrayList<>();
            Connection cnn = ConnectionDB.getConnection();

            String query = "SELECT * FROM Staffs WHERE Name LIKE ?";
            String searchTerm = "%" + name + "%";

            try {
                staffs.clear();
                PreparedStatement statement = cnn.prepareStatement(query);
                statement.setString(1, searchTerm);

                ResultSet re = statement.executeQuery();

                while (re.next()) {
                    int id=re.getInt("StaffId");
                    String staffName=re.getString("Name");
                    int age=re.getInt("Age");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    String username = re.getString("Username");
                    String password = re.getString("Password");
                    int accountPrevilege = re.getInt("AccountPrevilege");
                    Staff staff =new Staff(id,staffName, age, email, address,username, password, accountPrevilege);
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
}
