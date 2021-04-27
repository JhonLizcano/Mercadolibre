package com.mercadolibre.challenge_mutantes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.domain.DnaStats;
import com.mercadolibre.challenge_mutantes.model.StatsResponse;
import com.mercadolibre.challenge_mutantes.repository.DnaSequenceDBRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StatsServiceImpl implements StatsService {

	@Autowired
	private DnaSequenceDBRepository dnaSequenceDBRepository;
	
	public ResponseEntity<StatsResponse> execute() {
		DnaStats dnaStatsDefault = DnaStats.builder().statsId("STATS").build();
		DnaStats dnaStats = dnaSequenceDBRepository.getStats(dnaStatsDefault);
		
		StatsResponse statsResponse = StatsResponse.builder()
				.count_mutant_dna(dnaStats.getMutants())
				.count_human_dna(dnaStats.getHumans())
				.ratio(dnaStats.getRatio())
				.build();
		
		return new ResponseEntity<StatsResponse>(statsResponse, HttpStatus.OK);
	}
	
}