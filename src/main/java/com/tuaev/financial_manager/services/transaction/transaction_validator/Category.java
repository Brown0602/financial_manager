package com.tuaev.financial_manager.services.transaction.transaction_validator;

public enum Category {
    CATEGORIES("Товары", "Услуги");

    private final String products;
    private final String services;

    Category(String products, String services) {
        this.products = products;
        this.services = services;
    }

    public String getProducts() {
        return products;
    }

    public String getServices() {
        return services;
    }
}
