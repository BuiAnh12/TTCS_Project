
package com.controller;
import com.model.Product;

import com.control.db.ConnectionDB;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class controller_Product {
    public List<Product> getAllAvailableProduct() throws SQLException {
        List<Product> products =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         String query="select distinct p.*, sum(i.AvailableQuantity) as totalAmount" +
                " from Products p" +
                " join Imports i on p.ProductId = i.ProductId" +
                " where i.AvailableQuantity > 0" +
                " group by p.ProductId, p.Category, p.Description, p.Manufacturer, p.ProductName, p.Flag,p.SellPrice;";
         try{
                products.clear();
                PreparedStatement statement = cnn.prepareStatement(query);
                ResultSet re = statement.executeQuery();
                
                while(re.next()){
                    String productName=re.getString("ProductName");
                    String manufacturer=re.getString("Manufacturer");
                    String description=re.getString("Description");
                    String category=re.getString("Category");
                    int id=re.getInt("ProductId");
                    int quantity = re.getInt("totalAmount");
                    BigDecimal sellPrice = re.getBigDecimal("SellPrice");
                    Product product =new Product(id, productName, manufacturer, description, category, quantity,sellPrice);
                    products.add(product);
                }
               
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return products;
    }
    
     public List<Product> getAllproduct(int status,String name) throws SQLException{
         List<Product> products =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         String query="";
         
         if(status ==1){
             query="SELECT * FROM Products WHERE ProductName LIKE ? ORDER BY ProductName ";
         }else if(status ==2){
             query="SELECT * FROM Products WHERE ProductName LIKE ? ORDER BY Manufacturer ";
         }else if(status==3){
             query="SELECT * FROM Products WHERE ProductName LIKE ? ORDER BY Category ";
         }
         String searchTerm = "%" + name + "%";
         try{
                products.clear();
                PreparedStatement statement = cnn.prepareStatement(query);
                statement.setString(1, searchTerm);
                ResultSet re = statement.executeQuery();
                
                while(re.next()){
                    String productName=re.getString("ProductName");
                    String manufacturer=re.getString("Manufacturer");
                    String description=re.getString("Description");
                    String category=re.getString("Category");
                    int id=re.getInt("ProductId");
                    BigDecimal sellPrice = re.getBigDecimal("SellPrice");
                    Product product =new Product(id, productName, manufacturer, description, category, sellPrice);
                    products.add(product);
                }
               
         }   
         catch(Exception ex){
             ex.printStackTrace();
         }   
        return products;
    }
      public void addProduct(Product product) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Products (ProductName, Manufacturer, Description, Category, SellPrice) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, product.getProductName());       
            pre.setString(2, product.getManufacturer());
            pre.setString(3, product.getDescription());
            pre.setString(4, product.getCategory());
            pre.setBigDecimal(5, product.getSellPrice());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
       public void editProduct(Product product) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Products SET  ProductName =?,Manufacturer =?,Description =?,Category=?,SellPrice=? WHERE ProductId =?";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setString(1, product.getProductName());       
            pre.setString(2, product.getManufacturer());
            pre.setString(3, product.getDescription());
            pre.setString(4, product.getCategory());
            pre.setBigDecimal(5, product.getSellPrice());
            pre.setInt(6,product.getProductId());
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      public void deleteProduct(int id) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Products WHERE ProductId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,id);       
            int tmp=pre.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      public List<Product> findListProduct(String name) throws SQLException{
        List<Product> products = new ArrayList<>();
        Connection cnn = ConnectionDB.getConnection();

        String query = "SELECT * FROM Products WHERE ProductName LIKE ?";
        String searchTerm = "%" + name + "%";

        try {
            products.clear();
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setString(1, searchTerm);

            ResultSet re = statement.executeQuery();

            while (re.next()) {
                int id = re.getInt("ProductId");
                String productName = re.getString("ProductName");
                String manufacturer = re.getString("Manufacturer");
                String description = re.getString("Description");
                String category = re.getString("Category");
                BigDecimal sellPrice = re.getBigDecimal("SellPrice");
                Product product = new Product(id, productName, manufacturer, description, category, sellPrice);
                products.add(product);
            }

            statement.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return products;
    }
      
    
}
