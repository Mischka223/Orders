package com.intelliArts.orers.projectOfOrders.service;

import com.intelliArts.orers.projectOfOrders.convertor.ConvertorApi;
import com.intelliArts.orers.projectOfOrders.dao.Dao;
import com.intelliArts.orers.projectOfOrders.model.Order;
import com.intelliArts.orers.projectOfOrders.services.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    public  OrderService orderService;

    @MockBean
    public Dao dao;

    static LocalDate localDate = LocalDate.now();
    Order order1 = new Order(localDate,3,"UAH","Jogurt");

    @Test
    public void total() throws IOException {
        ConvertorApi convertorApi = new ConvertorApi();
        Map<String, Double> eur = convertorApi.getRates("EUR");
        double uah = eur.get("UAH");
        double v = order1.getAmount() / uah;
        dao.addOrder(order1);
        double expected = orderService.total("EUR").getTotal();
        assertEquals(v,expected,v);
    }
}
