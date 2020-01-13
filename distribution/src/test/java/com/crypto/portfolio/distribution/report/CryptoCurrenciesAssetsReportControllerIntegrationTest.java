package com.crypto.portfolio.distribution.report;

import com.crypto.portfolio.distribution.ApplicationConfiguration;
import com.crypto.portfolio.domain.currency.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CryptoCurrenciesAssetsReportControllerIntegrationTest {

    @Test
    public void shouldAdaptAssertReportToString() {
        //given
        CryptoCurrenciesAssetsReportController controller = new ApplicationConfiguration().cryptoCurrenciesAssetsReportController();
        String rawListOfAssets = "BTC=10\n" +
                "ETH=5\n" +
                "XRP=2000";

        //when
        String reportAsString = controller.generateReport(rawListOfAssets, Currency.EUR);

        //then
        Assertions.assertTrue(reportAsString.contains("BTC"));
        Assertions.assertTrue(reportAsString.contains("ETH"));
        Assertions.assertTrue(reportAsString.contains("XRP"));
    }
}