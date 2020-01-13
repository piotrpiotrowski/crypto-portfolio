package com.crypto.portfolio.domain;

import java.math.BigDecimal;
import java.util.List;

import com.crypto.portfolio.domain.GenerateCryptoCurrenciesAssetsReportUseCase.ReportInput;
import com.crypto.portfolio.domain.asset.CryptoCurrencyAsset;
import com.crypto.portfolio.domain.currency.CryptoCurrency;
import com.crypto.portfolio.domain.currency.Currency;
import com.crypto.portfolio.domain.report.AssetsReport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenerateCryptoCurrenciesAssetsReportUseCaseTest {

    CryptoCurrencyExchangeRepository cryptoCurrencyExchangeRepository = mock(CryptoCurrencyExchangeRepository.class);

    GenerateCryptoCurrenciesAssetsReportUseCase reportUseCase = new GenerateCryptoCurrenciesAssetsReportUseCase(cryptoCurrencyExchangeRepository);

    @Test
    public void shouldGenerateReportInEuro() {
        //given
        List<CryptoCurrencyAsset> cryptoCurrencyAssets = List.of(
                new CryptoCurrencyAsset(CryptoCurrency.BTC, BigDecimal.valueOf(2)),
                new CryptoCurrencyAsset(CryptoCurrency.ETH, BigDecimal.valueOf(5))
        );
        ReportInput input = new ReportInput(cryptoCurrencyAssets, Currency.EUR);

        //and
        when(cryptoCurrencyExchangeRepository.retrieveFor(CryptoCurrency.BTC, Currency.EUR)).thenReturn(new CryptoCurrencyExchange(CryptoCurrency.BTC,
                Currency.EUR, BigDecimal.valueOf(50)));
        when(cryptoCurrencyExchangeRepository.retrieveFor(CryptoCurrency.ETH, Currency.EUR)).thenReturn(new CryptoCurrencyExchange(CryptoCurrency.ETH,
                Currency.EUR, BigDecimal.valueOf(10)));
        //when
        AssetsReport assetsReport = reportUseCase.execute(input);

        //then
        assertEquals(BigDecimal.valueOf(150), assetsReport.getTotalValue());
        assertEquals(BigDecimal.valueOf(100), assetsReport.getAssetsValues().get("BTC"));
        assertEquals(BigDecimal.valueOf(50), assetsReport.getAssetsValues().get("ETH"));
    }
}