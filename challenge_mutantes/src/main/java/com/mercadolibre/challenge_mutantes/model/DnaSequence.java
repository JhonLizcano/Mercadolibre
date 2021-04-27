package com.mercadolibre.challenge_mutantes.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.mercadolibre.challenge_mutantes.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DnaSequence {
	
	@NotNull
	@Pattern(regexp = Constants.DNA_PATTERN, message = Constants.MSG_DNA_PATTERN)
	private String sequence;
}