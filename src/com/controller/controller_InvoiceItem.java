
package com.controller;

import com.model.InvoiceItem;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class controller_InvoiceItem {
    public List<InvoiceItem> getAllInvoiceItems() throws SQLException {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call GetAllInvoiceItems()}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int invoiceItemId = re.getInt("InvoiceItemId");
                int invoiceId = re.getInt("InvoiceId");
                int importId = re.getInt("ImportId");
                BigDecimal unitPrice = re.getBigDecimal("UnitPrice");
                int quantity = re.getInt("Quantity");
                BigDecimal totalPrice = re.getBigDecimal("TotalPrice");
                BigDecimal profit = re.getBigDecimal("Profit");
                InvoiceItem invoiceItem = new InvoiceItem(invoiceItemId, invoiceId, importId, unitPrice, quantity, totalPrice, profit);
                invoiceItems.add(invoiceItem);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (cnn != null) {
                cnn.close(); // Close connection in finally block
            }
        }

        return invoiceItems;
    }

      
    public void addInvoiceItem(InvoiceItem invoiceItem) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call AddInvoiceItem(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, invoiceItem.getInvoiceId());
            statement.setInt(2, invoiceItem.getImportId());
            statement.setBigDecimal(3, invoiceItem.getUnitPrice());
            statement.setInt(4, invoiceItem.getQuantity());
            statement.setBigDecimal(5, invoiceItem.getTotalPrice());
            statement.setBigDecimal(6, invoiceItem.getProfit());

            int tmp = statement.executeUpdate();
            System.out.println("run");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("fail");
        } finally {
            if (cnn != null) {
                cnn.close(); // Close connection in finally block
            }
        }
    }

    public void editInvoiceItem(InvoiceItem invoiceItem) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call EditInvoiceItem(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, invoiceItem.getInvoiceId());
            statement.setInt(2, invoiceItem.getImportId());
            statement.setBigDecimal(3, invoiceItem.getUnitPrice());
            statement.setInt(4, invoiceItem.getQuantity());
            statement.setBigDecimal(5, invoiceItem.getTotalPrice());
            statement.setBigDecimal(6, invoiceItem.getProfit());
            statement.setInt(7, invoiceItem.getInvoiceItemId());

            int tmp = statement.executeUpdate();
            System.out.println("Update success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception if needed
        } finally {
            if (cnn != null) {
                cnn.close(); // Close connection in finally block
            }
        }
    }

    public void deleteInvoiceItem(int invoiceItemId) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call DeleteInvoiceItem(?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, invoiceItemId);

            int tmp = statement.executeUpdate();
            System.out.println("Delete success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception if needed
        } finally {
            if (cnn != null) {
                cnn.close(); // Close connection in finally block
            }
        }
    }
  
    
    public void deleteInvoiceItemId(int invoiceId) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String storedProcedure = "{call DeleteInvoiceItemsByInvoiceId(?)}";

        try (CallableStatement statement = cnn.prepareCall(storedProcedure)) {
            statement.setInt(1, invoiceId);

            int tmp = statement.executeUpdate();
            System.out.println("Delete success");
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception if needed
        } finally {
            if (cnn != null) {
                cnn.close(); // Close connection in finally block
            }
        }
    }


      
}
