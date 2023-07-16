package com.service.moneyfllow.data;

import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateApiResponse {
    private String result;
    private String base_code;
    private Map<String, Double> conversion_rates;

}
