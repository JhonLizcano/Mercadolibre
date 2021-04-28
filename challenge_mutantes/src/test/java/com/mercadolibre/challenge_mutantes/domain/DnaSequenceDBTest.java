package com.mercadolibre.challenge_mutantes.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.mercadolibre.challenge_mutantes.utils.UtilTest;

public class DnaSequenceDBTest {
	
	@Test
	public void testToDnaSequenceDB() {
		DnaSequenceDB dnaSequenceDB = UtilTest.getDnaSequenceDB();
		
		assertNotNull(dnaSequenceDB);
		assertNotNull(dnaSequenceDB.getSequence());
		assertNotNull(dnaSequenceDB.isMutant());
	}
}