package com.model;

import java.math.BigDecimal;

public class ListChart {
    String month ;
    BigDecimal income ;

    public ListChart(String month, BigDecimal income) {
        this.month = month;
        this.income = income;
    }
    
    // Getter - Setter

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
    
}
