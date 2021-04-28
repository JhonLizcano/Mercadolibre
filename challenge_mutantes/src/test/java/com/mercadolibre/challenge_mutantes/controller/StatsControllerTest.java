package com.mercadolibre.challenge_mutantes.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mercadolibre.challenge_mutantes.model.StatsResponse;
import com.mercadolibre.challenge_mutantes.service.StatsServiceImpl;
import com.mercadolibre.challenge_mutantes.utils.UtilTest;

@RunWith(MockitoJUnitRunner.class)
public class StatsControllerTest {
	
	@Mock
	private StatsServiceImpl statsService;
	@InjectMocks
	private StatsController statsController;
	
	@Test
	public void testToStatsController() throws Exception {
		ResponseEntity<StatsResponse> responseExpected = 
				new ResponseEntity<StatsResponse>(UtilTest.getStatsResponse(), HttpStatus.OK);
		Mockito.when(statsService.execute()).thenReturn(responseExpected);
		
		ResponseEntity<StatsResponse> responseEntity = statsController.stats();
		
		Mockito.verify(statsService, Mockito.times(1)).execute();
		assertNotNull(responseEntity);
		assertEquals(responseExpected, responseEntity);
	}
}