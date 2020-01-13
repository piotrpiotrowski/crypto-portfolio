package com.crypto.portfolio.distribution;

import java.net.http.HttpClient;

import com.crypto.portfolio.distribution.file.FileReader;
import com.crypto.portfolio.distribution.report.AssetsReportAdapter;
import com.crypto.portfolio.distribution.report.CryptoCurrenciesAssetsReportController;
import com.crypto.portfolio.distribution.report.CryptoCurrencyAssetAdapter;
import com.crypto.portfolio.distribution.report.RestCryptoCurrencyExchangeRepository;
import com.crypto.portfolio.domain.GenerateCryptoCurrenciesAssetsReportUseCase;

public class ApplicationConfiguration {

    public CryptoCurrenciesAssetsReportController cryptoCurrenciesAssetsReportController() {
        return new CryptoCurrenciesAssetsReportController(generateCryptoCurrenciesAssetsReportUseCase(), new CryptoCurrencyAssetAdapter(),
                new AssetsReportAdapter());
    }

    private GenerateCryptoCurrenciesAssetsReportUseCase generateCryptoCurrenciesAssetsReportUseCase() {
        return new GenerateCryptoCurrenciesAssetsReportUseCase(new RestCryptoCurrencyExchangeRepository(HttpClient.newHttpClient(), "https://min-api" +
                ".cryptocompare.com"));
    }

    public FileReader fileReader() {
        return new FileReader();
    }
}
