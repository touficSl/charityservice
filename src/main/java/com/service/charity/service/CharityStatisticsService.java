package com.service.charity.service;

import org.springframework.http.ResponseEntity;

public interface CharityStatisticsService {

    ResponseEntity<?> getCharityStatistics(String username);
}
