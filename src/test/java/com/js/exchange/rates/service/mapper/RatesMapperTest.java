package com.js.exchange.rates.service.mapper;

import com.js.exchange.rates.service.config.DBConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringJUnitConfig(classes = {DBConfig.class})
class RatesMapperTest {
    @Autowired
    private RatesMapper ratesMapper;
    @Test
    void testFetchRates(){
        int a = 1;
        a++;
    }
}