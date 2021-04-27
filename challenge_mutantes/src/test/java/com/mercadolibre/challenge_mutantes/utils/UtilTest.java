package com.mercadolibre.challenge_mutantes.utils;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.model.DnaSequence;
import com.mercadolibre.challenge_mutantes.model.StatsResponse;

public class UtilTest {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ChallengeRequest getChallengeRequest() {
		DnaSequence[] dna = new DnaSequence[] {
				new DnaSequence("ATGC"), new DnaSequence("CAGT"), new DnaSequence("TTAT"), new DnaSequence("AGAA")};
		List<DnaSequence> listDna = new ArrayList(Arrays.asList(dna));
		return ChallengeRequest.builder().dna(listDna).build();
	}
	
	public static StatsResponse getStatsResponse() {
		return StatsResponse.builder()
				.count_mutant_dna(Constants.ONE_LONG)
				.count_human_dna(Constants.ONE_LONG)
				.ratio(Constants.ONE_DOUBLE)
				.build();
	}
}