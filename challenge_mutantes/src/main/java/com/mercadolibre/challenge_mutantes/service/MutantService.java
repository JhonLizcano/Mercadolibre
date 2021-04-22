package com.mercadolibre.challenge_mutantes.service;

import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;

public interface MutantService {
	
	ResponseEntity<Void> execute(ChallengeRequest challengeRequest);
}