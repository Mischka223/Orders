package com.intelliArts.orers.projectOfOrders.convertor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliArts.orers.projectOfOrders.Exchange.ExchangeApiResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
public class ConvertorApi {

    private static final String URL = "https://api.exchangerate.host/latest?base=";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public  Map<String, Double> getRates(String currency) throws IOException {
        URL url = new URL(URL + currency);
        ExchangeApiResponse response = MAPPER.readValue(url, ExchangeApiResponse.class);
        Map<String, Double> rates = response.getRates();
        return rates;
    }
}
