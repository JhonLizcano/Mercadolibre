package com.mercadolibre.challenge_mutantes.model;

import javax.validation.constraints.Pattern;

import com.mercadolibre.challenge_mutantes.utils.Constants;
import com.sun.istack.internal.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DnaSequence {
	
	@NotNull
	@Pattern(regexp = Constants.DNA_PATTERN, message = Constants.MSG_DNA_PATTERN)
	private String sequence;
}