package com.mercadolibre.challenge_mutantes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.service.MutantService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/")
@Validated
@AllArgsConstructor
public class MutantController {
	
	private final MutantService mutantServiceImpl;
	
	@PostMapping(value = "/isMutant")
	public ResponseEntity<Void> isMutant(@RequestBody ChallengeRequest challengeRequest) {
		return mutantServiceImpl.execute(challengeRequest);
	}
}