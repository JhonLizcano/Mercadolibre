package com.mercadolibre.challenge_mutantes.service;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.StatsResponse;

public interface StatsService {
	
	ResponseEntity<StatsResponse> execute();
}