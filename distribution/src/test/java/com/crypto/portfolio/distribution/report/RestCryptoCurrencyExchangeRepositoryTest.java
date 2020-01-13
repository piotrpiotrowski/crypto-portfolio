package com.crypto.portfolio.distribution.report;

import java.math.BigDecimal;
import java.net.http.HttpClient;

import com.crypto.portfolio.distribution.exception.ApplicationException;
import com.crypto.portfolio.domain.CryptoCurrencyExchange;
import com.crypto.portfolio.domain.currency.CryptoCurrency;
import com.crypto.portfolio.domain.currency.Currency;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestCryptoCurrencyExchangeRepositoryTest {

    static WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(28080));

    RestCryptoCurrencyExchangeRepository repository = new RestCryptoCurrencyExchangeRepository(HttpClient.newHttpClient(), "http://localhost:28080");

    @BeforeAll
    public static void setup() {
        wireMockServer.start();
        configureFor("localhost", 28080);
    }

    @Test
    public void shouldBuildReportForSingleAssetWithTotalValueEqualToValueOfSingleAsset() {
        //given
        stubFor(get(urlPathEqualTo("/data/price"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{\"EUR\":10}")));

        //when
        CryptoCurrencyExchange cryptoCurrencyExchange = repository.retrieveFor(CryptoCurrency.BTC, Currency.EUR);

        //then
        assertEquals(CryptoCurrency.BTC, cryptoCurrencyExchange.getCryptoCurrency());
        assertEquals(Currency.EUR, cryptoCurrencyExchange.getCurrency());
        assertEquals(BigDecimal.TEN, cryptoCurrencyExchange.getUnitRate());
    }

    @Test
    public void shouldThrowApplicationExceptionWhenResponseStatusIsNot200() {
        //expect
        ApplicationException applicationException = assertThrows(ApplicationException.class, () -> repository.retrieveFor(CryptoCurrency.ETH, Currency.EUR));
        assertEquals("Cannot get exchange rate in EUR for ETH. Unexpected Status code 404", applicationException.getMessage());
    }

}