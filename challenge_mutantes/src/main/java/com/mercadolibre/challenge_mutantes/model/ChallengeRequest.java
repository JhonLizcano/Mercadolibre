package com.mercadolibre.challenge_mutantes.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeRequest {
	
	@NotNull
	@NotEmpty
	private List<@Valid DnaSequence> dna;
}