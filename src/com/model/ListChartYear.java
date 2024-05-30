package com.model;

import java.math.BigDecimal;

public class ListChartYear {
    int year ;
    BigDecimal income ;

    public ListChartYear(int year, BigDecimal income) {
        this.year = year;
        this.income = income;
    }
    
    // Getter - Setter
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

   

    
}
