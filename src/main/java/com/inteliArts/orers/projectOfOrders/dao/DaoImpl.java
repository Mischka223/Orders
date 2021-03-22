package com.inteliArts.orers.projectOfOrders.dao;

import com.inteliArts.orers.projectOfOrders.Comparator.CompareByDate;
import com.inteliArts.orers.projectOfOrders.convertor.ConvertorApi;
import com.inteliArts.orers.projectOfOrders.model.Order;
import com.inteliArts.orers.projectOfOrders.model.TotalResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DaoImpl implements Dao {
    private static long idCursor = 0;
    public final ConvertorApi convertorApi;
    private final Order order;

    List<Order> list = new LinkedList<>();

    public DaoImpl(Order order, ConvertorApi convertorApi) {
        this.order = order;
        this.convertorApi = convertorApi;
    }

    public List<Order> getList() {
        Collections.sort(list, new CompareByDate());
        return list;
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

    public TotalResponse total(String base) throws IOException {
        Map<String, Double> map = convertorApi.getRates(base);
        double sum = list.stream()
                .mapToDouble(order -> order.getAmount() / map.get(order.getCurrency()))
                .sum();

        return new TotalResponse(sum,base);
    }
}
