package com.mercadolibre.challenge_mutantes.utils;

public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Constants Utility");
	}
	
	/*
	 * Numbers
	 */
	public static final int ZERO = 0;
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int FOUR = 4;
	public static final long ZERO_LONG = 0L;
	public static final long ONE_LONG = 1L;
	public static final double ZERO_POINT_ONE = 0.1;
	public static final double ONE_DOUBLE = 1;
	
	/*
	 * Texts
	 */
	public static final String STATS = "STATS";
	
	/*
	 * Fields
	 */
	public static final String STATS_ID = "statsId";
	
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