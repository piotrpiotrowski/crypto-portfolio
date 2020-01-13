package com.crypto.portfolio.domain.report;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.crypto.portfolio.domain.currency.Currency;

public class AssetsReport {

    private final Map<String, BigDecimal> assetsValues;

    private final BigDecimal totalValue;

    private final Currency reportCurrency;

    public AssetsReport(AssetsReportBuilder builder) {
        this.assetsValues = builder.assetsValues;
        this.totalValue = builder.totalValue;
        reportCurrency = builder.reportCurrency;
    }

    public static AssetsReportBuilder builder() {
        return new AssetsReportBuilder();
    }

    public Map<String, BigDecimal> getAssetsValues() {
        return Collections.unmodifiableMap(assetsValues);
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public Currency getReportCurrency() {
        return reportCurrency;
    }

    public static class AssetsReportBuilder {

        private final Map<String, BigDecimal> assetsValues = new HashMap<>();

        public Currency reportCurrency;

        private BigDecimal totalValue = BigDecimal.ZERO;

        public AssetsReportBuilder addAssetValue(String assetName, BigDecimal assetValue) {
            assetsValues.compute(assetName, (key, currentValue) -> updateCurrentValue(currentValue, assetValue));
            totalValue = calculateTotalValue();
            return this;
        }

        public AssetsReportBuilder withReportCurrency(Currency reportCurrency) {
            this.reportCurrency = reportCurrency;
            return this;
        }

        private BigDecimal calculateTotalValue() {
            return assetsValues.values()
                               .stream()
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        private BigDecimal updateCurrentValue(BigDecimal currentValue, BigDecimal assetValue) {
            return Optional.ofNullable(currentValue)
                           .map(value -> value.add(assetValue))
                           .orElse(assetValue);
        }

        public AssetsReport build() {
            return new AssetsReport(this);
        }
    }
}
