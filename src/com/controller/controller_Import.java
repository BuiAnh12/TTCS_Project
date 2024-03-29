package com.controller;

import com.model.Import;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;

public class controller_Import {

    public static List<Import> getAllImports(int status, String name) throws SQLException {

        List<Import> imports = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();
        String query = "";
        if (status == 1) {
            query = "SELECT * FROM Products JOIN Imports ON Products.ProductId = Imports.ProductId WHERE ProductName LIKE ? ORDER BY ProductName;";
        } else if (status == 2) {
            query = "SELECT * FROM Products JOIN Imports ON Products.ProductId = Imports.ProductId WHERE ProductName LIKE ? ORDER BY ImportQuantity;";
        } else if (status == 3) {
            query = "SELECT * FROM Products JOIN Imports ON Products.ProductId = Imports.ProductId WHERE ProductName LIKE ? ORDER BY AvailableQuantity;";
        }

        String searchTerm = "%" + name + "%";

        try {
            imports.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, searchTerm);

            ResultSet re = statement.executeQuery();
            while (re.next()) {
                int productid = re.getInt("ProductId");
                Date manuDate = re.getDate("ManufacturingDate");
                Date exDate = re.getDate("ExpiryDate");
                Date imDate = re.getDate("ImportDate");
                int importQuanity = re.getInt("ImportQuantity");
                int avaiableQuanity = re.getInt("AvailableQuantity");
                BigDecimal unitprice = re.getBigDecimal("UnitPrice");
                BigDecimal sellprice = re.getBigDecimal("SellPrice");
                int id = re.getInt("ImportId");
                String productName = re.getString("ProductName");
                String category = re.getString("Category");
                Import importss = new Import(id, productid, manuDate, exDate, imDate, importQuanity, avaiableQuanity, unitprice, sellprice, productName, category);
                imports.add(importss);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return imports;
    }

    public void addImport(Import importss) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        String query = "INSERT INTO Imports (ProductId, ManufacturingDate, ExpiryDate, ImportDate, ImportQuantity, AvailableQuantity, UnitPrice) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);
            pre.setInt(1, importss.getProductId());
            pre.setDate(2, (java.sql.Date) importss.getManufacturingDate());
            pre.setDate(3, (java.sql.Date) importss.getExpiryDate());
            pre.setDate(4, (java.sql.Date) importss.getImportDate());
            pre.setInt(5, importss.getImportQuantity());
            pre.setInt(6, importss.getAvailableQuantity());
            pre.setBigDecimal(7, importss.getUnitPrice());
            int tmp = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void editImport(Import importss) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();

        String query = "UPDATE Imports  SET ProductId =?, ManufacturingDate =?, ExpiryDate =?,"
                + "ImportDate = ?, ImportQuantity = ?, AvailableQuantity = ?,"
                + "UnitPrice = ? WHERE ImportId = ?";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);
            pre.setInt(1, importss.getProductId());
            pre.setDate(2, (java.sql.Date) importss.getManufacturingDate());
            pre.setDate(3, (java.sql.Date) importss.getExpiryDate());
            pre.setDate(4, (java.sql.Date) importss.getImportDate());
            pre.setInt(5, importss.getImportQuantity());
            pre.setInt(6, importss.getAvailableQuantity());
            pre.setBigDecimal(7, importss.getUnitPrice());
            pre.setInt(8, importss.getImportId());
            int tmp = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteImport(int id) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        Statement statement = cnn.createStatement();
        String query = "DELETE FROM Imports WHERE ImportId =?";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);
            pre.setInt(1, id);
            int tmp = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Import> findListImport(String name) throws SQLException {
        List<Import> importList = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String query = "SELECT * FROM Products JOIN Imports ON Products.ProductId = Imports.ProductId WHERE ProductName LIKE ?";
        String searchTerm = "%" + name + "%";

        try {
            importList.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, searchTerm);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int productid = re.getInt("ProductId");
                Date manuDate = re.getDate("ManufacturingDate");
                Date exDate = re.getDate("ExpiryDate");
                Date imDate = re.getDate("ImportDate");
                int importQuanity = re.getInt("ImportQuantity");
                int avaiableQuanity = re.getInt("AvailableQuantity");
                BigDecimal unitprice = re.getBigDecimal("UnitPrice");
                BigDecimal sellprice = re.getBigDecimal("SellPrice");
                int id = re.getInt("ImportId");
                String productName = re.getString("ProductName");
                String category = re.getString("Category");

                Import imports = new Import(id, productid, manuDate, exDate, imDate, importQuanity, avaiableQuanity, unitprice, sellprice, productName, category);
                importList.add(imports);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return importList;
    }

    public void editImport(int importId, int quantity) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();

        String query = "UPDATE Imports  SET AvailableQuantity = AvailableQuantity + ?"
                + " WHERE ImportId = ?";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);
            pre.setInt(1, quantity);
            pre.setInt(2, importId);
            int tmp = pre.executeUpdate();
            System.out.println("Update success");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> getProductName() throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        List<String> productName = new ArrayList<>();
        String query = "select ProductName from Products p join Imports i on p.ProductId=i.ProductId";
        try {
            PreparedStatement pre = cnn.prepareStatement(query);

            ResultSet re = pre.executeQuery();
            while (re.next()) {
                productName.add(re.getString("ProductName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productName;
    }

    public static int getProductId(String name) throws SQLException {
        int productId = 0;

        Connection cnn = ConnectionDB.getConnection();
        String query = """
                       select distinct p.ProductId 
                       from Products p 
                       join Imports i 
                       on p.ProductId=i.ProductId
                       where p.ProductName = ?""";
        PreparedStatement pre = cnn.prepareStatement(query);
        pre.setString(1, name);
        ResultSet re = pre.executeQuery();
        while (re.next()) {
            productId = re.getInt("ProductId");
        }
        return productId;
    }
}
