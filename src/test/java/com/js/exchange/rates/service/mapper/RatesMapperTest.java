package com.js.exchange.rates.service.mapper;

import com.js.exchange.rates.service.config.DBConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = {DBConfig.class})
class RatesMapperTest {
    @Autowired
    private RatesMapper ratesMapper;

    @Test
    void testFetchRates() {
       //Given

        //When
        List<Currency> currencies = ratesMapper.fetchCurrencies();

        //Then
        Assertions.assertEquals(3, currencies.size());
    }
}