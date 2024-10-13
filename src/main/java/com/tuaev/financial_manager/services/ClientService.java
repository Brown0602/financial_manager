package com.tuaev.financial_manager.services;

import com.tuaev.financial_manager.repositories.ClientRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientService {

    private ClientRepo clientRepo;
}
