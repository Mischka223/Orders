package com.inteliArts.orers.projectOfOrders.controllers;

import com.inteliArts.orers.projectOfOrders.dao.Dao;
import com.inteliArts.orers.projectOfOrders.model.Order;
import com.inteliArts.orers.projectOfOrders.model.TotalResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class OrderController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    final Dao dao;

    public OrderController(Dao dao) {
        this.dao = dao;
    }

    @GetMapping("/expenses")
    public List<Order> listOrder() {
        return dao.getList();
    }

    @PostMapping("/expenses")
    public Order createOrder(@RequestBody Order order) {
        dao.addOrder(order);
        return order;
    }

    @DeleteMapping("/expenses")
    public List<Order> removeOrder(@RequestParam(value = "date") String date) {
        return dao.removeOrder(LocalDate.parse(date,DATE_TIME_FORMATTER));
    }

    @GetMapping("/total")
    public TotalResponse getRates(@RequestParam(value = "base") String base) throws IOException {
        return dao.total(base);
    }
}
