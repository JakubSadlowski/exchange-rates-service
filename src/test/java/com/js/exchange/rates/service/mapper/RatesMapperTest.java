package com.js.exchange.rates.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatesMapperTest {
    @Mock
    private RatesMapper ratesMapperMock;

    private ExchangeRate exchangeRate;

    @BeforeEach
    void createBeforeEachTest() {
        this.exchangeRate = createFakeExchangeRateBuilder().build();
    }

    @Test
    void testFetchExchangeRates() {
        //Given
        when(ratesMapperMock.fetchExchangeRates()).thenReturn(List.of(this.exchangeRate));

        //When
        List<ExchangeRate> exchangeRates = ratesMapperMock.fetchExchangeRates();

        //Then
        Assertions.assertEquals(1, exchangeRates.size());
        verify(ratesMapperMock).fetchExchangeRates();
    }

    @Test
    void testUpdateExchangeRate() {
        //Given
        this.exchangeRate.setRate(new BigDecimal("2.555"));
        ExchangeRate mockedFetchedExchangeRate = createFakeExchangeRateBuilder().rate(new BigDecimal("2.555")).build();
        doNothing().when(this.ratesMapperMock).update(this.exchangeRate);
        when(this.ratesMapperMock.fetchExchangeRatesById(this.exchangeRate.getId())).thenReturn(mockedFetchedExchangeRate);

        //When
        ratesMapperMock.update(this.exchangeRate);
        ExchangeRate exchangeRateUpdated = ratesMapperMock.fetchExchangeRatesById(this.exchangeRate.getId());

        //Then
        Assertions.assertEquals(exchangeRate, exchangeRateUpdated);
    }

    private ExchangeRate.ExchangeRateBuilder createFakeExchangeRateBuilder() {
        return ExchangeRate.builder()
                .id(1)
                .rate(new BigDecimal("1.23"))
                .currencyFrom("USD")
                .currencyTo("PLN")
                .date(new Date())
                .dateInserted(new Date())
                .dateModified(new Date());
    }
}