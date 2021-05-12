package com.intelliArts.orers.projectOfOrders.controllers;

import com.intelliArts.orers.projectOfOrders.dao.Dao;
import com.intelliArts.orers.projectOfOrders.model.Order;
import com.intelliArts.orers.projectOfOrders.model.TotalResponse;
import com.intelliArts.orers.projectOfOrders.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    final OrderService orderService;
    final Dao dao;

    public OrderController(OrderService orderService, Dao dao) {
        this.orderService = orderService;
        this.dao = dao;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/expenses")
    public Map<String, List<Order>> listOrder() {
        return dao.getList().stream()
                .collect(Collectors.groupingBy(order1 -> order1.getDate().format(DATE_TIME_FORMATTER),
                        TreeMap::new,
                        Collectors.toList()));
    }

    @PostMapping("/expenses")
    public Order createOrder(@RequestBody @Valid Order order) {
        dao.addOrder(order);
        return order;
    }

    @DeleteMapping("/expenses")
    public List<Order> removeOrder(@RequestParam(value = "date") String date) {
        return dao.removeOrder(LocalDate.parse(date, DATE_TIME_FORMATTER));
    }

    @GetMapping("/total")
    public TotalResponse getRates(@RequestParam(value = "base") String base) throws IOException {
        return orderService.total(base);
    }
}
