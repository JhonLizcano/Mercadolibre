package com.mercadolibre.challenge_mutantes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.model.DnaSequence;
import com.mercadolibre.challenge_mutantes.utils.Constants;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class MutantServiceImpl implements MutantService {
	
	public ResponseEntity<Void> execute(ChallengeRequest challengeRequest) {
		int countMatches = validarSecuencia(challengeRequest.getDna());
		if (countMatches < 2)
			countMatches += validarSecuencia(obtenerColumnas(challengeRequest.getDna()));
		if (countMatches < 2)
			countMatches += validarSecuencia(obtenerDiagonal(challengeRequest.getDna()));
		if (countMatches < 2)
			countMatches += validarSecuencia(obtenerDiagonal(voltearHorizontales(challengeRequest.getDna())));
		if (countMatches >= 2)
			return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	public int validarSecuencia(List<DnaSequence> dna) {
        int resultado = 0;
        for(DnaSequence dnaSequence : dna) {
        	Pattern pattern = Pattern.compile(Constants.DNA_SEQUENCES_MATCH);
        	Matcher matcher = pattern.matcher(dnaSequence.getSequence());
        	while (matcher.find()) {
        		resultado++;
    		}
        }
		return resultado;
	}
	
	public List<DnaSequence> obtenerColumnas(List<DnaSequence> dna) {
		List<DnaSequence> listaVerticales = new ArrayList<DnaSequence>();
		for(int i = 0; i < dna.size(); i++) {
			StringBuilder dnaSequenceV = new StringBuilder("");
			for(int j = 0; j < dna.size(); j++) {
				dnaSequenceV.append(dna.get(j).getSequence().charAt(i));
			}
			DnaSequence dnaSequence = DnaSequence.builder().sequence(dnaSequenceV.toString()).build();
			listaVerticales.add(dnaSequence);
		}
		return listaVerticales;
	}
	
	public List<DnaSequence> obtenerDiagonal(List<DnaSequence> dna) {
		List<DnaSequence> listaDiagonalAdelante = new ArrayList<DnaSequence>();
		for (int i = 1 - dna.size(); i < dna.size(); i++) {
			StringBuilder dnaSequenceD = new StringBuilder("");
			for (int x = -min(0, i), y = max(0, i); x < dna.size() && y < dna.size(); x++, y++) {
				dnaSequenceD.append(dna.get(y).getSequence().charAt(x));
			}
			DnaSequence dnaSequence = DnaSequence.builder().sequence(dnaSequenceD.toString()).build();
			if(dnaSequence.getSequence().length() >= Constants.FOUR)
				listaDiagonalAdelante.add(dnaSequence);
		}
		return listaDiagonalAdelante;
	}
	
	public List<DnaSequence> voltearHorizontales(List<DnaSequence> dna) {
		List<DnaSequence> listReverse = new ArrayList<DnaSequence>();
		for(DnaSequence dnaSequence : dna) {
			DnaSequence dnaSequenceReverse = DnaSequence.builder()
					.sequence(new StringBuilder(dnaSequence.getSequence()).reverse().toString()).build();
			listReverse.add(dnaSequenceReverse);
		}
		return listReverse;
	}
}