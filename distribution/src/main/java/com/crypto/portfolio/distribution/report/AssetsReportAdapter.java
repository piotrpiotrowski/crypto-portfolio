package com.crypto.portfolio.distribution.report;

import java.util.stream.Collectors;

import com.crypto.portfolio.domain.report.AssetsReport;

public class AssetsReportAdapter {

    public String adapt(AssetsReport assetsReport) {
        return "Assets report\n"
                + convertAssetsValuesToString(assetsReport) + '\n'
                + '\n'
                + "Total portfolio value: " + assetsReport.getTotalValue() + ' ' + assetsReport.getReportCurrency();

    }

    private String convertAssetsValuesToString(AssetsReport assetsReport) {
        return assetsReport.getAssetsValues()
                           .entrySet()
                           .stream()
                           .map(entry -> entry.getKey() + ' ' + entry.getValue() + ' ' + assetsReport.getReportCurrency())
                           .collect(Collectors.joining("\n"));
    }
}
