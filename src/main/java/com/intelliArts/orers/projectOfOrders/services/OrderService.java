package com.intelliArts.orers.projectOfOrders.services;

import com.intelliArts.orers.projectOfOrders.model.TotalResponse;

import java.io.IOException;

public interface OrderService {
    public TotalResponse total(String base) throws IOException;
}
