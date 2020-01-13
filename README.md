# Crypto Portfolio

Is a simple program which prints the value of Bob's crypto portfolio report in real currency.
Currently only euro (EUR) is supported but program can be extended to support other currencies.

Example
```
Assets report
BTC 72383.90 EUR
XRP 378.8000 EUR
ETH 641.90 EUR

Total portfolio value: 73404.6000 EUR
```

## Build
```
./gradlew clean build
```

## Run locally
1. Run a class `com.crypto.portfolio.distribution.Application` in Intellij.
2. Edit a run configuration adding `Program arguments` with absolute path to `distribution/src/test/resources/bobs_crypto.txt` file