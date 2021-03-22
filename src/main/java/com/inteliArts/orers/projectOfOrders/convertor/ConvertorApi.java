package com.inteliArts.orers.projectOfOrders.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inteliArts.orers.projectOfOrders.Exchange.ExchangeApiResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
public class ConvertorApi {

    final ExchangeApiResponse response;
    private static final String url_str = "https://api.exchangerate.host/latest?base=";
    private static final ObjectMapper mapper = new ObjectMapper();

    public ConvertorApi(ExchangeApiResponse response) {
        this.response = response;
    }

    public  Map<String, Double> getRates(String currency) throws IOException {
        URL url = new URL(url_str + currency);
        ExchangeApiResponse response = mapper.readValue(url, ExchangeApiResponse.class);
        Map<String, Double> rates = response.getRates();
        return rates;
    }
}
