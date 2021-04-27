package com.mercadolibre.challenge_mutantes.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.model.DnaSequence;
import com.mercadolibre.challenge_mutantes.utils.UtilTest;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceImplTest {
	
	private ChallengeRequest challengeRequest;
	
	@InjectMocks
	private MutantServiceImpl mutantService;
	
	@Before
	public void setup() {
		challengeRequest = UtilTest.getChallengeRequest();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void isDnaMutanteMatchFilas200() {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.OK);
		
		DnaSequence[] dna = new DnaSequence[] {
				new DnaSequence("ATGC"), new DnaSequence("CCCC"), new DnaSequence("TGAT"), new DnaSequence("AAAA")};
		List<DnaSequence> listDna = new ArrayList(Arrays.asList(dna));
		ResponseEntity<Void> responseEntity = mutantService.execute(ChallengeRequest.builder().dna(listDna).build());
		
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void isDnaMutanteMatchColumnas200() {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.OK);
		
		DnaSequence[] dna = new DnaSequence[] {
				new DnaSequence("ATGC"), new DnaSequence("AAGT"), new DnaSequence("ATGT"), new DnaSequence("AGGA")};
		List<DnaSequence> listDna = new ArrayList(Arrays.asList(dna));
		ResponseEntity<Void> responseEntity = mutantService.execute(ChallengeRequest.builder().dna(listDna).build());
		
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void isDnaMutanteMatchDiagonalID200() {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.OK);
		
		DnaSequence[] dna = new DnaSequence[] {
				new DnaSequence("ATGCT"), new DnaSequence("CATTG"), new DnaSequence("TTATA"), 
				new DnaSequence("AGAAT"), new DnaSequence("GTGAT")};
		List<DnaSequence> listDna = new ArrayList(Arrays.asList(dna));
		ResponseEntity<Void> responseEntity = mutantService.execute(ChallengeRequest.builder().dna(listDna).build());
		
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void isDnaMutanteMatchDiagonalDI200() {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.OK);
		
		DnaSequence[] dna = new DnaSequence[] {
				new DnaSequence("TCGTA"), new DnaSequence("CTTAG"), new DnaSequence("TTATA"), 
				new DnaSequence("TAAGA"), new DnaSequence("GTGAT")};
		List<DnaSequence> listDna = new ArrayList(Arrays.asList(dna));
		ResponseEntity<Void> responseEntity = mutantService.execute(ChallengeRequest.builder().dna(listDna).build());
		
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
	
	@Test
	public void isDnaHumano403() {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		
		ResponseEntity<Void> responseEntity = mutantService.execute(challengeRequest);
		
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
}