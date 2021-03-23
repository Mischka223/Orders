package com.intelliArts.orers.projectOfOrders.dao;

import com.intelliArts.orers.projectOfOrders.convertor.ConvertorApi;
import com.intelliArts.orers.projectOfOrders.model.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DaoImplTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    public DaoImpl dao;
    Order order = new Order();
    static LocalDate localDate = LocalDate.now();
    Order order1 = new Order(localDate,3,"UAH","Jogurt");

    @org.junit.Test
    public void getList() {
        dao.addOrder(order);
        dao.addOrder(order1);
        List<Order> list = dao.getList();
        List<Order> orderListExpected = dao.list.stream().filter(Objects::nonNull).collect(Collectors.toList());
        assertEquals(list,orderListExpected);
    }

    @org.junit.Test
    public void addOrder() {
        int expected = 0;
        int size = dao.getList().size();
        assertEquals(expected,size);
        dao.addOrder(order);
        expected++;
        int size1 = dao.getList().size();
        assertEquals(expected,size1);
        dao.addOrder(order1);
        expected++;
        int size2 = dao.getList().size();
        assertEquals(size2,expected);
    }

    @org.junit.Test
    public void removeOrder() {
       dao.addOrder(order1);
       dao.addOrder(order1);
       dao.addOrder(order1);
        int size = dao.getList().size();
        int expected = 3;
        assertEquals(expected,size);
        dao.removeOrder(localDate);
        expected -= 3;
        int size1 = dao.getList().size();
        assertEquals(expected,size1);
    }

    @org.junit.Test
    public void total() throws IOException {
        ConvertorApi convertorApi = new ConvertorApi();
        Map<String, Double> eur = convertorApi.getRates("EUR");
        double uah = eur.get("UAH");
        double v = order1.getAmount() / uah;
        dao.addOrder(order1);
        double expected = dao.total("EUR").getTotal();
        assertEquals(v,expected,v);
    }
}