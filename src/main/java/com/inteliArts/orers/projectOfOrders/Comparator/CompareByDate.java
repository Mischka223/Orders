package com.inteliArts.orers.projectOfOrders.Comparator;

import com.inteliArts.orers.projectOfOrders.model.Order;
import org.springframework.stereotype.Component;
import java.util.Comparator;

@Component
public class CompareByDate implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
