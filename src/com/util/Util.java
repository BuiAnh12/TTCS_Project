/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author AnTran
 */
public class Util {

    //Ràng buộc ngày tháng của sản phẩm
    public static boolean checkDate(Date manufactureDate, Date expiryDate, Date entry) {
        Date today = new Date();
        if (manufactureDate.after(entry)) {
            JOptionPane.showMessageDialog(null, "Ngày sản xuất phải trước ngày nhập", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (manufactureDate.after(entry)) {
            JOptionPane.showMessageDialog(null, "Ngày sản xuất phải trước ngày hết hạn", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (entry.after(expiryDate)) {
            JOptionPane.showMessageDialog(null, "Ngày nhập phải trước ngày hết hạn", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (manufactureDate.after(today)) {
            JOptionPane.showMessageDialog(null, "Ngày sản xuất không thể sau hôm này", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (entry.after(today)) {
            JOptionPane.showMessageDialog(null, "Ngày nhập không thể sau ngày hôm nay", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Lấy thời gian hiện tại
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
    }

    //Kiểm tra đầu vào của Product
    public static boolean validateImportInput(Date manufactureDate, Date expiryDate, Date entry, int quantity, int avaiQuantity, BigDecimal unitP) {
        if (manufactureDate == null || expiryDate == null || entry == null || String.valueOf(unitP).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Information is incomplete", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (unitP.intValue() <= 0 || quantity <= 0 || avaiQuantity <= 0) {
            JOptionPane.showMessageDialog(null, "Giá vốn và số lượng phải lớn hơn 0", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return !(!checkDate(manufactureDate, expiryDate, entry));
    }

    public static boolean validateProductInput(String name, String manufacturer, String desc, String category, BigDecimal sellPrice) {
        if (name.isEmpty() || manufacturer.isEmpty() || category.isEmpty() || desc.isEmpty() || String.valueOf(sellPrice).isEmpty()) {
            JOptionPane.showMessageDialog(null, "Information is incomplete", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (sellPrice.intValue() <= 0) {
            JOptionPane.showMessageDialog(null, "Giá bán phải lớn hơn 0", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Kiểm tra email có hợp lệ không
    public static boolean checkEmail(String email) {
        final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        final Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Kiểm tra đầu vào của Customer
    public static boolean validateCustomerInput(String name, String email, String address) {
        if (name.isEmpty() || address.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Information is incomplete", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
//        if (!isValidPhoneNumber(phone)) {
//            JOptionPane.showMessageDialog(null, "SĐT không hợp lệ", "Warning", JOptionPane.WARNING_MESSAGE);
//            return false;
//        }
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(null, "Email không khả dụng", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Kiểm tra đầu vào của Employee
    public static boolean validateStaffInput(String name, int age, String email, String address, String username, String pw) {
        if (name.isEmpty() || email.isEmpty() || address.isEmpty() || username.isEmpty() || String.valueOf(age).isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Information is incomplete", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!isValidUsername(username)) {
            JOptionPane.showMessageDialog(null, "Invalid username", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!checkEmail(email)) {
            JOptionPane.showMessageDialog(null, "Email không khả dụng", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Kiểm tra username hợp lệ (không có kí tự đặc biệt và dấu cách, chỉ bao gồm chữ và số, không có tiếng việt)
    public static boolean isValidUsername(String username) {
        // Chỉ cho phép các ký tự chữ và số
        String regex = "^[a-zA-Z0-9]+$";
        return Pattern.matches(regex, username);
    }

    //Format tên đúng định dạng. Ví dụ: "NGuyỄn Văn a" -> "Nguyễn Văn A"
    public static String formatName(String name) {
        // Chuyển tất cả các ký tự thành chữ thường
        name = name.toLowerCase();
        // Tách tên thành các phần riêng biệt 
        String[] nameParts = name.split(" ");
        // Khởi tạo một StringBuilder để tạo lại tên theo đúng định dạng
        StringBuilder formattedNameBuilder = new StringBuilder();
        for (String part : nameParts) {
            part = part.trim();
            if (!part.isEmpty()) {
                // Chuyển chữ cái đầu tiên của mỗi phần thành chữ hoa
                String firstLetter = part.substring(0, 1).toUpperCase();
                // Ghép lại phần đã được chuyển đổi vào StringBuilder
                formattedNameBuilder.append(firstLetter).append(part.substring(1)).append(" ");
            }
        }
        return formattedNameBuilder.toString().trim();
    }

    //Chuyển đổi giá từ số sang VND
//    public static String convertToVND(double number) {
//        @SuppressWarnings("deprecation")
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        return currencyFormat.format(number);
//    }
//
//    public static String vndConvertToNumber(String amount) throws ParseException {
//        amount = amount.replace("đ", "").replace(".", "").trim();
//        @SuppressWarnings("deprecation")
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        return String.valueOf(currencyFormat.parse(amount).intValue());
//    }

}
