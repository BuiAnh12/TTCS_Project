package com.controller;
import com.control.db.ConnectionDB;
import com.model.Staff;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class controller_Account {
    private Staff account;

    public Staff getAccount() {
        return account;
    }

    public void setAccount(Staff account) {
        this.account = account;
    }
    
    
        private boolean checkCredentials(String username, String password) throws SQLException {
        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isValid = false;

        try {
            cnn = ConnectionDB.getConnection();
            String query = "SELECT COUNT(*) AS count FROM Staffs WHERE Username = ? AND Password = ?";
            preparedStatement = cnn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                isValid = count > 0; // If count > 0, credentials are valid
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        
        return isValid;
    }
    
    public Staff login(String username, String password) throws SQLException{
        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet re = null;
        
        if (this.checkCredentials(username, password)){
            try {
                cnn = ConnectionDB.getConnection();
                String query = "SELECT * FROM Staffs WHERE Username = ? AND Password = ?";
                preparedStatement = cnn.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                re = preparedStatement.executeQuery();
                if (re.next()) {
                    int id=re.getInt("StaffId");
                    String staffsname=re.getString("Name");
                    int age=re.getInt("Age");
                    String email=re.getString("Email");
                    String address=re.getString("Address");
                    int accountPrevilege = re.getInt("AccountPrevilege");
                    this.account =new Staff(id, staffsname, age, email, address,username, password, accountPrevilege);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Close resources
                if (re != null) {
                    re.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (cnn != null) {
                    cnn.close();
                }
            }


        }
        return account;
    }
        
    public void logout(){
        account = null;
    }
    
    
}
