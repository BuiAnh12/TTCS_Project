
package com.control.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonValidation {
    public static String strip(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("^\\s+|\\s+$", "");
    }
    
    public static boolean isNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false); // Set lenient to false to enforce strict date parsing
        try {
            // Attempt to parse the date
            Date parsedDate = dateFormat.parse(dateString);
            // If parsing succeeds, return true
            return true;
        } catch (ParseException ex) {
            // If parsing fails, return false
            return false;
        }
    }
}
