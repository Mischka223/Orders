package com.intelliArts.orers.projectOfOrders.dao;

import com.intelliArts.orers.projectOfOrders.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface Dao {
    List<Order> getList();

    Order addOrder(Order order);

    List<Order> removeOrder(LocalDate date);

}
