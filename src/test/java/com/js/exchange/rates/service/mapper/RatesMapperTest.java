package com.js.exchange.rates.service.mapper;

import com.js.exchange.rates.service.config.DBConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringJUnitConfig(classes = {DBConfig.class})
class RatesMapperTest {
    @Autowired
    private RatesMapper ratesMapper;

    private ExchangeRate exchangeRate;

    @BeforeEach
    void createBeforeEachTest() {
        this.exchangeRate = createFakeExchangeRate();
        ratesMapper.insert(exchangeRate);
    }

    @AfterEach
    void deleteAfterEachTest() {
        ratesMapper.deleteV2(this.exchangeRate.getId());
        //ratesMapper.delete(this.exchangeRate);
    }

    @Test
    void testFetchExchangeRates() {
        //Given

        //When
        List<ExchangeRate> exchangeRates = ratesMapper.fetchExchangeRates();

        //Then
        Assertions.assertEquals(1, exchangeRates.size());
        //Assertions.assertTrue(currencies.stream().anyMatch(currency -> "EUR".equals(currency.getCurrency())));
    }

    @Test
    void testUpdateExchangeRate() {
        //Given
        this.exchangeRate.setRate(new BigDecimal("2.555"));

        //When
        ratesMapper.update(this.exchangeRate);
        ExchangeRate exchangeRateUpdated = ratesMapper.fetchExchangeRatesById(this.exchangeRate.getId());

        //Then
        Assertions.assertEquals(exchangeRate, exchangeRateUpdated);
    }

    private ExchangeRate createFakeExchangeRate() {
        return ExchangeRate.builder()
                .id(1)
                .rate(new BigDecimal("1.23"))
                .currencyFrom("USD")
                .currencyTo("PLN")
                .date(new Date())
                .dateInserted(new Date())
                .dateModified(new Date())
                .build();
    }
}