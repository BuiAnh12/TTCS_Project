
package com.controller;

import com.model.Invoice;

import com.control.db.ConnectionDB;
import com.model.Import;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;



public class controller_Invoice {
    public List<Invoice> getAllInvoices(int status, String name) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "GetInvoicesByStatusAndName(?, ?)";
        String searchTerm = "%" + name + "%";

        try {
            invoices.clear();
            CallableStatement statement = cnn.prepareCall("{call " + storedProcedure + "}");
            statement.setInt(1, status);
            statement.setString(2, searchTerm);
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int invoiceId = re.getInt("InvoiceId");
                int customerId = re.getInt("CustomerId");
                int staffId = re.getInt("StaffId");
                Timestamp purchaseDate = re.getTimestamp("PurchaseDate");
                String customerName = re.getString("CustomerName");
                String staffName = re.getString("StaffName");
                int totalAmount = re.getInt("TotalAmount");
                Timestamp updateDate = re.getTimestamp("UpdateDate");
                Invoice invoice = new Invoice(invoiceId, customerId, staffId, purchaseDate, customerName, staffName, totalAmount,updateDate);
                invoices.add(invoice);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return invoices;
    }



    public int addInvoice(Invoice invoice) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call AddInvoice(?, ?, ?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, invoice.getCustomerId());
            statement.setInt(2, invoice.getStaffId());
            statement.registerOutParameter(3, Types.INTEGER); 

            statement.executeUpdate();

            // Retrieve the generated InvoiceId
            int invoiceId = statement.getInt(3);
            return invoiceId;
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }

        return -1; 
    }



    public void editInvoice(Invoice invoice) throws SQLException {
        String storedProcedure = "{call EditInvoice(?, ?, ?)}";

        try (Connection cnn = ConnectionDB.getConnection();
             CallableStatement statement = cnn.prepareCall(storedProcedure)) {

            statement.setInt(1, invoice.getCustomerId());
            statement.setInt(2, invoice.getStaffId());
            statement.setInt(3, invoice.getInvoiceId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            throw ex; 
        }
    }

    
      

    public void deleteInvoice(int invoiceId) throws SQLException {
        String storedProcedure = "{call DeleteInvoice(?)}";

        try (Connection cnn = ConnectionDB.getConnection();
             CallableStatement statement = cnn.prepareCall(storedProcedure)) {

            statement.setInt(1, invoiceId);

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(); 
            throw ex; // Re-throw the exception if necessary
        }
    }



    public List<Import> findAvailableId(int productId, int quantity) throws SQLException {
        List<Import> listImport = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call FindAvailableImports(?, ?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, productId);
            statement.setInt(2, quantity);

            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int productid = re.getInt("ProductId");
                java.sql.Date manuDate = re.getDate("ManufacturingDate");
                java.sql.Date exDate = re.getDate("ExpiryDate");
                java.sql.Date imDate = re.getDate("ImportDate");
                int importQuanity = re.getInt("ImportQuantity");
                int avaiableQuanity = re.getInt("AvailableQuantity");
                BigDecimal unitprice = re.getBigDecimal("UnitPrice");
                int id = re.getInt("ImportId");
                Import imports = new Import(id, productid, manuDate, exDate, imDate, importQuanity, avaiableQuanity, unitprice);
                listImport.add(imports);
            }
            return listImport;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (cnn != null) {
                cnn.close(); 
            }
        }
    }

}
