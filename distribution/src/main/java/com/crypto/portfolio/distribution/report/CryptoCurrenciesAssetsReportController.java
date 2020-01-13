package com.crypto.portfolio.distribution.report;

import java.util.List;

import com.crypto.portfolio.domain.GenerateCryptoCurrenciesAssetsReportUseCase;
import com.crypto.portfolio.domain.GenerateCryptoCurrenciesAssetsReportUseCase.ReportInput;
import com.crypto.portfolio.domain.asset.CryptoCurrencyAsset;
import com.crypto.portfolio.domain.currency.Currency;
import com.crypto.portfolio.domain.report.AssetsReport;

public class CryptoCurrenciesAssetsReportController {

    private final GenerateCryptoCurrenciesAssetsReportUseCase generateCryptoCurrenciesAssetsReportUseCase;

    private final CryptoCurrencyAssetAdapter cryptoCurrencyAssetAdapter;

    private final AssetsReportAdapter assetsReportAdapter;

    public CryptoCurrenciesAssetsReportController(GenerateCryptoCurrenciesAssetsReportUseCase generateCryptoCurrenciesAssetsReportUseCase,
                                                  CryptoCurrencyAssetAdapter cryptoCurrencyAssetAdapter, AssetsReportAdapter assetsReportAdapter) {
        this.generateCryptoCurrenciesAssetsReportUseCase = generateCryptoCurrenciesAssetsReportUseCase;
        this.cryptoCurrencyAssetAdapter = cryptoCurrencyAssetAdapter;
        this.assetsReportAdapter = assetsReportAdapter;
    }

    public String generateReport(String rawListOfAssets, Currency reportCurrency) {
        List<CryptoCurrencyAsset> cryptoCurrencyAssets = cryptoCurrencyAssetAdapter.adapt(rawListOfAssets);
        AssetsReport assetsReport = generateCryptoCurrenciesAssetsReportUseCase.execute(new ReportInput(cryptoCurrencyAssets, reportCurrency));
        return assetsReportAdapter.adapt(assetsReport);
    }
}
