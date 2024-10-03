package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.services.ExchangeRateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class LimitsController {

    private ExchangeRateService exchangeRateService;

    @PutMapping("/limits")
    public void updateLimit(){

    }

    @GetMapping("/limits")
    public void getLimits() throws IOException, InterruptedException {
        //exchangeRateService.saveExchangeRate();
    }

    @GetMapping("/limits/{userId}")
    public void getLimitsByUserId(@PathVariable("userId") Long userId){

    }
}
