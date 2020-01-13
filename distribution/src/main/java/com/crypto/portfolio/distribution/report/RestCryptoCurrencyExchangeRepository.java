package com.crypto.portfolio.distribution.report;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.crypto.portfolio.distribution.exception.ApplicationException;
import com.crypto.portfolio.domain.CryptoCurrencyExchange;
import com.crypto.portfolio.domain.CryptoCurrencyExchangeRepository;
import com.crypto.portfolio.domain.currency.CryptoCurrency;
import com.crypto.portfolio.domain.currency.Currency;

public class RestCryptoCurrencyExchangeRepository implements CryptoCurrencyExchangeRepository {

    private final HttpClient httpClient;

    private final String baseUri;

    public RestCryptoCurrencyExchangeRepository(HttpClient httpClient, String baseUri) {
        this.httpClient = httpClient;
        this.baseUri = baseUri;
    }

    @Override
    public CryptoCurrencyExchange retrieveFor(CryptoCurrency cryptoCurrency, Currency currency) {
        URI uri = URI.create(baseUri + "/data/price?fsym=" + cryptoCurrency + "&tsyms=" + currency);
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(uri)
                                         .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApplicationException("Cannot get exchange rate in " + currency + " for " + cryptoCurrency + ". Unexpected Status code " + response.statusCode());
            }
            return new CryptoCurrencyExchange(cryptoCurrency, currency, convertToBigDecimal(response.body()));
        } catch (IOException | InterruptedException e) {
            throw new ApplicationException("Cannot get exchange rate in " + currency + " for " + cryptoCurrency, e);
        }
    }

    private BigDecimal convertToBigDecimal(String body) {
        String numberAsString = body.substring(7, body.length() - 1);
        return parseBigDecimal(numberAsString);
    }

    private BigDecimal parseBigDecimal(String numberAsString) {
        try {
            return new BigDecimal(numberAsString);
        } catch (NumberFormatException e) {
            throw new ApplicationException("Cannot parse exchange rate", e);
        }
    }
}
