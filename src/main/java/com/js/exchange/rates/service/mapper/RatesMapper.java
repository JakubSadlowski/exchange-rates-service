package com.js.exchange.rates.service.mapper;

import org.apache.ibatis.annotations.*;

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


}
