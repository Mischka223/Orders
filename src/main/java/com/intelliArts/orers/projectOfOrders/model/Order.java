package com.intelliArts.orers.projectOfOrders.model;


import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Component
public class Order {
    private Long id;
    @NotNull(message = "the date should not be empty")
    @PastOrPresent(message = "the date should not be in future")
    private LocalDate date;
    @PositiveOrZero(message = "amount should be greatest than 0")
    private double amount;
    @NotEmpty(message = "currency should not be empty")
    private String currency;
    @NotEmpty(message = "name of product should not be empty")
    private String product;

    public Order() {
    }

    public Order(LocalDate date, double amount, String currency, String product) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Order{" +
                "Id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", product='" + product + '\'' +
                '}';
    }
}
