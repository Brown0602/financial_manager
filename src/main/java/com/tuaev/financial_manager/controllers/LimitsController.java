package com.tuaev.financial_manager.controllers;

import com.tuaev.financial_manager.dto.LimitDTO;
import com.tuaev.financial_manager.services.limit.LimitServiceSaveLimit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LimitsController {

    private LimitServiceSaveLimit limitCreate;

    @PostMapping("/limit")
    public void createLimit(@RequestBody LimitDTO limitDTO){
        limitCreate.save(limitDTO);
    }

    @GetMapping("/limits")
    public void getLimits(){
    }

}
