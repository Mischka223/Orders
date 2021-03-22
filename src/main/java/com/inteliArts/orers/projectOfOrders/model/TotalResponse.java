package com.inteliArts.orers.projectOfOrders.model;

public class TotalResponse {
    private double total;
    private String currency;

    public TotalResponse(double total, String currency) {
        this.total = total;
        this.currency = currency;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
