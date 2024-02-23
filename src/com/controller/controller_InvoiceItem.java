
package com.controller;

import com.model.InvoiceItem;

import com.control.db.ConnectionDB;
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

public class controller_InvoiceItem {
      public List<InvoiceItem>getAllInvoiceItems() throws SQLException{
         List<InvoiceItem> invoiceItems =new ArrayList<>();
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="SELECT * FROM Invoice_Items";
         try{
                ResultSet re=statement.executeQuery(query);
                while(re.next()){
                    int invoiceitemId=re.getInt("InvoiceItemId");
                    int invoiceid=re.getInt("InvoiceId");               
//                    int productid=re.getInt("ProductId");
                    int importid=re.getInt("ImportId");
                    BigDecimal unitprice=re.getBigDecimal("UnitPrice");
                    int quanity=re.getInt("Quantity");
                    BigDecimal totalprice=re.getBigDecimal("TotalPrice");
                    BigDecimal profit=re.getBigDecimal("Profit");   
                     java.sql.Date createAt=re.getDate("CreatedAt");            
                    java.sql.Date updateAt=re.getDate("UpdatedAt");
                     InvoiceItem invoiceitem=new InvoiceItem(invoiceitemId, invoiceid, importid, unitprice, quanity, totalprice, profit);
                    invoiceItems.add(invoiceitem);
                }
               
         }   
         catch(SQLException ex){
         }   
        return invoiceItems;
    }
      
       public void addInvoiceItem(InvoiceItem invoiceitem) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();
        String query="INSERT INTO Invoice_Items (InvoiceId, ImportId, UnitPrice, Quantity, TotalPrice, Profit) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoiceitem.getInvoiceId());                    
//            pre.setInt(2, invoiceitem.getProductId());  
            pre.setInt(2, invoiceitem.getImportId());
            pre.setBigDecimal(3, invoiceitem.getUnitPrice());
            pre.setInt(4, invoiceitem.getQuantity());
            pre.setBigDecimal(5, invoiceitem.getTotalPrice());
            pre.setBigDecimal(6, invoiceitem.getProfit());
            
            //Date createAt = Date.from(invoiceitem.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            //pre.setDate(8, (java.sql.Date) createAt);
      
            //Date uppdateAt = Date.from(invoiceitem.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            //pre.setDate(9, (java.sql.Date) uppdateAt);
            int tmp = pre.executeUpdate();
            System.out.println("run");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("fail");
        }
    } 
     public void editInvoiceItem(InvoiceItem invoiceitem) throws SQLException{
        Connection cnn=ConnectionDB.getConnection();

        String query="UPDATE Invoice_Items SET  InvoiceId=?, ImportId=?, UnitPrice=?, Quantity=?, TotalPrice=?, Profit=? WHERE InvoiceItemId =?";
        try{
           PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1, invoiceitem.getInvoiceId());                    
//            pre.setInt(2, invoiceitem.getProductId());  
            pre.setInt(2, invoiceitem.getImportId());
            pre.setBigDecimal(3, invoiceitem.getUnitPrice());
            pre.setInt(4, invoiceitem.getQuantity());
            pre.setBigDecimal(5, invoiceitem.getTotalPrice());
            pre.setBigDecimal(6,invoiceitem.getProfit());
            
            //Date createAt = Date.from(invoiceitem.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
            //pre.setDate(8, (java.sql.Date) createAt);
      
            //Date uppdateAt = Date.from(invoiceitem.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant());
            //pre.setDate(9, (java.sql.Date) uppdateAt);
            pre.setInt(7,invoiceitem.getInvoiceItemId() );
            
            int tmp=pre.executeUpdate();
            System.out.println("Update success");
        }
        catch (SQLException ex) {
        }
    }   
    public void deleteInvoiceItem (int invoiceItemId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Invoice_Items WHERE InvoiceItemId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceItemId);       
            int tmp=pre.executeUpdate();
        }
        catch (SQLException ex) {
        }
    }  
    public void deleteInvoiceItemId (int invoiceId) throws SQLException{
         Connection cnn=ConnectionDB.getConnection();
         Statement statement=cnn.createStatement();
         String query="DELETE FROM Invoice_Items WHERE InvoiceId =?";
         try{
            PreparedStatement pre=cnn.prepareStatement(query);
            pre.setInt(1,invoiceId);       
            int tmp=pre.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      
}
