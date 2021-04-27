package com.mercadolibre.challenge_mutantes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsResponse {
	
	private Long count_mutant_dna;
	private Long count_human_dna;
	private Double ratio;
}