package com.mercadolibre.challenge_mutantes.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.ChallengeRequest;
import com.mercadolibre.challenge_mutantes.service.MutantServiceImpl;
import com.mercadolibre.challenge_mutantes.utils.UtilTest;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {
	
	private ChallengeRequest challengeRequest;
	
	@Mock
	private MutantServiceImpl mutantService;
	@InjectMocks
	private MutantController mutantController;
	
	@Before
	public void setup() {
		challengeRequest = UtilTest.getChallengeRequest();
	}
	
	@Test
	public void testToMutantController() throws Exception {
		ResponseEntity<Void> responseExpected = new ResponseEntity<Void>(HttpStatus.OK);
		Mockito.when(mutantService.execute(challengeRequest)).thenReturn(responseExpected);
		
		ResponseEntity<Void> responseEntity = mutantController.isMutant(challengeRequest);
		
		Mockito.verify(mutantService, Mockito.times(1)).execute(UtilTest.getChallengeRequest());
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
}