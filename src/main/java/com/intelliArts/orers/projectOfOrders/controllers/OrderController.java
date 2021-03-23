package com.intelliArts.orers.projectOfOrders.controllers;

import com.intelliArts.orers.projectOfOrders.dao.Dao;
import com.intelliArts.orers.projectOfOrders.model.Order;
import com.intelliArts.orers.projectOfOrders.model.TotalResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final Dao dao;

    public OrderController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping("/expenses")
    public Map<String,List<Order>> listOrder() {
        return dao.getList().stream()
                .collect(Collectors.groupingBy(order1 -> order1.getDate().format(DATE_TIME_FORMATTER),
                        TreeMap::new,
                        Collectors.toList()));
    }

    @PostMapping("/expenses")
    public Order createOrder(@RequestBody Order order) {
        dao.addOrder(order);
        return order;
    }

    @DeleteMapping("/expenses")
    public List<Order> removeOrder(@RequestParam(value = "date") String date) {
        return dao.removeOrder(LocalDate.parse(date, DATE_TIME_FORMATTER));
    }

    @GetMapping("/total")
    public TotalResponse getRates(@RequestParam(value = "base") String base) throws IOException {
        return dao.total(base);
    }
}
