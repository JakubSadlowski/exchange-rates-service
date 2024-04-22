package com.js.exchange.rates.service.mapper;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

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

    /**
     * Adjusted equal to make compatible with Data Base decimal precision.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ExchangeRate other)) {
            return false;
        } else {
            if (this.getId() != other.getId()) {
                return false;
            } else if (!Objects.equals(this.getCurrencyFrom(), other.getCurrencyFrom())) {
                return false;
            } else if (!Objects.equals(this.getCurrencyTo(), other.getCurrencyTo())) {
                return false;
            } else if (!equalsBigDecimals(this.getRate(), other.getRate())) {
                return false;
            } else return equalsDates(this.getDate(), other.getDate());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyFrom, currencyTo, rate, date);
    }

    private static boolean equalsBigDecimals(BigDecimal a, BigDecimal b) {
        return (Objects.equals(a, b)) || (a != null && a.compareTo(b) == 0);
    }

    private static boolean equalsDates(Date a, Date b) {
        if (a == b)
            return true;
        LocalDate localA = a.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localB = b.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Objects.equals(localA, localB);
    }
}
