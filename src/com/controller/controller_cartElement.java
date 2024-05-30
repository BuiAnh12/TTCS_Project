

package com.controller;

import com.control.db.ConnectionDB;
import com.model.CartElement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class controller_cartElement {
    public List<CartElement> getAllCartElement(int invoiceId) throws SQLException{
        List<CartElement> cartList =new ArrayList<>();
        Connection cnn=ConnectionDB.getConnection();
        
        String query = "with t1 as (" +
                        "select item.ImportId, item.Quantity, item.TotalPrice, item.InvoiceItemId, item.UnitPrice " +
                        "from Invoices invoice " +
                        "join Invoice_Items item on invoice.InvoiceId = item.InvoiceId " +
                        "where invoice.InvoiceId = ? " +
                        ") " +
                        "select p.ProductId, p.ProductName, t1.Quantity, p.SellPrice, t1.TotalPrice,t1.InvoiceItemId, i.ImportId,t1.UnitPrice " +
                        "from Imports i " +
                        "join t1 on i.ImportId =t1.ImportId " +
                        "join Products p on p.ProductId = i.ProductId";
        try{
            PreparedStatement statement = cnn.prepareStatement(query);
            statement.setInt(1, invoiceId);
            ResultSet re = statement.executeQuery();
            while(re.next()){
                int productId=re.getInt("ProductId");
                String productName=re.getString("ProductName");
                int quantity=re.getInt("Quantity");
                BigDecimal sellPrice =re.getBigDecimal("SellPrice");
                BigDecimal totalPrice =re.getBigDecimal("TotalPrice");
                int invoiceItemId = re.getInt("InvoiceItemId");
                int importId = re.getInt("ImportId");
                BigDecimal unitPrice =re.getBigDecimal("UnitPrice");
                CartElement cart = new CartElement(productId,productName,quantity,sellPrice,totalPrice,-1,invoiceItemId,importId,unitPrice);
                cartList.add(cart);
            }
            return cartList;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

