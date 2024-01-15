package com.js.exchange.rates.service.mapper;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExchangeRate {
    private int id;
    private BigDecimal rate;
    private String currencyFrom;
    private String currencyTo;
    private Date date;
    private Date dateInserted;
    private Date dateModified;

}
