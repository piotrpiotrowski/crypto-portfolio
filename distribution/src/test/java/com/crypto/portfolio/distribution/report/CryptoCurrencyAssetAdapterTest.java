package com.crypto.portfolio.distribution.report;

import java.math.BigDecimal;
import java.util.List;

import com.crypto.portfolio.distribution.exception.ApplicationException;
import com.crypto.portfolio.domain.asset.CryptoCurrencyAsset;
import com.crypto.portfolio.domain.currency.CryptoCurrency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CryptoCurrencyAssetAdapterTest {

    CryptoCurrencyAssetAdapter adapter = new CryptoCurrencyAssetAdapter();

    @Test
    public void shouldAdaptStringRepresentationOfPortfolio() {
        //given
        String stringRepresentation = "BTC=10\n" +
                "ETH=5\n" +
                "XRP=2000";
        //when
        List<CryptoCurrencyAsset> cryptoCurrencyAssets = adapter.adapt(stringRepresentation);

        //then
        assertEquals(CryptoCurrency.BTC, cryptoCurrencyAssets.get(0).getCryptoCurrency());
        assertEquals(BigDecimal.valueOf(10), cryptoCurrencyAssets.get(0).getQuantity());

        assertEquals(CryptoCurrency.ETH, cryptoCurrencyAssets.get(1).getCryptoCurrency());
        assertEquals(BigDecimal.valueOf(5), cryptoCurrencyAssets.get(1).getQuantity());

        assertEquals(CryptoCurrency.XRP, cryptoCurrencyAssets.get(2).getCryptoCurrency());
        assertEquals(BigDecimal.valueOf(2000), cryptoCurrencyAssets.get(2).getQuantity());
    }

    @Test
    public void shouldThrowApplicationExceptionWhenCryptoCurrencyUnsupported() {
        //given
        String stringRepresentation = "ABC=10";

        //expect
        ApplicationException applicationException = assertThrows(ApplicationException.class, () -> adapter.adapt(stringRepresentation));
        assertEquals("Unsupported crypto currency: ABC", applicationException.getMessage());
    }

    @Test
    public void shouldThrowApplicationExceptionWhenQuantityIsNotAValidNumber() {
        //given
        String stringRepresentation = "XRP=invalid-number";

        //expect
        ApplicationException applicationException = assertThrows(ApplicationException.class, () -> adapter.adapt(stringRepresentation));
        assertEquals("invalid-number is not a valid number", applicationException.getMessage());
    }

    @Test
    public void shouldThrowApplicationExceptionWhenLineDoesNotHaveExpectedFormat() {
        //given
        String stringRepresentation = "XRP->1";

        //expect
        ApplicationException applicationException = assertThrows(ApplicationException.class, () -> adapter.adapt(stringRepresentation));
        assertEquals("Cannot parse line [XRP->1]expected format CURRENCY=quantity", applicationException.getMessage());
    }

}