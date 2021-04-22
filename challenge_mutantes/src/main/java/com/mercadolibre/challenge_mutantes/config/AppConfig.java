package com.mercadolibre.challenge_mutantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercadolibre.challenge_mutantes.service.MutantServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public MutantServiceImpl mutantServiceImpl() {
		return new MutantServiceImpl();
	}
}