# Crypto Portfolio

Is a simple program which prints the value of Bob's crypto portfolio report in real currency.
Currently only euro (EUR) is supported but program can be extended to support other currencies.
Required java version 13.

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
./gradlew clean shadowJar
```

## Run locally

### Intellij 
1. Run a class `com.crypto.portfolio.distribution.Application` in Intellij.
2. Edit a run configuration adding `Program arguments` with absolute path to `distribution/src/test/resources/bobs_crypto.txt` file

### Command line
```
java -jar distribution\build\libs\crypto-portfolio.jar <absolute path to file distribution/src/test/resources/bobs_crypto.txt>
```