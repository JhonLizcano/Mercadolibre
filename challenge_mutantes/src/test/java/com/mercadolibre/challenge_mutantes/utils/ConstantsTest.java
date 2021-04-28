package com.mercadolibre.challenge_mutantes.utils;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class ConstantsTest {
	
	@Test(expected = IllegalStateException.class)
	public void evilConstructorInaccessibilityTest() throws Exception {
		Whitebox.invokeConstructor(Constants.class);
	}
}