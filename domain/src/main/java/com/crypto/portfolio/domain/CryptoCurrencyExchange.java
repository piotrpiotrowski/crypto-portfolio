package com.crypto.portfolio.domain;

import java.math.BigDecimal;

import com.crypto.portfolio.domain.currency.CryptoCurrency;
import com.crypto.portfolio.domain.currency.Currency;

public class CryptoCurrencyExchange {

    private final CryptoCurrency cryptoCurrency;

    private final Currency currency;

    private final BigDecimal unitRate;

    public CryptoCurrencyExchange(CryptoCurrency cryptoCurrency, Currency currency, BigDecimal unitRate) {
        this.cryptoCurrency = cryptoCurrency;
        this.currency = currency;
        this.unitRate = unitRate;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }
}
