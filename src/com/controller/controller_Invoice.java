
package com.controller;

import com.model.Invoice;

import com.control.db.ConnectionDB;
import com.model.Import;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class controller_Invoice {
     public List<Invoice> getAllInvoices(int status ,String name) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String query="";
        if(status==1){
            query = "SELECT DISTINCT Invoices.InvoiceId, Customers.CustomerId, Staffs.StaffId, PurchaseDate, CustomerName, Staffs.Name AS StaffName, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                  "FROM Invoices " +
                  "JOIN Customers ON Customers.CustomerId = Invoices.CustomerId " +
                  "JOIN Staffs ON Staffs.StaffId = Invoices.StaffId " +
                  "JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                  "GROUP BY " +
                  "Invoices.InvoiceId, " +
                  "Customers.CustomerId, " +
                  "Staffs.StaffId, " +
                  "PurchaseDate, " +
                  "CustomerName, " +
                  "Staffs.Name " +
                  "HAVING CustomerName LIKE ? " +
                  "ORDER BY CustomerName;";


        }else if(status ==2){
           query = "SELECT DISTINCT Invoices.InvoiceId, Customers.CustomerId, Staffs.StaffId, PurchaseDate, CustomerName, Staffs.Name AS StaffName, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                  "FROM Invoices " +
                  "JOIN Customers ON Customers.CustomerId = Invoices.CustomerId " +
                  "JOIN Staffs ON Staffs.StaffId = Invoices.StaffId " +
                  "JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                  "GROUP BY " +
                  "Invoices.InvoiceId, " +
                  "Customers.CustomerId, " +
                  "Staffs.StaffId, " +
                  "PurchaseDate, " +
                  "CustomerName, " +
                  "Staffs.Name " +
                  "HAVING CustomerName LIKE ? " +
                  "ORDER BY StaffName;";


        }else if(status ==3){
            query = "SELECT DISTINCT Invoices.InvoiceId, Customers.CustomerId, Staffs.StaffId, PurchaseDate, CustomerName, Staffs.Name AS StaffName, SUM(Invoice_Items.TotalPrice) AS TotalAmount " +
                  "FROM Invoices " +
                  "JOIN Customers ON Customers.CustomerId = Invoices.CustomerId " +
                  "JOIN Staffs ON Staffs.StaffId = Invoices.StaffId " +
                  "JOIN Invoice_Items ON Invoice_Items.InvoiceId = Invoices.InvoiceId " +
                  "GROUP BY " +
                  "Invoices.InvoiceId, " +
                  "Customers.CustomerId, " +
                  "Staffs.StaffId, " +
                  "PurchaseDate, " +
                  "CustomerName, " +
                  "Staffs.Name " +
                  "HAVING CustomerName LIKE ? " +
                  "ORDER BY TotalAmount;";


        }
        String searchTerm = "%" + name + "%";
        try {
            invoices.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, searchTerm);
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int invoiceId = re.getInt("InvoiceId");
                int customerId = re.getInt("CustomerId");
                int staffId = re.getInt("StaffId");
                Date purchaseDate = re.getDate("PurchaseDate");
                String customerName = re.getString("CustomerName");
                String staffName = re.getString("StaffName");
                int totalAmount = re.getInt("TotalAmount");

                Invoice invoice = new Invoice(invoiceId, customerId, staffId, purchaseDate, customerName, staffName, totalAmount);
                invoices.add(invoice);
            }

        } catch (SQLException ex) {
            // Xử lý ngoại lệ nếu cần
            ex.printStackTrace();
        }

        return invoices;
    }


    public int addInvoice(Invoice invoice) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String query = "INSERT INTO Invoices (CustomerId, StaffId, PurchaseDate) VALUES (?, ?, ?)";

        try (PreparedStatement pre = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1, invoice.getCustomerId());
            pre.setInt(2, invoice.getStaffId());
            pre.setDate(3, (java.sql.Date) invoice.getPurchaseDate());

            int tmp = pre.executeUpdate();

            if (tmp > 0) {
                ResultSet generatedKeys = pre.getGeneratedKeys();
                if (generatedKeys.next()) {
                    System.out.println("run");
                    return generatedKeys.getInt(1); // Assuming InvoiceId is an auto-increment primary key
                    // Set the generated ID to the invoice object or use it as needed
                } else {
                    // Handling case where no keys were generated
                }
            }
        } catch (SQLException ex) {
            System.out.println("fail");
            ex.printStackTrace(); // Log the exception for debugging
        }

        return -1;
    }


      public void editInvoice(Invoice invoice) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Invoices SET  CustomerId =?,StaffId =?,PurchaseDate =? WHERE InvoiceId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoice.getCustomerId());
            pre.setInt(2, invoice.getStaffId());
            pre.setDate(3, (java.sql.Date) invoice.getPurchaseDate());
            //pre.setString(4,invoice.getCustomerName());
           // pre.setString(5,invoice.getStaffName());
           // pre.setInt(6,invoice.getTotalAmount());
            pre.setInt(4,invoice.getInvoiceId());

            int re = pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      
      

     public void deleteInvoice (int invoiceId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         String query="DELETE FROM Invoices WHERE InvoiceId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceId);
            int tmp= pre.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public List<Import> findAvailableId(int productId, int quantity) throws SQLException{
        List<Import>  listImport = new ArrayList();
        Connection cnn = ConnectionDB.getConnection();
        String query1 = "WITH t1 AS (" +
                "    SELECT top 1 i1.ImportId" +
                "    FROM Imports i1" +
                "    JOIN Imports i2 ON i1.ProductId = i2.ProductId AND i1.ExpiryDate >= i2.ExpiryDate" +
                "    WHERE i1.ProductId = ? AND i1.AvailableQuantity > 0" +
                "    GROUP BY i1.ImportId" +
                "    HAVING SUM(i2.AvailableQuantity) >= ?" +
                ")";
        String query2 = 
                "SELECT *" +
                " FROM Imports" +  // Added space here
                " WHERE ProductId = ? and AvailableQuantity > 0" +
                "   AND ExpiryDate <= (" +
                "       SELECT ExpiryDate" +
                "       FROM Imports" +
                "       WHERE ImportId = (SELECT ImportId FROM t1)" +
                "   )" +
                " ORDER BY ExpiryDate ASC;";

        String query = query1 + query2;

        try (PreparedStatement pre = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pre.setInt(1, productId);
            pre.setInt(2, quantity);
            pre.setInt(3, productId);
            ResultSet re = pre.executeQuery();
            while (re.next()) {
                int productid=re.getInt("ProductId");              
                java.sql.Date manuDate=re.getDate("ManufacturingDate");
                java.sql.Date exDate=re.getDate("ExpiryDate");
                java.sql.Date imDate=re.getDate("ImportDate");
                int importQuanity=re.getInt("ImportQuantity");
                int avaiableQuanity=re.getInt("AvailableQuantity");
                BigDecimal unitprice=re.getBigDecimal("UnitPrice");
                BigDecimal sellprice=re.getBigDecimal("SellPrice");
                int id=re.getInt("ImportId");
                

                Import importss =new Import(id, productid, manuDate, exDate, imDate, importQuanity, avaiableQuanity,unitprice, sellprice);
                listImport.add(importss);
                
            }
            return listImport;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
