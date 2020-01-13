package com.crypto.portfolio.distribution.report;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.crypto.portfolio.distribution.exception.ApplicationException;
import com.crypto.portfolio.domain.asset.CryptoCurrencyAsset;
import com.crypto.portfolio.domain.currency.CryptoCurrency;

public class CryptoCurrencyAssetAdapter {

    public List<CryptoCurrencyAsset> adapt(String rawListOfAssets) {
        return rawListOfAssets.lines()
                              .map(this::adaptLine)
                              .collect(Collectors.toList());
    }

    private CryptoCurrencyAsset adaptLine(String line) {
        String[] parts = line.split("=");
        if (parts.length != 2) {
            throw new ApplicationException("Cannot parse line [" + line + "]expected format CURRENCY=quantity");
        }
        return new CryptoCurrencyAsset(convertToCryptoCurrency(parts[0]), convertToBigDecimal(parts[1]));
    }

    private BigDecimal convertToBigDecimal(String quantity) {
        try {
            return new BigDecimal(quantity);
        } catch (NumberFormatException e) {
            throw new ApplicationException(quantity + " is not a valid number", e);
        }
    }

    private CryptoCurrency convertToCryptoCurrency(String currency) {
        try {
            return CryptoCurrency.valueOf(currency);
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Unsupported crypto currency: " + currency, e);
        }

    }
}
