package com.tuaev.financial_manager.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tuaev.financial_manager.dto.ExchangeRateDTO;
import com.tuaev.financial_manager.entity.ExchangeRate;
import com.tuaev.financial_manager.repositories.ExchangeRateRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateService {

    private ExchangeRateRepo exchangeRateRepo;

    public List<ExchangeRate> getAllExchangeRates(){
        return exchangeRateRepo.findAll();
    }

    public void saveExchangeRate() throws IOException, InterruptedException {
        String url = "https://api.currencyapi.com/v3/latest?apikey=cur_live_VARInFyu46RQ4Y3hL70HUdqZkPpJXa2DBKWB0iAs";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        ExchangeRateDTO exchangeRateDTO = objectMapper.readValue(httpResponse.body(), ExchangeRateDTO.class);
        exchangeRateDTO.getData().forEach((code, currencyData) ->{
            if (code.equals("RUB") || code.equals("KZT")) {
                LocalDate date = exchangeRateDTO.getMeta().getLast_update_at().toLocalDate();
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setDate(date);
                exchangeRate.setCurrencyPair(currencyData.getCode() + "/USD");
                exchangeRate.setClose(currencyData.getValue());
                exchangeRateRepo.save(exchangeRate);
            }
        });
    }
}
