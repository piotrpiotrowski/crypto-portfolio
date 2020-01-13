package com.crypto.portfolio.domain.asset;

import java.math.BigDecimal;

import com.crypto.portfolio.domain.currency.CryptoCurrency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoCurrencyAssetTest {

    @Test
    public void shouldCalculateAssetValue() {
        //given
        CryptoCurrencyAsset cryptoCurrencyAsset = new CryptoCurrencyAsset(CryptoCurrency.BTC, BigDecimal.TEN);

        //when
        BigDecimal value = cryptoCurrencyAsset.calculateValue(new BigDecimal("1.5"));

        //then
        assertEquals(new BigDecimal("15.0"), value);
    }
}