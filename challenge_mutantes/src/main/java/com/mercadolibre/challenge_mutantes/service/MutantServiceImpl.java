package com.mercadolibre.challenge_mutantes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.domain.DnaSequenceDB;
import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.model.DnaSequence;
import com.mercadolibre.challenge_mutantes.repository.DnaSequenceDBRepository;
import com.mercadolibre.challenge_mutantes.utils.Constants;

import lombok.AllArgsConstructor;

import static java.lang.Math.min;
import static java.lang.Math.max;

@AllArgsConstructor
public class MutantServiceImpl implements MutantService {
	
	@Autowired
	private DnaSequenceDBRepository dnaSequenceDBRepository;
	
	public ResponseEntity<Void> execute(ChallengeRequest challengeRequest) {
		ResponseEntity<Void> response;
		boolean mutant = false;
		int countMatches = validarSecuencia(challengeRequest.getDna());
		if (countMatches < Constants.TWO)
			countMatches += validarSecuencia(obtenerColumnas(challengeRequest.getDna()));
		if (countMatches < Constants.TWO)
			countMatches += validarSecuencia(obtenerDiagonal(challengeRequest.getDna()));
		if (countMatches < Constants.TWO)
			countMatches += validarSecuencia(obtenerDiagonal(voltearHorizontales(challengeRequest.getDna())));
		DnaSequenceDB dnaSequenceDB = new DnaSequenceDB();
		dnaSequenceDB.setSequence(challengeRequest.getDna());
		if (countMatches >= Constants.TWO) {
			dnaSequenceDB.setMutant(true);
			mutant = true;
			response =  ResponseEntity.ok().build();
		} else {
			dnaSequenceDB.setMutant(false);
			response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		dnaSequenceDBRepository.save(dnaSequenceDB, mutant);
		return response;
	}
	
	/*
	 * Método que valida la cantidad de veces que se encuentra un patrón en un listado de DnaSequence's
	 * @parameter dna
	 * @return resultado
	 */
	public int validarSecuencia(List<DnaSequence> dna) {
        int resultado = Constants.ZERO;
        for(DnaSequence dnaSequence : dna) {
        	Pattern pattern = Pattern.compile(Constants.DNA_SEQUENCES_MATCH);
        	Matcher matcher = pattern.matcher(dnaSequence.getSequence());
        	while (matcher.find()) {
        		resultado++;
    		}
        }
		return resultado;
	}
	
	/*
	 * Método que se ancarga de girar un listado de DnaSequence, es decir, pasar las columnas a filas
	 * @parameter dna
	 * @return listaVerticales
	 */
	public List<DnaSequence> obtenerColumnas(List<DnaSequence> dna) {
		List<DnaSequence> listaVerticales = new ArrayList<DnaSequence>();
		for(int i = Constants.ZERO; i < dna.size(); i++) {
			StringBuilder dnaSequenceV = new StringBuilder("");
			for(int j = Constants.ZERO; j < dna.size(); j++) {
				dnaSequenceV.append(dna.get(j).getSequence().charAt(i));
			}
			DnaSequence dnaSequence = DnaSequence.builder().sequence(dnaSequenceV.toString()).build();
			listaVerticales.add(dnaSequence);
		}
		return listaVerticales;
	}
	
	/*
	 * Método que se encarga de obtener las diagonales de la lista de izquierda a derecha siempre y cuando
	 * sean de longitud 4 o superior
	 * @parameter dna
	 * @return listaDiagonalAdelante
	 */
	public List<DnaSequence> obtenerDiagonal(List<DnaSequence> dna) {
		List<DnaSequence> listaDiagonalAdelante = new ArrayList<DnaSequence>();
		for (int i = Constants.ONE - dna.size(); i < dna.size(); i++) {
			StringBuilder dnaSequenceD = new StringBuilder("");
			for (int x = -min(Constants.ZERO, i), y = max(Constants.ZERO, i);
					x < dna.size() && y < dna.size(); x++, y++) {
				dnaSequenceD.append(dna.get(y).getSequence().charAt(x));
			}
			DnaSequence dnaSequence = DnaSequence.builder().sequence(dnaSequenceD.toString()).build();
			if(dnaSequence.getSequence().length() >= Constants.FOUR)
				listaDiagonalAdelante.add(dnaSequence);
		}
		return listaDiagonalAdelante;
	}
	
	/*
	 * Método que se encarga de obtener los inversos de las filas de la matríz
	 * @parameter dna
	 * @return listReverse
	 */
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