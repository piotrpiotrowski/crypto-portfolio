package com.crypto.portfolio.distribution;

import com.crypto.portfolio.distribution.file.FileReader;
import com.crypto.portfolio.distribution.report.CryptoCurrenciesAssetsReportController;
import com.crypto.portfolio.domain.currency.Currency;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("File name as a first parameter required");
        }
        ApplicationConfiguration applicationConfiguration = new ApplicationConfiguration();
        CryptoCurrenciesAssetsReportController cryptoCurrenciesAssetsReportController = applicationConfiguration.cryptoCurrenciesAssetsReportController();
        FileReader fileReader = applicationConfiguration.fileReader();
        String fileContent = fileReader.readAsString(args[0]);
        System.out.println(cryptoCurrenciesAssetsReportController.generateReport(fileContent, Currency.EUR));
    }
}
