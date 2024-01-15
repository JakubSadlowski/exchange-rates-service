package com.js.exchange.rates.service.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface RatesMapper {
    @Select("""
            select c_currency from exchange_rates.dbo.currencies;
            """)

    @Results(value = {
            @Result(property = "currency", column = "c_currency", javaType = String.class),
    })
    List<Currency> fetchCurrencies();

    @Select("""
            select [er_id], [er_currency_from], [er_currency_to], [er_rate], [er_date], [er_date_inserted], [er_date_modified] 
            from exchange_rates.dbo.rates;
            """)

    @Results(value = {
            @Result(property = "id", column = "er_id", javaType = Integer.class),
            @Result(property = "currencyFrom", column = "er_currency_from", javaType = String.class),
            @Result(property = "currencyTo", column = "er_currency_to", javaType = String.class),
            @Result(property = "rate", column = "er_rate", javaType = BigDecimal.class),
            @Result(property = "date", column = "er_date", javaType = Date.class),
            @Result(property = "dateInserted", column = "er_date_inserted", javaType = Date.class),
            @Result(property = "dateModified", column = "er_date_modified", javaType = Date.class),
    })
    List<ExchangeRate> fetchExchangeRates();
}
