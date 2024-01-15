package com.js.exchange.rates.service.mapper;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ExchangeRate {
    private int id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal rate;
    private Date date;
    private Date dateInserted;
    private Date dateModified;

}
