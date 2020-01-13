package com.crypto.portfolio.domain.report;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetsReportTest {

    @Test
    public void shouldBuildReportForSingleAssetWithTotalValueEqualToValueOfSingleAsset() {
        //given
        AssetsReport.AssetsReportBuilder builder = AssetsReport.builder();

        //when
        AssetsReport assetsReport = builder.addAssetValue("BTC", BigDecimal.TEN)
                                           .build();

        //then
        assertEquals(BigDecimal.TEN, assetsReport.getTotalValue());
        assertEquals(BigDecimal.TEN, assetsReport.getAssetsValues().get("BTC"));
    }

    @Test
    public void shouldBuildReportForMultipleAssets() {
        //given
        AssetsReport.AssetsReportBuilder builder = AssetsReport.builder();

        //when
        AssetsReport assetsReport = builder.addAssetValue("BTC", BigDecimal.TEN)
                                           .addAssetValue("ETH", BigDecimal.valueOf(5))
                                           .build();

        //then
        assertEquals(BigDecimal.valueOf(15), assetsReport.getTotalValue());
        assertEquals(BigDecimal.TEN, assetsReport.getAssetsValues().get("BTC"));
        assertEquals(BigDecimal.valueOf(5), assetsReport.getAssetsValues().get("ETH"));
    }
}