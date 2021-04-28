package com.mercadolibre.challenge_mutantes.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolibre.challenge_mutantes.model.StatsResponse;
import com.mercadolibre.challenge_mutantes.service.StatsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/")
@Validated
@AllArgsConstructor
public class StatsController {
	
	private final StatsService statsServiceImpl;
		
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatsResponse> stats() {
		return statsServiceImpl.execute();
	}
}