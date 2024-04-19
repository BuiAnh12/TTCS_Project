package com.controller;

import com.model.Import;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
import java.sql.CallableStatement;
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
        String storedProcedure = "GetImportByStatusAndSearchKey(?,?)";
        String searchTerm = "%" + name + "%";
        try {
            imports.clear();
            CallableStatement statement = cnn.prepareCall("{call " + storedProcedure + "}");
            statement.setInt(1, status);
            statement.setString(2, searchTerm);
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
        String storedProcedure = "{call InsertImport(?,?,?,?,?,?,?)}";
        
        try {
            PreparedStatement pre = cnn.prepareStatement(storedProcedure);
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
        String storedProcedure = "{call UpdateImport(?,?,?,?,?,?,?,?)}";
        try {
            PreparedStatement pre = cnn.prepareStatement(storedProcedure);
            pre.setInt(2, importss.getProductId());
            pre.setDate(3, (java.sql.Date) importss.getManufacturingDate());
            pre.setDate(4, (java.sql.Date) importss.getExpiryDate());
            pre.setDate(5, (java.sql.Date) importss.getImportDate());
            pre.setInt(6, importss.getImportQuantity());
            pre.setInt(7, importss.getAvailableQuantity());
            pre.setBigDecimal(8, importss.getUnitPrice());
            pre.setInt(1, importss.getImportId());
            
            int tmp = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteImport(int id) throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        Statement statement = cnn.createStatement();
        String storedProcedure = "{call DeleteImport(?)}";
        try {
            CallableStatement  pre = cnn.prepareCall(storedProcedure);
            pre.setInt(1, id);
            boolean tmp = pre.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Import> findListImport(String name) throws SQLException {
        List<Import> importList = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String storedProcedure = "{call SelectProductsByProductName(?)}";
        String searchTerm = "%" + name + "%";

        try {
            importList.clear();
            CallableStatement statement = cnn.prepareCall(storedProcedure);
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
        
        String storedProcedure =" {call UpdateImportByImportIdandQuatity(?,?)}";

        try {
            CallableStatement pre = cnn.prepareCall(storedProcedure);
            pre.setInt(1, quantity);
            pre.setInt(2, importId);
            boolean tmp = pre.execute();
            System.out.println("Update success");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> getProductName() throws SQLException {
        Connection cnn = ConnectionDB.getConnection();
        List<String> productName = new ArrayList<>();
        String storedProcedure =" {call SelectProductName}";
        try {
            CallableStatement pre = cnn.prepareCall(storedProcedure);
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
        String storedProcedure =" {call SelectDistinctProductIdByProductName(?)}";
       try{
        CallableStatement pre = cnn.prepareCall(storedProcedure);
        pre.setString(1, name);
        ResultSet re = pre.executeQuery();
        while (re.next()) {
            productId = re.getInt("ProductId");
        }
       }catch(Exception e){
           e.printStackTrace();
       }
        return productId;
    }
}
