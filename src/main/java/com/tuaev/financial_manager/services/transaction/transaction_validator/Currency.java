package com.tuaev.financial_manager.services.transaction.transaction_validator;

public enum Currency {

    CURRENCIES("USD");

    private final String usd;


    Currency(String usd) {
        this.usd = usd;
    }

    public String getUSD() {
        return usd;
    }
}
