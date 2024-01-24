package com.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
public class Validation {
    public boolean isValidDate(String dateStr) {
         // Chuỗi đại diện cho ngày
        java.sql.Date sqlDate = new java.sql.Date(0);
        try {
            // Định dạng cho chuỗi đầu vào
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Chuyển đổi chuỗi thành java.util.Date
            Date utilDate = dateFormat.parse(dateStr);

            // Chuyển đổi java.util.Date thành java.sql.Date
             sqlDate = new java.sql.Date(utilDate.getTime());

            
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    
}
