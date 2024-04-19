
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
import java.sql.CallableStatement;
import java.sql.PreparedStatement;


public class controller_Product {
    public List<Product> getAllAvailableProduct() throws SQLException {
        List<Product> products =new ArrayList<>();
        
//         String query="select distinct p.*, sum(i.AvailableQuantity) as totalAmount" +
//                " from Products p" +
//                " join Imports i on p.ProductId = i.ProductId" +
//                " where i.AvailableQuantity > 0" +
//                " group by p.ProductId, p.Category, p.Description, p.Manufacturer, p.ProductName, p.Flag,p.SellPrice;";
         try{
                Connection cnn=ConnectionDB.getConnection();
                CallableStatement  statement = cnn.prepareCall("{call getAllAvailableProduct}");
                products.clear();
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
         String query="";
         
         String searchTerm = "%" + name + "%";
         try{
                Connection cnn=ConnectionDB.getConnection();
                products.clear();
                CallableStatement stmt = cnn.prepareCall("{call getAllProduct(?, ?)}");
                stmt.setInt(1, status);
                stmt.setString(2, searchTerm);
                ResultSet re = stmt.executeQuery();
                
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
      
        try{
             Connection cnn=ConnectionDB.getConnection();
            CallableStatement stmt = cnn.prepareCall("{call addProduct(?, ?, ?, ?, ?)}");
            stmt.setString(1, product.getProductName());       
            stmt.setString(2, product.getManufacturer());
            stmt.setString(3, product.getDescription());
            stmt.setString(4, product.getCategory());
            stmt.setBigDecimal(5, product.getSellPrice());
            int tmp=stmt.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //
    public void editProduct(Product product) throws SQLException{
           
        try{
            Connection cnn=ConnectionDB.getConnection();
            CallableStatement stmt = cnn.prepareCall("{call editProduct(?, ?, ?, ?, ?, ?)}");
               
            stmt.setString(2, product.getProductName());       
            stmt.setString(3, product.getManufacturer());
            stmt.setString(4, product.getDescription());
            stmt.setString(5, product.getCategory());
            stmt.setBigDecimal(6, product.getSellPrice());
            stmt.setInt(1,product.getProductId());
            int tmp=stmt.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      public void deleteProduct(int id) throws SQLException{
         try{
            Connection cnn=ConnectionDB.getConnection();
            CallableStatement stmt = cnn.prepareCall("{call deleteProduct(?)}");
            
            stmt.setInt(1,id);       
            int tmp=stmt.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
      public List<Product> findListProduct(String name) throws SQLException{
        List<Product> products = new ArrayList<>();
        String searchTerm = "%" + name + "%";

        try {
            products.clear();
            Connection cnn = ConnectionDB.getConnection();
            CallableStatement stmt = cnn.prepareCall("{call findListProduct(?)}");
            stmt.setString(1, searchTerm);
            ResultSet re = stmt.executeQuery();

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

            stmt.close();
            cnn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQL exception (show a message dialog, log the error, etc.)
        }
        return products;
    }
      
    
}
