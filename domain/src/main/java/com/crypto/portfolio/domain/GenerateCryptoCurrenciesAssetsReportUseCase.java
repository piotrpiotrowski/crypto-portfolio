package com.crypto.portfolio.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collector;

import com.crypto.portfolio.domain.asset.CryptoCurrencyAsset;
import com.crypto.portfolio.domain.currency.Currency;
import com.crypto.portfolio.domain.report.AssetsReport;

public class GenerateCryptoCurrenciesAssetsReportUseCase {

    private final CryptoCurrencyExchangeRepository cryptoCurrencyExchangeRepository;

    public GenerateCryptoCurrenciesAssetsReportUseCase(CryptoCurrencyExchangeRepository cryptoCurrencyExchangeRepository) {
        this.cryptoCurrencyExchangeRepository = cryptoCurrencyExchangeRepository;
    }

    public AssetsReport execute(ReportInput input) {
        return input.getAssets().stream()
                    .map(cryptoCurrencyAsset -> calculateAssetValue(cryptoCurrencyAsset, input.getReportCurrency()))
                    .collect(generateReport(input.getReportCurrency()));
    }

    private Collector<AssetValue, AssetsReport.AssetsReportBuilder, AssetsReport> generateReport(Currency currency) {
        return Collector.of(() -> AssetsReport.builder().withReportCurrency(currency),
                this::addAssetValue,
                (builder1, builder2) -> builder2,
                AssetsReport.AssetsReportBuilder::build
        );
    }

    private AssetsReport.AssetsReportBuilder addAssetValue(AssetsReport.AssetsReportBuilder reportBuilder, AssetValue assetValue) {
        return reportBuilder.addAssetValue(assetValue.getAssetName(), assetValue.getValue());
    }

    private AssetValue calculateAssetValue(CryptoCurrencyAsset cryptoCurrencyAsset, Currency reportCurrency) {
        CryptoCurrencyExchange cryptoCurrencyExchange = cryptoCurrencyExchangeRepository.retrieveFor(cryptoCurrencyAsset.getCryptoCurrency(), reportCurrency);
        BigDecimal value = cryptoCurrencyAsset.calculateValue(cryptoCurrencyExchange.getUnitRate());
        return new AssetValue(cryptoCurrencyAsset.getCryptoCurrency().name(), value);
    }

    private static class AssetValue {

        private final String assetName;

        private final BigDecimal value;

        AssetValue(String assetName, BigDecimal value) {
            this.assetName = assetName;
            this.value = value;
        }

        public String getAssetName() {
            return assetName;
        }

        public BigDecimal getValue() {
            return value;
        }
    }

    public static class ReportInput {

        private final List<CryptoCurrencyAsset> assets;

        private final Currency reportCurrency;

        public ReportInput(List<CryptoCurrencyAsset> assets, Currency reportCurrency) {
            this.assets = assets;
            this.reportCurrency = reportCurrency;
        }

        public List<CryptoCurrencyAsset> getAssets() {
            return assets;
        }

        public Currency getReportCurrency() {
            return reportCurrency;
        }
    }
}
