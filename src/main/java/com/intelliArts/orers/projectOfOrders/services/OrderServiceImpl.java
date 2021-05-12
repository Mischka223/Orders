package com.intelliArts.orers.projectOfOrders.services;

import com.intelliArts.orers.projectOfOrders.convertor.ConvertorApi;
import com.intelliArts.orers.projectOfOrders.dao.Dao;
import com.intelliArts.orers.projectOfOrders.model.TotalResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    public final ConvertorApi convertorApi;

    public final Dao dao;

    public OrderServiceImpl(ConvertorApi convertorApi, Dao dao) {
        this.convertorApi = convertorApi;
        this.dao = dao;
    }



    @Override
    public TotalResponse total(String base) throws IOException {
        Map<String, Double> map = convertorApi.getRates(base);
        double sum = dao.getList().stream()
                .mapToDouble(order -> order.getAmount() / map.get(order.getCurrency()))
                .sum();
        return new TotalResponse(sum, base);
    }
}

