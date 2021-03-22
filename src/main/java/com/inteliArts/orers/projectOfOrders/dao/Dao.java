package com.inteliArts.orers.projectOfOrders.dao;

import com.inteliArts.orers.projectOfOrders.model.Order;
import com.inteliArts.orers.projectOfOrders.model.TotalResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface Dao {
    List<Order> getList();

    Order addOrder(Order order);

    List<Order> removeOrder(LocalDate date);

    TotalResponse total(String rates) throws IOException;
}
