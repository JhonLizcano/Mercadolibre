package com.mercadolibre.challenge_mutantes.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class DotEnvUtil {
	
	private static DotEnvUtil dotEnvUtil;
	private static Dotenv dotEnv;
	
	private DotEnvUtil() {
		dotEnv = Dotenv.configure()
				.directory("./src/main/resources")
				.ignoreIfMalformed()
				.filename("env")
				.ignoreIfMissing()
				.load();
	}
	
	public static Dotenv getInstance() {
		if(dotEnvUtil == null) {
			dotEnvUtil = new DotEnvUtil();
		}
		return dotEnvUtil.getDotenv();
	}
	
	public Dotenv getDotenv() {
		return dotEnv;
	}
}