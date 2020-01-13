package com.crypto.portfolio.domain.asset;

import java.math.BigDecimal;
import java.math.MathContext;

import com.crypto.portfolio.domain.currency.CryptoCurrency;

public class CryptoCurrencyAsset {

    private static final MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    private final CryptoCurrency cryptoCurrency;

    private final BigDecimal quantity;

    public CryptoCurrencyAsset(CryptoCurrency cryptoCurrency, BigDecimal quantity) {
        this.cryptoCurrency = cryptoCurrency;
        this.quantity = quantity;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal calculateValue(BigDecimal unitValue) {
        return unitValue.multiply(quantity, MATH_CONTEXT);
    }

}
