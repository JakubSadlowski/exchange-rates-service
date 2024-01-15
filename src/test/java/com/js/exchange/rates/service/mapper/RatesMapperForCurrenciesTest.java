package com.js.exchange.rates.service.mapper;

import com.js.exchange.rates.service.config.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = {DBConfig.class})
class RatesMapperForCurrenciesTest {
    @Autowired
    private RatesMapper ratesMapper;

    @Test
    void testFetchCurrencies() {
        //Given

        //When
        List<Currency> currencies = ratesMapper.fetchCurrencies();

        //Then
        Assertions.assertEquals(3, currencies.size());
        Assertions.assertTrue(currencies.stream().anyMatch(currency -> "EUR".equals(currency.getCurrency())));
    }
}