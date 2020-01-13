package com.crypto.portfolio.domain;

import com.crypto.portfolio.domain.currency.CryptoCurrency;
import com.crypto.portfolio.domain.currency.Currency;

public interface CryptoCurrencyExchangeRepository {

    CryptoCurrencyExchange retrieveFor(CryptoCurrency cryptoCurrency, Currency currency);
}
