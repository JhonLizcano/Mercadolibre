package com.mercadolibre.challenge_mutantes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mercadolibre.challenge_mutantes.repository.DnaSequenceDBRepository;
import com.mercadolibre.challenge_mutantes.service.MutantServiceImpl;
import com.mercadolibre.challenge_mutantes.service.StatsServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public MutantServiceImpl mutantServiceImpl(DnaSequenceDBRepository dnaSequenceDBRepository) {
		return new MutantServiceImpl(dnaSequenceDBRepository);
	}
	
	@Bean
	public StatsServiceImpl statsServiceImpl(DnaSequenceDBRepository dnaSequenceDBRepository) {
		return new StatsServiceImpl(dnaSequenceDBRepository);
	}
}