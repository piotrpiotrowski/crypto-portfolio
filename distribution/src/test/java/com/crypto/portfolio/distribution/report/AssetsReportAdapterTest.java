package com.crypto.portfolio.distribution.report;

import java.math.BigDecimal;

import com.crypto.portfolio.domain.currency.Currency;
import com.crypto.portfolio.domain.report.AssetsReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetsReportAdapterTest {

    AssetsReportAdapter adapter = new AssetsReportAdapter();

    String expectedReport = "Assets report\n"
            + "BTC 10 EUR\n"
            + "ETH 5 EUR\n"
            + '\n'
            + "Total portfolio value: 15 EUR";

    @Test
    public void shouldAdaptAssertReportToString() {
        //given
        AssetsReport assetsReport = AssetsReport.builder()
                                                .withReportCurrency(Currency.EUR)
                                                .addAssetValue("BTC", BigDecimal.TEN)
                                                .addAssetValue("ETH", BigDecimal.valueOf(5))
                                                .build();

        //when
        String reportAsString = adapter.adapt(assetsReport);

        //then
        assertEquals(expectedReport, reportAsString);
    }
}