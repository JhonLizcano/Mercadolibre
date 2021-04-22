package com.mercadolibre.challenge_mutantes.utils;

public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Constants Utility");
	}
	
	/*
	 * Numbers
	 */
	public static final int FOUR = 4;
	
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String ACCEPT = "Accept";
	public static final String HTTP_HEADER_X_CONTENT_TYPE_OPTION = "X-Content-Type-Options";
	public static final String HTTP_HEADER_X_CONTENT_TYPE_OPTION_VALUE = "nosniff";
	
	/*
	 * Pattern validation
	 */
	public static final String DNA_PATTERN = "^([ACGT]+)$";
	public static final String DNA_SEQUENCES_MATCH = "(A){4}|(C){4}|(G){4}|(T){4}";
	
	/*
	 * Messages
	 */
	public static final String MSG_DNA_PATTERN = "Invalid DNA Sequence";
}