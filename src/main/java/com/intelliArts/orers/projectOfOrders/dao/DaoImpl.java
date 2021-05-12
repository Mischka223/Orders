package com.intelliArts.orers.projectOfOrders.dao;

import com.intelliArts.orers.projectOfOrders.convertor.ConvertorApi;
import com.intelliArts.orers.projectOfOrders.model.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DaoImpl implements Dao {
    private static long idCursor = 0;
    public final ConvertorApi convertorApi;

    List<Order> list = new LinkedList<>();

    public DaoImpl(ConvertorApi convertorApi) {
        this.convertorApi = convertorApi;
    }

    public List<Order> getList() {
        return list.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Order addOrder(Order order) {
        order.setId(idCursor++);
        list.add(order);
        return order;
    }

    public List<Order> removeOrder(LocalDate date) {
        List<Order> toRemove = list.stream()
                .filter(order1 -> order1.getDate().equals(date))
                .collect(Collectors.toList());
        list.removeAll(toRemove);
        return toRemove;
    }
}
