package com.js.exchange.rates.service.mapper;

import com.js.exchange.rates.service.config.DBConfig;
import com.js.exchange.rates.service.config.DBConfigTestContainer;
import com.js.exchange.rates.service.config.MyBatisConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@ActiveProfiles("dev")
@SpringJUnitConfig(classes = {DBConfig.class, DBConfigTestContainer.class, MyBatisConfig.class})
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
        Assertions.assertTrue(currencies.stream().anyMatch(currency -> "EUR".equals(currency.getValue())));
    }
}