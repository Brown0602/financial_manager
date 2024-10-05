package com.tuaev.financial_manager.services.exchangeRate;

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
public class ExchangeRateService implements ExchangeRateSave, ExchangeRateIfEmptyOrSizeNull, ExchangeRateCheckingTheCorrectDate{

    private ExchangeRateRepo exchangeRateRepo;

    @Override
    public List<ExchangeRate> checkingTheCorrectDate(List<ExchangeRate> exchangeRates) throws IOException, InterruptedException {
        if (exchangeRates.stream().noneMatch(exchangeRate -> exchangeRate.getDate().equals(LocalDate.now().minusDays(1)))){
            exchangeRateRepo.deleteAll();
            save();
            exchangeRates = exchangeRateRepo.findAll();
        }
        return exchangeRates;
    }

    @Override
    public List<ExchangeRate> ifEmptyOrSizeNull(List<ExchangeRate> exchangeRates) throws IOException, InterruptedException {
        if (exchangeRates.isEmpty()){
            exchangeRates = exchangeRateRepo.findAll();
        }
        if (exchangeRates.size() == 0){
            save();
            exchangeRates = exchangeRateRepo.findAll();
        }
        return exchangeRates;
    }

    @Override
    public void save() throws IOException, InterruptedException {
        String url = "https://api.currencyapi.com/v3/latest?apikey=cur_live_VARInFyu46RQ4Y3hL70HUdqZkPpJXa2DBKWB0iAs";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.
                newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
        ExchangeRateDTO exchangeRateDTO = objectMapper.readValue(httpResponse.body(), ExchangeRateDTO.class);
        exchangeRateDTO.getData().forEach((code, currencyData) -> {
            if (code.equals("RUB") || code.equals("KZT")) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setClose(currencyData.getValue());
                    exchangeRate.setCurrencyPair(currencyData.getCode() + "/USD");
                    exchangeRate.setDate(exchangeRateDTO.getMeta().getLast_update_at().toLocalDate());
                    exchangeRateRepo.save(exchangeRate);
            }
        });
    }
}
